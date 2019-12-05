import PlayerHandler.*;
import PlayerHandler.CombatHandler.CombatGroup;
import PlayerHandler.GamePieces.Room;
import PlayerHandler.UI.Frame;
import PlayerHandler.UI.StandardFrame;

import javax.sql.ConnectionEventListener;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/*
 * This is an example of how to run the adventure server.
 * Your game class parses and responds to input by handling ConnectionEvents.
 */
public class TechAdventure implements ConnectionListener {
	private AdventureServer adventureServer;
	private InputHandler inputHandler = new InputHandler();
	private String[] serverCommands = {"SHUTDOWN", "IPADDRESS", "SERVERMESSAGE"};
	private ArrayList<Room> rooms = new ArrayList<>();
	private Room startRoom;
	private ArrayList<CombatGroup> combatGroups = new ArrayList<>();

	public TechAdventure() {
		adventureServer = new AdventureServer();
		adventureServer.setOnTransmission(this);
		adventureServer.setFrameEventListener(e -> {
			//Run app frames
			for (int i = 0; i < combatGroups.size(); i++) {
				if (combatGroups.get(i).getCombatants().size() <= 0) {
					combatGroups.remove(combatGroups.get(i));
					i--;
				}
			}

			for (CombatGroup group : combatGroups) {
				group.RunCombat();
			}
		});
	}

	public void start(int port) {
		Room startRoom = new Room("The Void",
				"You are in an endless void.\r\n" +
						"Looking around, you see nothing, just darkness." +
						"It is darker than the blackest night of a starless sky.\r\n" +
						"Maybe you should ask someone to program something for you to do.",
				"You see darkness, darker than the blackest night.");
		rooms.add(startRoom);
		startRoom.setEast(startRoom);
		startRoom.setWest(startRoom);
		startRoom.setNorth(startRoom);
		startRoom.setSouth(startRoom);
		this.startRoom = startRoom;
		inputHandler.setMessageListener(e -> {
			try {
				Frame frame = e.getClient().getLastFrame();
				frame.addLine(e.getMessage(), true);
				adventureServer.sendMessage(e.getClient().getConnectionID(), frame.getOutput());
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
					player.setLocation(new Tutorial().getStart());
					player.setInfoEventListener(event -> {
						try {
							Frame output = player.getLastFrame();
							if (output.isEmpty()) {
								output = new StandardFrame();
							}
							output.addLine(event.getMessage(), true);
							player.setLastFrame(output);
							adventureServer.sendMessage(event.getSource().getConnectionID(), output.getOutput());
						} catch (UnknownConnectionException ex) {
							ex.printStackTrace();
						}
					});
					Frame output = player.getLocation().getDescription(player, new StandardFrame());
					player.setLastFrame(output);
					adventureServer.sendMessage(player.getConnectionID(), output.getOutput());
					player.setState(PlayerStates.normal);
					break;
				case TRANSMISSION_RECEIVED:
					adventureServer.sendMessage(e.getConnectionID(), String.format(
							"MESSAGE RECEIVED: connectionId=%d, data=%s", e.getConnectionID(), e.getData()));
					player = Player.findClient(e.getConnectionID());

					if (player.getState() == PlayerStates.normal) {
						Frame message = inputHandler.handleInput(e.getData(), player);
						if (!message.isEmpty()) {
							adventureServer.sendMessage(e.getConnectionID(), message.getOutput());
						}
					} else if (player.getState() == PlayerStates.combat) {
						switch (player.getCombatGroup().getCombatState()) {
							case startCombat:
								try {
									int choice = Integer.parseInt(e.getData());
									if (choice > 0 && choice < player.getCombatGroup().getCombatants().size()) {
										player.setTarget(player.getCombatGroup().getCombatants().get(choice - 1));
									}

								} catch (NumberFormatException ex) {
									ex.printStackTrace();
								}
								break;
							case words:
								player.getWords().add(e.getData().toLowerCase());
								break;
							case rps:
								Scanner scanner = new Scanner(e.getData().toLowerCase());
								switch (scanner.next()) {
									case "fight":
										player.setCombatDecision(CombatGroup.rpsChoice.fight);
										break;
									case "flee":
										player.setState(PlayerStates.normal);
										player.getCombatGroup().removeCombatant(player);
										if (scanner.hasNext()) {
											handle(new ConnectionEvent(ConnectionEventCode.CONNECTION_ESTABLISHED,
													e.getConnectionID(), "go " + scanner.nextLine()));
										} else {
											int direction = (int) (Math.random() * 4.0);
											switch (direction) {
												case 0:
													player.setLocation(player.getLocation().getEast());
													break;
												case 1:
													player.setLocation(player.getLocation().getNorth());
													break;
												case 2:
													player.setLocation(player.getLocation().getSouth());
													break;
												case 3:
													player.setLocation(player.getLocation().getWest());
													break;
											}
										}

										break;
									case "talk":
										player.setCombatDecision(CombatGroup.rpsChoice.talk);
										break;
								}
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
			case attack:
				combatGroups.add(new CombatGroup(player.getLocation().getCombatants()));
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
