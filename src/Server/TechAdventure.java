package Server;


import java.util.ConcurrentModificationException;

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

    private AdventureServer adventureServer;                                         //End of a random dungeon
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
        adventureServer.startServer(port);
    }

    /**
     * Handles new connections, input, and terminations
     *
     * @param e the Server.ConnectionEvent
     */
    @Override
    public void handle(ConnectionEvent e) {
        //Sync the threads
        synchronized (TechAdventure.lock) {
            //The player with the connection
            switch (e.getCode()) {
                case CONNECTION_ESTABLISHED:
                    // Make a new player and get them set up in the tutorial


                    break;
                case TRANSMISSION_RECEIVED:
                    //Find the player
                    //Handle system command

                    break;
                case CONNECTION_TERMINATED:
                    break;
                default:

            }
        }
    }

    /**
     * Handler for server commands (the big ones)
     *
     * @param player  Player using the command
     * @param command Command being used
     * @param args    Additional args command may have
     * @throws UnknownConnectionException If for some reason the connection brakes in .1 seconds
     */

    /**
     * Broadcasts a message to all players
     *
     * @param message the message to send
     * @throws UnknownConnectionException If the player can't be sent a message
     */


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

                }
            } catch (ConcurrentModificationException e) {
                e.printStackTrace();
            }
        }

    }

}
