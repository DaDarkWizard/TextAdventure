package Shared.world;

import Server.Player;
import Shared.coms.messages.Message;

import java.util.ArrayList;
import java.util.HashMap;

public class Chunk {
    private static final int radius = 2;
    private final Tile[][] tiles = new Tile[10][10];
    private Chunk north, south, east, west;
    private ArrayList<Chunk> checkChunks;

    //Stores the players at their id
    private final HashMap<Integer, Player> players = new HashMap<>();

    public Chunk(Tile[][] tiles) {
        if(tiles.length != 10) {
            throw new IllegalArgumentException("Must be 10 x 10!");
        }

        for (int i = 0; i < 10; i++) {
            if (tiles[i].length != 10) {
                throw new IllegalArgumentException("Must by 10 x 10!");
            }
        }
    }

    /**
     * Spreads a given message to each of the chunks within the radius, including itself
     *
     * @param message the message to spread
     */
    public void spreadMessage(Message message) {
        for(Chunk chunk : checkChunks) {
            chunk.outputMessage(message);
        }
    }

    /**
     * Takes a message and sends it to each of it's players
     *
     * @param message the message to send
     */
    public void outputMessage(Message message) {
        for (Player player : players.values()) {
            player.sendMessage(message);
        }
    }

    /**
     * Calculates what chunks this one should send messages to
     */
    public void calcChunks() {
        int height = 1;
        int width = 1;
        Chunk northWest = this;
        Chunk southEast = this;
        for(int i = 0; i < radius && northWest.getNorth() != null; i++) {
            northWest = northWest.getNorth();
            height++;
        }
        for(int i = 0; i < radius && northWest.getWest() != null; i++) {
            northWest = northWest.getWest();
            width++;
        }
        for(int i = 0; i < radius && southEast.getSouth() != null; i++) {
            southEast = southEast.getSouth();
            height++;
        }
        for(int i = 0; i < radius && southEast.getEast() != null; i++) {
            southEast = southEast.getEast();
            width++;
        }

        ArrayList<Chunk> chunks = new ArrayList<>(width * height);

        for(int i = 0; i < height; i++) {
            southEast = northWest;
            for(int j = 0; j < width; j++) {
                chunks.add(southEast);
                southEast = southEast.getEast();
            }
            northWest = northWest.getSouth();
        }

        this.checkChunks = chunks;
    }

    public Chunk getNorth() {
        return north;
    }

    public void setNorth(Chunk north) {
        this.north = north;
    }

    public Chunk getSouth() {
        return south;
    }

    public void setSouth(Chunk south) {
        this.south = south;
    }

    public Chunk getEast() {
        return east;
    }

    public void setEast(Chunk east) {
        this.east = east;
    }

    public Chunk getWest() {
        return west;
    }

    public void setWest(Chunk west) {
        this.west = west;
    }
}
