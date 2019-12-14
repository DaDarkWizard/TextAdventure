package PlayerHandler;

import CombatHandler.AttackCommands;
import CombatHandler.CombatGroup;
import CombatHandler.Weapons.TooFewCombatantsException;
import CombatHandler.Weapons.Weapon;
import GamePieces.Holdable;
import GamePieces.Interactable;
import GamePieces.Item;
import PlayerHandler.Persistence.CharacterLoading;
import PlayerHandler.Persistence.CharacterSaving;
import PlayerHandler.Persistence.PlayerUninitializedException;
import PlayerHandler.Persistence.PlayerUnknownException;
import PlayerHandler.UI.Frame;
import PlayerHandler.UI.InventoryFrame;
import PlayerHandler.UI.StandardFrame;
import PlayerHandler.UI.StatsFrame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class InputHandler {
    private static String[] east = {"e", "east", "right"};
    private static String[] west = {"w", "west", "left"};
    private static String[] north = {"n", "north", "up"};
    private static String[] south = {"s", "south", "down"};
    private static String[] say = {"say", "shout", "yell", "orate"};
    private static String[] fillerWords = {"the", "at", "a", "an", "of", "for", "over", "up"};
    private static String[] inventory = {"inventory", "items", "stuff"};
    private static String[] pickup = {"pickup", "grab", "get", "pick"};
    private static String[] drop = {"drop", "throw", "remove", "delete"};
    private static String badMove = "You can't move that direction.";
    private MessageListener messageListener;
    private ServerCommandListener serverCommandListener;

    public static Commands getCommand(String command) {
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
        } else if (Arrays.binarySearch(pickup, command) > -1 || command.equals("pickup")) {
            return Commands.pickup;
        } else if (Arrays.binarySearch(drop, command) > -1) {
            return Commands.drop;
        } else if (command.equals("stats")) {
            return Commands.stats;
        }else if (command.equals("go")) {
            return Commands.skip;
        } else if (command.equals("SHUTDOWN")) {
            return Commands.SHUTDOWN;
        } else if (command.equals("SERVERMESSAGE")) {
            return Commands.SERVERMESSAGE;
        } else if (command.equals("look")) {
            return Commands.look;
        } else if (command.equals("attack")) {
            return Commands.attack;
        } else if (command.equals("restore")) {
            return Commands.restore;
        } else if (command.equals("save")) {
            return Commands.save;
        } else if (command.equals("equip")) {
            return Commands.equip;
        } else if (command.equals("dequip")) {
            return Commands.dequip;
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
        String name = null;
        ArrayList<Interactable> possibleMatchesInter = null;
        ArrayList<Holdable> possibleMatchesHod = null;


        if (!scanner.hasNext()) {
            return player.getLastFrame();
        }
        Commands command = getCommand(scanner.next());
        if (command == null) {
            output = player.getLastFrame();
            output.addLine("[" + input + "]: That command isn't valid!");
            return output;
        }
        player.setLastCommand(command);
        String newInput;
        switch (command) {
            case equip:
                scanner = new Scanner(input);
                scanner.next();
                if (scanner.hasNext()) {
                    String s = scanner.nextLine();
                    Holdable item = checkInventory(player, s.trim());
                    System.out.println(s.trim());
                    output = player.getLastFrame();
                    if (item != null) {
                        if (verifyEquippable(player, item)) {
                            player.getEquipped().add((Weapon) item);
                            output.addLine("[equip]: You equip the " + item.getShortDescription());
                        } else {
                            output.addLine("[equip]: You already have something of that type equipped!");
                        }
                    } else {
                        output.addLine("[equip]: I can't find that item! Try to be more specific.");
                    }
                } else {
                    output = player.getLastFrame();
                    output.addLine("[equip]: You need to specify a target!");
                }
                break;
            case dequip:
                scanner = new Scanner(input);
                scanner.next();
                if (scanner.hasNext()) {
                    String s = scanner.nextLine();
                    Holdable item = checkInventory(player, s.trim());
                    System.out.println(s.trim());
                    output = player.getLastFrame();
                    if (item != null) {
                        if (player.getEquipped().contains(item)) {
                            output.addLine("[dequip]: You dequip the " + item.getShortDescription());
                            player.getEquipped().remove(item);
                        } else {
                            output.addLine("[dequip]: I can't find that item! Try to be more specific.");
                        }
                    } else {
                        output.addLine("[dequip]: I can't find that item! Try to be more specific.");
                    }
                } else {
                    output = player.getLastFrame();
                    output.addLine("[dequip]: You need to specify a target!");
                }
                break;
            case restore:
                new CharacterLoading().RestoreCharacter(player);
                output = player.getLastFrame();
                break;
            case save:
                try {
                    new CharacterSaving(player);
                    output = player.getLastFrame();
                    output.addLine("[save]: You have successfully saved!");
                } catch (PlayerUninitializedException e) {
                    output = player.getLastFrame();
                    output.addLine("[save]: You haven't restored or created a character yet!", true);
                } catch (PlayerUnknownException e) {
                    output = player.getLastFrame();
                    output.addLine("[save]: You are an anomaly. One of a kind. A bug in the program!");
                }
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
                    //output = new StandardFrame();
                    //output.add(player.getLocation().getDescription());
                    return player.getLastFrame();
                }
                break;
            case west:
                if (player.getLocation().getWest() == null) {
                    output.clearFrame();
                    output.addLine(badMove);
                } else {
                    player.setLocation(player.getLocation().getWest());
                    //output = new StandardFrame();
                    //output.add(player.getLocation().getDescription());
                    return player.getLastFrame();
                }
                break;
            case south:
                if (player.getLocation().getSouth() == null) {
                    output.clearFrame();
                    output.addLine(badMove);
                } else {
                    player.setLocation(player.getLocation().getSouth());
                    //output = new StandardFrame();
                    //output.add(player.getLocation().getDescription());
                    return player.getLastFrame();
                }
                break;
            case north:
                if (player.getLocation().getNorth() == null) {
                    output.clearFrame();
                    output.addLine(badMove);
                } else {

                    //output = new StandardFrame();
                    //output.add(player.getLocation().getDescription());

                    player.setLocation(player.getLocation().getNorth());
                    return player.getLastFrame();
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
                    output = new StandardFrame();
                    output.add(player.getLocation().getDescription());
                    player.setLastFrame(output);
                } else {
                    String normalized = removeFillerWords(scanner.nextLine());
                    scanner.close();
                    scanner = new Scanner(normalized);
                    Commands direction = getCommand(scanner.next());
                    if (direction != null) {
                        if (player.getLocation().getRoomFromCommand(direction) != null) {
                            output = player.getLastFrame();
                            output.newLine();
                            output.add("[look " + direction.toString() + "]: ");
                            output.add(player.getLocation().getRoomFromCommand(direction).getLookDescription());
                        }
                    } else {
                        output = player.getLastFrame();
                        output.addLine("[look]: " + findObject(player, command, normalized));
                    }
                }
                break;
            case inventory:
                InventoryFrame inventoryFrame = new InventoryFrame(player);
                output = player.getLastFrame();
                for (int i = 0; i < inventoryFrame.getCurrentSize(); i++) {
                    output.addLine(inventoryFrame.getLine(i), true);
                }
                return output;
            case stats:
                StatsFrame statsFrame = new StatsFrame(player);
                output = player.getLastFrame();
                for (int i = 0; i < statsFrame.getCurrentSize(); i++) {
                    output.addLine(statsFrame.getLine(i), true);
                }
                break;
            case pickup:
                output = player.getLastFrame();
                newInput = input;
                newInput = removeFillerWords(newInput);
                scanner = new Scanner(newInput);
                scanner.next();
                if (!scanner.hasNext()) {
                    output.addLine("[" + command.toString() + "]: You need to specify something to pick up!");
                    return output;
                }

                name = scanner.nextLine();
                name = name.trim();
                possibleMatchesInter = new ArrayList<>();
                for(Interactable i : player.getLocation().getInteractables()){
                    if(i.isValidName(name) && (i instanceof Holdable)){
                        possibleMatchesInter.add(i);
                    } else {
                        player.sendMessage("[" + command.toString() + "]: You can't do that!");
                    }
                }

                if (possibleMatchesInter.size() == 1) {
                    Item item = (Item) possibleMatchesInter.get(0);
                    String pickedUp = item.pickup(player);
                    output.addLine("[" + command.toString() + "]: " + pickedUp);
                } else if (possibleMatchesInter.size() > 1) {
                    output.addLine("[" + command.toString() + "]: Be more specific!");
                } else {
                    output.addLine("[" + command.toString() + "]: That doesn't exist!");
                }
                break;
            case drop:
                output = player.getLastFrame();
                newInput = input;
                newInput = removeFillerWords(newInput);
                scanner = new Scanner(newInput);
                scanner.next();
                if (!scanner.hasNext()) {
                    output.addLine("[" + command.toString() + "]: You need to specify something to drop!");
                    return output;
                }

                name = scanner.nextLine();
                name = name.trim();
                possibleMatchesInter = new ArrayList<>();
                for (Holdable i : player.getInventory()) {
                    System.out.println(name);
                    System.out.println(i.isValidName(name));
                    if (i.isValidName(name)) {
                        possibleMatchesInter.add(i);
                    } else {
                        player.sendMessage("[" + command.toString() + "]: You don't have one of those!");
                    }
                }

                if (possibleMatchesInter.size() == 1) {
                    Item item = (Item) possibleMatchesInter.get(0);
                    String dropped = item.drop(player);
                    output.addLine("[" + command.toString() + "]: " + dropped);
                } else if (possibleMatchesInter.size() > 1) {
                    output.addLine("[" + command.toString() + "]: Be more specific!");
                } else {
                    output.addLine("[" + command.toString() + "]: That doesn't exist!");
                }
                break;
            case attack:
                try {
                    new CombatGroup(player.getLocation().getCombatants(), player);
                } catch (TooFewCombatantsException e) {
                    output = player.getLastFrame();
                    output.addLine("[attack]: " + e.getMessage(), true);
                }
                break;
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

    private Holdable checkInventory(Player player, String name) {
        Holdable check = null;
        for (Holdable holdable : player.getInventory()) {
            if (holdable.isValidName(name)) {
                if (check == null) {
                    check = holdable;
                } else {
                    return null;
                }
            }
        }
        return check;
    }

    private boolean verifyEquippable(Player player, Holdable holdable) {
        if (!(holdable instanceof Weapon)) {
            return false;
        }
        AttackCommands command = ((Weapon) holdable).getAttackCommand();
        for (Weapon weapon : player.getEquipped()) {
            if (weapon.getAttackCommand() == command) {
                return false;
            }
        }
        return true;
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
