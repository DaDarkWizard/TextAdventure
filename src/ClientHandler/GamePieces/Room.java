package ClientHandler.GamePieces;

import ClientHandler.Client;

import java.util.ArrayList;

public class Room {
    private Room north, south, east, west;
    private String description;
    private String name;
    private String lookDescription;
    private ArrayList<Client> clients;

    public Room(String name) {
        this(name, null, null);
    }

    public Room(String name, String description, String lookDescription) {
        this.name = name;
        this.description = description;
        this.lookDescription = lookDescription;
    }

    public Room getNorth() {
        return north;
    }

    public void setNorth(Room north) {
        this.north = north;
    }

    public Room getSouth() {
        return south;
    }

    public void setSouth(Room south) {
        this.south = south;
    }

    public Room getEast() {
        return east;
    }

    public void setEast(Room east) {
        this.east = east;
    }

    public Room getWest() {
        return west;
    }

    public void setWest(Room west) {
        this.west = west;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLookDescription() {
        return lookDescription;
    }


    public void setLookDescription(String lookDescription) {
        this.lookDescription = lookDescription;
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public void setClients(ArrayList<Client> clients) {
        this.clients = clients;
    }
}
