import PlayerHandler.*;
import CombatHandler.CombatGroup;
import GamePieces.Room;
import PlayerHandler.Persistence.CharacterModificationInputHandler;
import PlayerHandler.UI.CommandInputHandler;
import PlayerHandler.UI.Frame;
import PlayerHandler.UI.StandardFrame;
import World.Spawn;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Scanner;

public class TechAdventure implements ConnectionListener {
	private AdventureServer adventureServer;
	private InputHandler inputHandler = new InputHandler();
	private CommandInputHandler commandInputHandler = new CommandInputHandler();
    private CombatInputHandler combatInputHandler = new CombatInputHandler(this);
	private CharacterModificationInputHandler characterModificationInputHandler = new CharacterModificationInputHandler();
	private ArrayList<Room> rooms = new ArrayList<>();
	private Room startRoom;
	public static Room spawn;

	private static boolean runFrames = true;
	public static final Object lock = 0;

	public TechAdventure() {
		adventureServer = new AdventureServer();
		adventureServer.setOnTransmission(this);
	}

	public void start(int port) {
		Room startRoom = new Tutorial().getStart();
		spawn = new Spawn();
		rooms.add(startRoom);
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
		synchronized (TechAdventure.lock) {
			try {
				Player player;
				switch (e.getCode()) {
					case CONNECTION_ESTABLISHED:
						// What do you do when the connection is established?
						player = new Player(e.getConnectionID());
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
								}
								adventureServer.sendMessage(event.getSource().getConnectionID(), output.getOutput());
							} catch (UnknownConnectionException ex) {
								ex.printStackTrace();
							}
						});
						player.setLocation(startRoom);
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

						Commands systemCommand = commandInputHandler.handleInput(e.getData());
						if (systemCommand != null) {
							Scanner scanner = new Scanner(e.getData());
							ArrayList<String> args = new ArrayList<>();
							while (scanner.hasNext()) {
								args.add(scanner.next());
							}
                            String[] argsArray = new String[args.size()];
                            for (int i = 0; i < args.size(); i++) {
                                argsArray[i] = args.get(i);
                            }
                            handleServerCommands(player, systemCommand, argsArray);
                            break;
						}

						if (player.getState() == PlayerStates.normal) {
							Frame message = inputHandler.handleInput(e.getData(), player);
							if (message != null && !message.isEmpty()) {

								adventureServer.sendMessage(e.getConnectionID(), message.getOutput());
							}
						} else if (player.getState() == PlayerStates.combat) {
							Frame message = combatInputHandler.handleInput(e.getData(), player);

						} else if (player.getState() == PlayerStates.characterRestoration ||
								player.getState() == PlayerStates.characterCreation) {
							Frame message = characterModificationInputHandler.handleInput(e.getData(), player);
							adventureServer.sendMessage(e.getConnectionID(), message.getOutput());
						}
						break;
					case CONNECTION_TERMINATED:
						// Cleanup when the connection is terminated.
						player = Player.findClient(e.getConnectionID());
						if (player != null && player.getLocation() != null) {
							player.getLocation().removePlayer(player);
							System.out.println("Connection terminated");
						}
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
				if (player.isAdmin()) {
					player.setLastCommand(Commands.SHUTDOWN);
					broadcastMessage("SHUTTING DOWN...");
					runFrames = false;
					adventureServer.stopServer();
				}
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
					break;
				}
				break;
			case EXIT:
				player = Player.findClient(player.getConnectionID());
				if (player != null) {
					player.getLocation().removePlayer(player);
					System.out.println("Connection terminated");
					Player.players.remove(player);
				}
				break;
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
