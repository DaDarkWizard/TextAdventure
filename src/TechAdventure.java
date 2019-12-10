import PlayerHandler.*;
import PlayerHandler.CombatHandler.CombatGroup;
import PlayerHandler.CombatHandler.Combatant;
import PlayerHandler.GamePieces.Room;
import PlayerHandler.UI.Frame;
import PlayerHandler.UI.StandardFrame;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Scanner;

public class TechAdventure implements ConnectionListener {
	private AdventureServer adventureServer;
	private InputHandler inputHandler = new InputHandler();
	private ArrayList<Room> rooms = new ArrayList<>();
	private Room startRoom;
	private static boolean runFrames = true;
	public static final Object lock = 0;

	public TechAdventure() {
		adventureServer = new AdventureServer();
		adventureServer.setOnTransmission(this);
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
		System.out.println("Trying to handle lock");
		synchronized (TechAdventure.lock) {
			System.out.println("Handled");
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
								if (output == null || output.isEmpty()) {
									output = new StandardFrame();
								}
								output.newLine();
								output.addLine(event.getMessage(), true);
								player.setLastFrame(output);
								adventureServer.sendMessage(event.getSource().getConnectionID(), output.getOutput());
							} catch (UnknownConnectionException ex) {
								ex.printStackTrace();
							}
						});

						player.setUpdateEventListener(event -> {
							Frame output = player.getLastFrame();
							if (output == null || output.isEmpty()) {
								return;
							}
							try {
								if (event.getSource() == null) {
									System.out.println("YEET");
								}
								adventureServer.sendMessage(event.getSource().getConnectionID(), output.getOutput());
							} catch (UnknownConnectionException ex) {
								ex.printStackTrace();
							}
						});
						Frame output = new StandardFrame();
						output.add(player.getLocation().getDescription());
						player.setLastFrame(output);
						adventureServer.sendMessage(player.getConnectionID(), output.getOutput());
						player.setState(PlayerStates.normal);
						break;
					case TRANSMISSION_RECEIVED:
						adventureServer.sendMessage(e.getConnectionID(), String.format(
								"MESSAGE RECEIVED: connectionId=%d, data=%s", e.getConnectionID(), e.getData()));
						player = Player.findClient(e.getConnectionID());
						System.out.println("Player State: " + player.getState());
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
												Room exitRoom = null;
												switch (direction) {
													case 0:
														exitRoom = player.getLocation().getEast();
														break;
													case 1:
														exitRoom = player.getLocation().getNorth();
														break;
													case 2:
														exitRoom = player.getLocation().getSouth();
														break;
													case 3:
														exitRoom = player.getLocation().getWest();
														break;
												}
												if (exitRoom == null) {
													player.sendMessage("In a panic you flee, but smack into a wall!");
													player.sendMessage("Looks like you're stuck here for now..");
												} else {
													player.setLocation(exitRoom);
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

	private static class RunFrame extends Thread {
		int port;


		void start(int port) {
			this.port = port;
			start();
		}

		public void run() {
			TechAdventure techAdventure = new TechAdventure();
			techAdventure.start(port);
		}
	}

	public static void main(String[] args) {
		RunFrame thread = new RunFrame();
		int port;
		if (args.length > 0) {
			try {
				port = Integer.parseInt(args[0]);
			} catch (NumberFormatException e) {
				port = 2112;
			}
		} else {
			port = 2112;
		}
		thread.start(port);
		while (runFrames) {
			//This runs the frames
			try {
				synchronized (TechAdventure.lock) {
					for (CombatGroup group : CombatGroup.CombatGroups) {
						group.RunCombat();

					}
				}
			} catch (ConcurrentModificationException e) {
				e.printStackTrace();
			}
		}

	}

}
