package PlayerHandler;

import PlayerHandler.GamePieces.Holdable;
import PlayerHandler.GamePieces.Room;

import java.util.ArrayList;

public class Player {
    static ArrayList<Player> players = new ArrayList<>();

    private long connectionID;
    private Commands lastCommand;
    private PlayerStates state = PlayerStates.initializing;
    private boolean admin = false;
    private String username;
    private String password;
    private Room location;
    private boolean online = true;
    private ArrayList<Player> blockedPlayers = new ArrayList<>();
    private ArrayList<Holdable> items = new ArrayList<>();

    public static ArrayList<Player> getPlayers() {
        return players;
    }

    public void setConnectionID(long connectionID) {
        this.connectionID = connectionID;
    }

    public ArrayList<Holdable> getInventory() {
        return this.items;
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

    public Player(long connectionID) {
        this.connectionID = connectionID;
        players.add(this);
    }

    public void setState(PlayerStates newState) {
        this.state = newState;
    }

    public PlayerStates getState() {
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

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public void blockClient(Player player) {
        if (!isBlocked(player)) {
            blockedPlayers.add(player);
        }
    }

    public void unblockClient(Player player) {
        if (isBlocked(player)) {
            blockedPlayers.remove(player);
        }
    }

    public boolean isBlocked(Player player) {
        return (blockedPlayers.indexOf(player) > -1);
    }

    public static Player findClient(long connectionID) {
        for (Player player : players) {
            if (player.connectionID == connectionID) {
                return player;
            }
        }
        throw new ClientNotFoundException();
    }

    static class ClientNotFoundException extends RuntimeException {
    }
}
