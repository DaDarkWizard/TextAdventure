package GamePieces;

import NPCHandler.NPC;
import CombatHandler.Combatant;
import PlayerHandler.Commands;
import PlayerHandler.Player;

import java.util.ArrayList;

/**
 * This is the room class for the Text Adventure
 * Provides a space for the Player to move and interact with
 * <p>
 * Date Last Modified: 12/9/2019
 *
 * @author Daniel Masker, Joe Teahen, Emma Smith, Ben Hodsdon
 * <p>
 * CS 1131, Fall 2019
 * Lab Section 2
 */
public class Room {
    private Room north, south, east, west;                              //North, South, East, and West Roooms
    private String description;                                         //Description of this Room
    private String name;                                                //Name of this Room
    private String lookDescription;                                     //'look' description of this Room
    private ArrayList<Player> players = new ArrayList<>();              //All players in this Room
    private ArrayList<Interactable> interactables = new ArrayList<>();  //All Interactable objects in this Room
    private ArrayList<NPC> npcs = new ArrayList<>();                    //All NPCs in this Room

    /**
     * Create a room with the given name, description, and lookDescription
     *
     * @param name the name of the room to be displayed
     * @param description the description of the room (From within)
     * @param lookDescription the 'look' description of the room (From afar)
     */
    public Room(String name, String description, String lookDescription) {
        this.name = name;
        this.description = description;
        this.lookDescription = lookDescription;
    }

    /**
     * Get all of the interactable objects in the room
     *
     * @return ArrayList of all Interactable objects
     */
    public ArrayList<Interactable> getInteractables() {
        return this.interactables;
    }

    /**
     * Get the room to the north of this one
     *
     * @return the Room to the north
     */
    public Room getNorth() {
        return north;
    }

    /**
     * Set the room to the north of this one
     *
     * @param north the Room to the north
     */
    public void setNorth(Room north) {
        this.north = north;
    }

    /**
     * Get the room south of this one
     *
     * @return
     */
    public Room getSouth() {
        return south;
    }

    /**
     * Set the room south of this one
     *
     * @param south the Room to the south
     */
    public void setSouth(Room south) {
        this.south = south;
    }

    /**
     * Get the room east of this one
     *
     * @return the Room to the east
     */
    public Room getEast() {
        return east;
    }

    /**
     * Set the room east of this one
     *
     * @param east the Room to the east
     */
    public void setEast(Room east) {
        this.east = east;
    }

    /**
     * Get the room west to this one
     *
     * @return the west Room
     */
    public Room getWest() {
        return west;
    }

    /**
     * Set the room to the west of this one
     *
     * @param west room to the west
     */
    public void setWest(Room west) {
        this.west = west;
    }

    /**
     * Gets the description of the room for the player
     *
     * @return description of the room
     */
    public String getDescription() {
        StringBuilder output = new StringBuilder();

        //Get name
        output.append(this.name).append("\n\n");

        //Get written description
        output.append(description);

        //Add all interactables in the room
        if (interactables.size() > 0) {
            output.append("\n");
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
            output.append("\n");
        }
        output.append("\n");

        //List all combatants in the room
        if (getCombatants().size() < 2) {
            output.append("You are the only one in the room.");
        }
        if (getCombatants().size() == 2) {
            output.append("There is one other person in the room.");
        }
        if (getCombatants().size() > 2) {
            output.append(String.format("There are %d people in the room besides you.", getCombatants().size() - 1));
        }

        //List all exits of the room
        int count = 0;
        if (west != null) {
            count++;
        }
        if (east != null) {
            count++;
        }
        if (north != null) {
            count++;
        }
        if (south != null) {
            count++;
        }

        if (count > 1) {
            output.append("\n\n");
        }

        if (count == 1) {
            output.append("\n\n");
            output.append("There is a room to the ");
            if (east != null) {
                output.append("east");
            } else if (west != null) {
                output.append("west");
            } else if (north != null) {
                output.append("north");
            } else if (south != null) {
                output.append("south");
            }
            output.append(".");
        } else if (count > 1) {
            output.append("There are rooms to the");
            if (north != null) {
                output.append(" north");
                count--;
                if (count == 1) {
                    output.append(" and");
                } else {
                    output.append(",");
                }
            }

            if (south != null) {
                output.append(" south");
                count--;
                if (count == 1) {
                    output.append(" and");
                } else if (count == 0) {
                    output.append(".");
                } else {
                    output.append(",");
                }
            }

            if (east != null && count > 0) {
                output.append(" east");
                count--;
                if (count == 1) {
                    output.append(" and");
                } else if (count == 0) {
                    output.append(".");
                } else {
                    output.append(",");
                }
            }

            if (west != null) {
                output.append(" west.");
            }
        }

        //Add spacing for looks
        output.append("\n\n");

        //Return the completed description
        return output.toString();
    }

    /**
     * Finds the adjoining room in the given command direction
     *
     * @param command direction command to loo
     *
     * @return room in that direction, null otherwise
     */
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

    /**
     * Set a new description for the room
     *
     * @param description the room's new description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get the name of the room
     *
     * @return the room's name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the room
     *
     * @param name the room's new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the 'look' description of the room
     *
     * @return the 'look' description
     */
    public String getLookDescription() {
        return this.lookDescription;
    }

    /**
     * Sets the 'look' description of the room (for peeking)
     *
     * @param lookDescription new description to set
     */
    public void setLookDescription(String lookDescription) {
        this.lookDescription = lookDescription;
    }

    /**
     * Gets all players in the room
     *
     * @return ArrayList of all players
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * Gets all combatants in the room
     *
     * @return ArrayList of all combatants
     */
    public ArrayList<Combatant> getCombatants() {
        ArrayList<Combatant> output = new ArrayList<>();
        output.addAll(players);
        output.addAll(npcs);
        return output;
    }

    /**
     * Gets all NPCs in the room
     *
     * @return ArrayList of all NPCs
     */
    public ArrayList<NPC> getNpcs() {
        return npcs;
    }

    /**
     * Removes the specified player from the room
     *
     * @param player player to remove
     */
    public void removePlayer(Player player) {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i) == player) {
                players.remove(player);
                i--;
            }
        }
    }
}
