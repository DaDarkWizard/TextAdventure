package PlayerHandler;

import PlayerHandler.GamePieces.Holdable;
import PlayerHandler.GamePieces.Interactable;
import PlayerHandler.UI.Frame;
import PlayerHandler.UI.StandardFrame;
import com.sun.jmx.snmp.SnmpUnknownAccContrModelException;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class InputHandler {
    private String[] east = {"e", "east", "right"};
    private String[] west = {"w", "west", "left"};
    private String[] north = {"n", "north", "up"};
    private String[] south = {"s", "south", "down"};
    private String[] say = {"say", "shout", "yell", "orate"};
    private String[] fillerWords = {"the", "at", "a", "an", "of", "for", "over"};
    private String[] inventory = {"inventory", "items", "stuff"};
    private String badMove = "You can't move that direction.";
    private MessageListener messageListener;
    private ServerCommandListener serverCommandListener;

    public Commands getCommand(String command) {
        if (Arrays.binarySearch(east, command) > -1) {
            return Commands.east;
        } else if (Arrays.binarySearch(west, command) > -1) {
            return Commands.west;
        } else if (Arrays.binarySearch(north, command) > -1) {
            return Commands.north;
        } else if (Arrays.binarySearch(south, command) > -1) {
            return Commands.south;
        } else if (Arrays.binarySearch(say, command) > -1) {
            return Commands.say;
        } else if (Arrays.binarySearch(inventory, command) > -1) {
            return Commands.inventory;
        } else if (command.equals("go")) {
            return Commands.skip;
        } else if (command.equals("SHUTDOWN")) {
            return Commands.SHUTDOWN;
        } else if (command.equals("SERVERMESSAGE")) {
            return Commands.SERVERMESSAGE;
        } else if (command.equals("look")) {
            return Commands.look;
        } else if (command.equals("attack")) {
            return Commands.attack;
        }
        return null;
    }

    public Frame handleInput(String input, Player player) {
        Frame output = new StandardFrame();                          //The output to send to the player
        MessageEvent messageEvent;
        ServerCommandEvent serverCommandEvent;

        //Normalize input
        input = input.toLowerCase();

        Scanner scanner = new Scanner(input);
        Commands command = getCommand(scanner.next());
        if (command == null) {
            output.clearFrame();
            output.addLine("That command isn't valid!");
            return output;
        }
        player.setLastCommand(command);

        switch (command) {
            case skip:
                StringBuilder refresh = new StringBuilder();
                while (scanner.hasNext()) {
                    refresh.append(scanner.next());
                }
                output = handleInput(refresh.toString(), player);
                break;
            case east:
                if (player.getLocation().getEast() == null) {
                    output.clearFrame();
                    output.addLine(badMove);
                } else {
                    player.setLocation(player.getLocation().getEast());
                    output = player.getLocation().getDescription(player, output);
                }
                break;
            case west:
                if (player.getLocation().getWest() == null) {
                    output.clearFrame();
                    output.addLine(badMove);
                } else {
                    player.setLocation(player.getLocation().getWest());
                    output = player.getLocation().getDescription(player, output);
                }
                break;
            case south:
                if (player.getLocation().getSouth() == null) {
                    output.clearFrame();
                    output.addLine(badMove);
                } else {
                    player.setLocation(player.getLocation().getSouth());
                    output = player.getLocation().getDescription(player, output);
                }
                break;
            case north:
                if (player.getLocation().getNorth() == null) {
                    output.clearFrame();
                    output.addLine(badMove);
                } else {
                    player.setLocation(player.getLocation().getNorth());
                    output = player.getLocation().getDescription(player, output);
                }
                break;
            case say:
                String message = scanner.nextLine();
                output = player.getLastFrame();
                output.addLine("You say, \"" + message + "\" to everyone in the room.");
                message = player.getUsername() + ": " + message;
                for (Player receiver : player.getLocation().getPlayers()) {
                    if (player != receiver && !receiver.isBlocked(player)) {
                        messageEvent = new MessageEvent(player, receiver, message);
                        this.handleMessage(messageEvent);
                    }
                }
                break;
            case look:
                if (!scanner.hasNext()) {
                    output = player.getLocation().getDescription(player, output);
                } else {
                    String normalized = removeFillerWords(scanner.nextLine());
                    scanner.close();
                    scanner = new Scanner(normalized);
                    Commands direction = getCommand(scanner.next());
                    if (direction != null) {
                        if (player.getLocation().getRoomFromCommand(direction) != null) {
                            output = player.getLocation().getRoomFromCommand(direction).getLookDescription(player, output);
                        }
                    } else {
                        findObject(player, command, normalized);
                    }
                }
                break;
            case inventory:
                StringBuilder inventory = new StringBuilder();
                inventory.append("You have:\n");
                if (player.getInventory().size() < 1) {
                    inventory.append("  Absolutely Nothing");
                } else {
                    for (Holdable holdable : player.getInventory()) {
                        inventory.append("  ").append(holdable.getShortDescription()).append("\n");
                    }
                }
                break;
            case attack:
            case IPADDRESS:
            case SHUTDOWN:
                serverCommandEvent = new ServerCommandEvent(player, command, null);
                this.handleServerCommand(serverCommandEvent);
                break;
            case SERVERMESSAGE:
                serverCommandEvent = new ServerCommandEvent(player, command, new String[]{scanner.nextLine()});
                this.handleServerCommand(serverCommandEvent);
                break;
            default:
                output.addLine(findObject(player, command, scanner.nextLine()));
                if (output == null || output.isEmpty()) {
                    output = new StandardFrame();
                    output.addLine("You shouldn't be getting this.");
                }
        }
        return output;
    }

    public String findObject(Player player, Commands command, String name) {
        name = removeFillerWords(name);
        String output = "";
        search:
        {
            for (Holdable holdable : player.getInventory()) {
                if (holdable.isValidName(name)) {
                    output = holdable.interact(player, command);
                    break search;
                }
            }
            for (Interactable interactable : player.getLocation().getInteractables()) {
                if (interactable.isValidName(name)) {
                    output = interactable.interact(player, command);
                    break search;
                }
            }
        }
        return output;
    }

    public String removeFillerWords(String input) {
        ArrayList<String> words = new ArrayList<>();
        Scanner scanner = new Scanner(input);
        while (scanner.hasNext()) {
            words.add(scanner.next());
        }
        for (int i = 0; i < words.size(); i++) {
            if (Arrays.binarySearch(fillerWords, words.get(i)) > -1) {
                words.remove(i);
                i--;
            }
        }
        StringBuilder output = new StringBuilder();
        for (String word : words) {
            output.append(word).append(" ");
        }
        return output.toString();
    }

    public void setMessageListener(MessageListener listener) {
        messageListener = listener;
    }

    public void setServerCommandListener(ServerCommandListener listener) {
        serverCommandListener = listener;
    }

    public void handleServerCommand(ServerCommandEvent event) {
        if (serverCommandListener == null) {
            return;
        }
        serverCommandListener.handle(event);
    }

    public void handleMessage(MessageEvent event) {
        if (messageListener == null) {
            return;
        }
        messageListener.handle(event);
    }
}
