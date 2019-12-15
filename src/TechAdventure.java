import Generator.LevelGenerator;
import NPCHandler.NPC;
import PlayerHandler.*;
import CombatHandler.CombatGroup;
import GamePieces.Room;
import PlayerHandler.Persistence.CharacterModificationInputHandler;
import PlayerHandler.UI.CommandInputHandler;
import PlayerHandler.UI.Frame;
import PlayerHandler.UI.StandardFrame;
import World.Spawn;
import World.Tutorial.Tutorial;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Scanner;

/**
 * Main class to run the server
 * <p>
 * Date Last Modified: 12/15/2019
 *
 * @author Daniel Masker, Ben Hodsdon, Emma Smith, Joseph Teahen
 * <p>
 * CS1131, Fall 2019
 * Lab Section 2
 */
public class TechAdventure implements ConnectionListener {

	private AdventureServer adventureServer;                            //The server
	private InputHandler inputHandler = new InputHandler();                //Input handler for normal input
	private CommandInputHandler commandInputHandler = new CommandInputHandler();        //Input handler to check input commands
	private CombatInputHandler combatInputHandler = new CombatInputHandler();        //Handles combat input
	private CharacterModificationInputHandler characterModificationInputHandler;    //Handles character creation/restoration input
	private ArrayList<Room> rooms = new ArrayList<>();        //For when we need to check the rooms
	private static Room spawn;                                //The spawn to hold all initialized players
	private Room levelStart;                                //Start of a random dungeon
	private Room levelEnd;                                    //End of a random dungeon
	private static boolean runFrames = true;                //True if frames thread should run
	public static final Object lock = 0;                    //Lock for multithreading sync

	/**
	 * Constructor for the server.
	 * Starts the server and sets this up to handle input
	 */
	public TechAdventure() {
		adventureServer = new AdventureServer();
		adventureServer.setOnTransmission(this);
	}

