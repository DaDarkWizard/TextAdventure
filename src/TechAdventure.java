import ClientHandler.Client;
import ClientHandler.ClientStates;
import ClientHandler.Commands;
import ClientHandler.InputHandler;

import java.util.Arrays;

/*
 * This is an example of how to run the adventure server.
 * Your game class parses and responds to input by handling ConnectionEvents.
 */
public class TechAdventure implements ConnectionListener {
    AdventureServer adventureServer = null;
    InputHandler inputHandler = new InputHandler();
    public String[] serverCommands = {"SHUTDOWN", "IPADDRESS", "SERVERMESSAGE"};

    public TechAdventure() {
        adventureServer = new AdventureServer();
        adventureServer.setOnTransmission(this);
    }

    public void start(int port) {
        adventureServer.startServer(port);
    }

    @Override
    public void handle(ConnectionEvent e) {
        System.out.println("EVENT RECEIVED - YOU MUST PARSE THE DATA AND RESPOND APPROPRIATELY");
        System.out.println(String.format("connectionId=%d, data=%s", e.getConnectionID(), e.getData()));
        try {
            switch (e.getCode()) {
                case CONNECTION_ESTABLISHED:
                    // What do you do when the connection is established?
                    new Client(e.getConnectionID());
                    break;
                case TRANSMISSION_RECEIVED:
                    adventureServer.sendMessage(e.getConnectionID(), String.format(
                            "MESSAGE RECEIVED: connectionId=%d, data=%s", e.getConnectionID(), e.getData()));
                    Client client = Client.findClient(e.getConnectionID());

                    if (client.getState() == ClientStates.normal) {

                        //Handle server commands
                        if (Arrays.binarySearch(serverCommands, e.getData()) > -1) {
                            if (!client.isAdmin()) {
                                adventureServer.sendMessage(client.getConnectionID(),
                                        "You must be an admin to use this command.");
                            } else {
                                handleServerCommands(client, e.getData());
                            }
                            break;
                        }

                        adventureServer.sendMessage(e.getConnectionID(), inputHandler.handleInput(e.getData()));
                    } else if (client.getState() == ClientStates.sendingServerMessage) {
                        broadcastMessage(e.getData());
                        break;
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

    private void handleServerCommands(Client client, String message) throws UnknownConnectionException {
        switch (message) {
            case "SHUTDOWN":
                client.setLastCommand(Commands.SHUTDOWN);
                for (Client client1 : Client.getClients()) {
                    adventureServer.sendMessage(client1.getConnectionID(), "SHUTTING DOWN...");
                }
                adventureServer.stopServer();
                break;
            case "SERVERMESSAGE":
                client.setLastCommand(Commands.PRESERVERMESSAGE);
                break;
        }
    }

    private void broadcastMessage(String message) throws UnknownConnectionException {
        for (Client client : Client.getClients()) {
            adventureServer.sendMessage(client.getConnectionID(), message);
        }
    }

    public static void main(String[] args) {
        TechAdventure techAdventure = new TechAdventure();
        techAdventure.start(2112);
    }

}
