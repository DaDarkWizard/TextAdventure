package PlayerHandler.GamePieces;

import PlayerHandler.CombatHandler.Combatant;
import PlayerHandler.Commands;
import PlayerHandler.Player;
import PlayerHandler.UI.Frame;

import java.util.ArrayList;

public class Room {
    private Room north, south, east, west;
    private String description;
    private String name;
    private String lookDescription;
    private ArrayList<Player> players = new ArrayList<>();
    private ArrayList<Interactable> interactables = new ArrayList<>();

    public Room(String name) {
        this(name, null, null);
    }

    public Room(String name, String description, String lookDescription) {
        this.name = name;
        this.description = description;
        this.lookDescription = lookDescription;
    }

    public ArrayList<Interactable> getInteractables() {
        return this.interactables;
    }

    ;

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

    public Frame getDescription(Player player, Frame frame) {
        StringBuilder output = new StringBuilder();
        frame.addParagraph(this.name);
        System.out.println(frame.addParagraph(description));

        if (interactables.size() > 0) {
            frame.newLine();
            output = new StringBuilder();
            output.append("There is a ").append(interactables.get(0).getShortDescription());
            if (interactables.size() == 1) {
                output.append(".");
            } else if (interactables.size() == 2) {
                output.append(" and a ");
            } else {
                for (int i = 1; i < interactables.size(); i++) {
                    if (i < interactables.size() - 1) {
                        output.append(", a ").append(interactables.get(i).getShortDescription());
                    } else {
                        output.append(", and a ").append(interactables.get(i).getShortDescription()).append(".");
                    }
                }
            }
            frame.addLine(output.toString());
        }
        frame.newLine();
        output = new StringBuilder();
        if (players.size() < 2) {
            output.append("You are the only one in the room.");
        }
        if (players.size() == 2) {
            output.append("There is one other person in the room.");
        }
        if (players.size() > 2) {
            output.append(String.format("There are %d people in the room besides you.", players.size() - 1));
        }
        frame.addLine(output.toString());
        return frame;
    }

    public Room getRoomFromCommand(Commands command) {
        switch (command) {
            case north:
                return this.north;
            case south:
                return this.south;
            case west:
                return this.west;
            case east:
                return this.east;
        }
        return null;
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

    public Frame getLookDescription(Player player, Frame frame) {
        frame.addParagraph(this.lookDescription);
        return frame;
    }


    public void setLookDescription(String lookDescription) {
        this.lookDescription = lookDescription;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public ArrayList<Combatant> getCombatants() {
        ArrayList<Combatant> output = new ArrayList<>();
        for (Player player : getPlayers()) {
            output.add((Combatant) player);
        }
        return output;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }
}
