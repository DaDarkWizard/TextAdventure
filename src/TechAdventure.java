import PlayerHandler.Player;
import PlayerHandler.PlayerStates;
import PlayerHandler.Commands;
import PlayerHandler.GamePieces.Room;
import PlayerHandler.InputHandler;

import java.net.UnknownHostException;
import java.util.ArrayList;

/*
 * This is an example of how to run the adventure server.
 * Your game class parses and responds to input by handling ConnectionEvents.
 */
public class TechAdventure implements ConnectionListener {
    AdventureServer adventureServer = null;
    InputHandler inputHandler = new InputHandler();
	private String[] serverCommands = {"SHUTDOWN", "IPADDRESS", "SERVERMESSAGE"};
	private ArrayList<Room> rooms = new ArrayList<>();
	private Room startRoom;

    public TechAdventure() {
        adventureServer = new AdventureServer();
        adventureServer.setOnTransmission(this);
    }

    public void start(int port) {
		Room startRoom = new Room("The Void",
				"You are in an endless void.\n" +
						"Looking around, you see nothing, just darkness." +
						"It is darker than the blackest night of a starless sky.\n" +
						"Maybe you should ask someone to program something for you to do.",
				"You see darkness, darker than the blackest night.");
		rooms.add(startRoom);
		startRoom.setEast(startRoom);
		startRoom.setWest(startRoom);
		startRoom.setNorth(startRoom);
		startRoom.setSouth(startRoom);
		inputHandler.setMessageListener(e -> {
			try {
				adventureServer.sendMessage(e.getClient().getConnectionID(), e.getMessage());
			} catch (UnknownConnectionException ex) {
				ex.printStackTrace();
			}
		});

		inputHandler.setServerCommandListener(e -> {
			try {
				handleServerCommands(e.getClient(), e.getCommand(), e.getArgs());
			} catch (UnknownConnectionException ex) {
				ex.printStackTrace();
			}
		});

        adventureServer.startServer(port);
    }

    @Override
    public void handle(ConnectionEvent e) {
        System.out.println("EVENT RECEIVED - YOU MUST PARSE THE DATA AND RESPOND APPROPRIATELY");
        System.out.println(String.format("connectionId=%d, data=%s", e.getConnectionID(), e.getData()));
        try {
			Player player;
            switch (e.getCode()) {
                case CONNECTION_ESTABLISHED:
                    // What do you do when the connection is established?
					player = new Player(e.getConnectionID());
					player.setLocation(startRoom);
					adventureServer.sendMessage(player.getConnectionID(), player.getLocation().getDescription(player));
                    break;
                case TRANSMISSION_RECEIVED:
                    adventureServer.sendMessage(e.getConnectionID(), String.format(
                            "MESSAGE RECEIVED: connectionId=%d, data=%s", e.getConnectionID(), e.getData()));
					player = Player.findClient(e.getConnectionID());

					if (player.getState() == PlayerStates.normal) {
						String message = inputHandler.handleInput(e.getData(), player);
						if (!message.equals("")) {
							adventureServer.sendMessage(e.getConnectionID(), message);
						}
                    }
                    break;
                case CONNECTION_TERMINATED:
                    // Cleanup when the connection is terminated.
                    break;
                default:
                    // What is a reasonable default?
            }
        } catch (UnknownConnectionException unknownConnectionException) {
            unknownConnectionException.printStackTrace();
        }
    }

	private void handleServerCommands(Player player, Commands command, String[] args) throws UnknownConnectionException {
		switch (command) {
			case SHUTDOWN:
				player.setLastCommand(Commands.SHUTDOWN);
				broadcastMessage("SHUTTING DOWN...");
                adventureServer.stopServer();
                break;
			case SERVERMESSAGE:
				player.setLastCommand(Commands.SERVERMESSAGE);
				String message = player.getUsername() + " SERVER MESSAGE: " + args[0];
				broadcastMessage(message);
                break;
			case IPADDRESS:
				try {
					adventureServer.sendMessage(player.getConnectionID(),
							"IP Address: " + adventureServer.getInetAddress() + "\n" +
									"Port: " + adventureServer.getPort());
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
        }
    }

    private void broadcastMessage(String message) throws UnknownConnectionException {
		for (Player player : Player.getPlayers()) {
			adventureServer.sendMessage(player.getConnectionID(), message);
        }
    }

    public static void main(String[] args) {
        TechAdventure techAdventure = new TechAdventure();
        techAdventure.start(2112);
    }

}