	/**
	 * Starts the server with the given port
	 *
	 * @param port the port to use
	 */
	public void start(int port) {
		spawn = new Spawn();
		characterModificationInputHandler = new CharacterModificationInputHandler(spawn);
		rooms.add(spawn);
		inputHandler.setMessageListener(e -> {
			try {
				synchronized (TechAdventure.lock) {
					Frame frame = e.getClient().getLastFrame();
					frame.addLine(e.getMessage(), true);
					adventureServer.sendMessage(e.getClient().getConnectionID(), frame.getOutput());
				}
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

		LevelGenerator generator = new LevelGenerator(10, 9);    //Generate a level
		levelEnd = generator.getEnd();
		levelStart = generator.getStart();
		adventureServer.startServer(port);
	}

	/**
	 * Handles new connections, input, and terminations
	 *
	 * @param e the ConnectionEvent
	 */
	@Override
	public void handle(ConnectionEvent e) {
		//Sync the threads
		synchronized (TechAdventure.lock) {
			try {
				Player player;            //The player with the connection
				switch (e.getCode()) {
					case CONNECTION_ESTABLISHED:
						// Make a new player and get them set up in the tutorial
						player = new Player(e.getConnectionID());
						player.setInfoEventListener(event -> {
							try {
								synchronized (TechAdventure.lock) {
									Frame output = player.getLastFrame();
									if (output == null || output.isEmpty()) {
										output = new StandardFrame();
									}
									output.newLine();
									output.addLine(event.getMessage(), true);
									player.setLastFrame(output);
									adventureServer.sendMessage(event.getSource().getConnectionID(), output.getOutput());
								}
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
								adventureServer.sendMessage(event.getSource().getConnectionID(), output.getOutput());
							} catch (NullPointerException | UnknownConnectionException ex) {
								ex.printStackTrace();
							}
						});
						//Start them at the tutorial
						player.setLocation(new Tutorial().getStart());
						player.setStartRoom(player.getLocation());

						//Update their frame
						Frame output = new StandardFrame();
						output.add(player.getLocation().getDescription());
						player.setLastFrame(output);
						adventureServer.sendMessage(player.getConnectionID(), output.getOutput());

						//Set their username and state
						player.setUsername("Noob");
						player.setState(PlayerStates.tutorial);

						break;
					case TRANSMISSION_RECEIVED:
						//Find the player
						player = Player.findClient(e.getConnectionID());

						//Check for system command
						Commands systemCommand = commandInputHandler.handleInput(e.getData());

						//Handle system command
						if (systemCommand != null) {
							//Parse input nicely for command
							Scanner scanner = new Scanner(e.getData());
							ArrayList<String> args = new ArrayList<>();
							while (scanner.hasNext()) {
								args.add(scanner.next());
							}
							String[] argsArray = new String[args.size()];
							for (int i = 0; i < args.size(); i++) {
								argsArray[i] = args.get(i);
							}
							//Send command to be handles
							handleServerCommands(player, systemCommand, argsArray);
							break;
						}

						//Normal input handler for tutorial or normal modes
						if (player.getState() == PlayerStates.normal ||
								player.getState() == PlayerStates.tutorial) {
							//Get output from handler
							Frame message = inputHandler.handleInput(e.getData(), player);
							//Send to server if it's not empty
							if (message != null && !message.isEmpty()) {
								adventureServer.sendMessage(e.getConnectionID(), message.getOutput());
							}

							//Combat input handler for combat mode also handles output
						} else if (player.getState() == PlayerStates.combat) {
							combatInputHandler.handleInput(e.getData(), player);

							//Handles input for restoration/creation
						} else if (player.getState() == PlayerStates.characterRestoration ||
								player.getState() == PlayerStates.characterCreation) {
							Frame message = characterModificationInputHandler.handleInput(e.getData(), player);
							adventureServer.sendMessage(e.getConnectionID(), message.getOutput());
						}
						break;
					case CONNECTION_TERMINATED:
						// Clear the player from existence
						player = Player.findClient(e.getConnectionID());
						if (player != null && player.getLocation() != null) {
							player.getLocation().removePlayer(player);
							System.out.println("Connection terminated");
						}
						break;
					default:

				}
			} catch (UnknownConnectionException unknownConnectionException) {
				unknownConnectionException.printStackTrace();
			}
		}
	}

	/**
	 * Handler for server commands (the big ones)
	 *
	 * @param player        Player using the command
	 * @param command        Command being used
	 * @param args            Additional args command may have
	 *
	 * @throws UnknownConnectionException If for some reason the connection brakes in .1 seconds
	 */
	private void handleServerCommands(Player player, Commands command, String[] args) throws UnknownConnectionException {
		switch (command) {
			case SHUTDOWN:
				//Can't let just anyone use this one...
				if (player.isAdmin()) {
					player.setLastCommand(Commands.SHUTDOWN);
					broadcastMessage("SHUTTING DOWN...");
					runFrames = false;
					adventureServer.stopServer();
				}
				break;
			case SERVERMESSAGE:
				//Sends a message to the server
				player.setLastCommand(Commands.SERVERMESSAGE);
				String message = player.getUsername() + " SERVER MESSAGE: " + args[0];
				broadcastMessage(message);
				break;
			case IPADDRESS:
				//Gets the server's IP address
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
				//Handles player quitting
				player = Player.findClient(player.getConnectionID());
				if (player != null) {
					player.getLocation().removePlayer(player);
					Player.players.remove(player);
				}
				break;
		}

	}

	/**
	 * Broadcasts a message to all players
	 *
	 * @param message the message to send
	 *
	 * @throws UnknownConnectionException If the player can't be sent a message
	 */
	private void broadcastMessage(String message) throws UnknownConnectionException {
		for (Player player : Player.getPlayers()) {
			adventureServer.sendMessage(player.getConnectionID(), message);
		}
	}

	/**
	 * Thread to hold server
	 */
	private static class RunFrame extends Thread {
		int port; //Port the server is running on

		/**
		 * Initializes the thread
		 *
		 * @param port port to use
		 */
		void start(int port) {
			this.port = port;
			start();
		}

		/**
		 * Runs once, but contains a loop
		 */
		public void run() {
			TechAdventure techAdventure = new TechAdventure();
			techAdventure.start(port);
		}
	}

	/**
	 * The main loop of the program
	 *
	 * @param args port to use
	 */
	public static void main(String[] args) {
		RunFrame thread = new RunFrame(); //Make thread for server
		int port;                          //Port to use

		//Check if port is given, otherwise default
		if (args.length > 0) {
			try {
				port = Integer.parseInt(args[0]);
			} catch (NumberFormatException e) {
				port = 2112;
			}
		} else {
			port = 2112;
		}

		//Start server
		thread.start(port);

		while (runFrames) {
			//This runs the frames
			try {
				//Sync threads
				synchronized (TechAdventure.lock) {
					//Run all active combat
					for (CombatGroup group : CombatGroup.CombatGroups) {
						group.RunCombat();

					}
					//Run all NPC AI
					for (NPC npc : NPC.npcs) {
						if (npc.getRoom() != null) {
							npc.run();
						}
					}
				}
			} catch (ConcurrentModificationException e) {
				e.printStackTrace();
			}
		}

	}

}
