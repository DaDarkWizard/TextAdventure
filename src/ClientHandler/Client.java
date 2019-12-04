package ClientHandler;

import ClientHandler.GamePieces.Room;

import java.util.ArrayList;

public class Client {
    static ArrayList<Client> clients = new ArrayList<>();

    private long connectionID;
    private Commands lastCommand;
    private ClientStates state = ClientStates.initializing;
    private boolean admin = false;
    private String username;
    private String password;
    private Room location;

    public static ArrayList<Client> getClients() {
        return clients;
    }

    public void setConnectionID(long connectionID) {
        this.connectionID = connectionID;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Client(long connectionID) {
        this.connectionID = connectionID;
        clients.add(this);
    }

    public void setState(ClientStates newState) {
        this.state = newState;
    }

    public ClientStates getState() {
        return this.state;
    }

    public Commands getLastCommand() {
        return lastCommand;
    }

    public void setLastCommand(Commands lastCommand) {
        this.lastCommand = lastCommand;
    }

    public long getConnectionID() {
        return connectionID;
    }

    public Room getLocation() {
        return location;
    }

    public void setLocation(Room location) {
        this.location = location;
    }

    public static Client findClient(long connectionID) {
        for (Client client : clients) {
            if (client.connectionID == connectionID) {
                return client;
            }
        }
        throw new ClientNotFoundException();
    }

    static class ClientNotFoundException extends RuntimeException {
    }
}
