package Server.PlayerHandler;

import Server.CombatHandler.AttackCommands;
import Server.CombatHandler.CombatGroup;
import Server.CombatHandler.Combatant;
import Server.CombatHandler.Weapons.StatHandler;
import Server.GamePieces.Holdable;
import Server.GamePieces.Room;
import Server.CombatHandler.Weapons.Weapon;
import Server.PlayerHandler.UI.Frame;
import Server.PlayerHandler.UI.StandardFrame;
import Server.PlayerHandler.UI.UpdateListener;

import java.util.ArrayList;

/**
 * This class makes a player
 * <p>
 * Date Last Modified: 12/14/2019
 *
 * @author Daniel Masker, Ben Hodsdon, Joe Teahen, Emma Smith
 * <p>
 * CS 1131, Fall 2019
 * Lab Section 2
 */
public class Player implements Combatant {
    public static ArrayList<Player> players = new ArrayList<>(); //arraylist of players

    //General stuff
    private boolean disconnected = false;
    private long connectionID; //connection ID
    private Commands lastCommand; //a player's last command
    private PlayerStates state = PlayerStates.initializing; //A player's state
    private boolean admin = false; //admin class or not
    private String username; //username
    private String password; //passoword
    private Room location; //location
    private Room lastLocation; //last location
    private boolean online = true; //if they are online or not
    private ArrayList<Player> blockedPlayers = new ArrayList<>(); //blocked players
    private ArrayList<Holdable> items = new ArrayList<>(); //items the player holds
    private InfoListener infoEventListener; //infoEventListener
    private UpdateListener updateEventListener; //updateEventListener
    private Frame lastFrame; //last frame for the player

    //For combat
    private ArrayList<Weapon> equipped = new ArrayList<>(); //what is equipped for the player
    private int hitpoints; //a player's hit points
    private int maxHitpoints; //a player's maxHitpoints
    private int brawn, spiffness, smarts, moxy; //a player's stats
    private Combatant target; //a player's target in combat
    private CombatGroup combatGroup; //a combat group with the player in it
    private ArrayList<String> words = new ArrayList<>(); //words that a player typed in combat
    private int pendingDamage; //pending damage for the player
    private int pendingHeal; //pending heal for the player
    private int pendingBlock; //pending block for the player
    private CombatGroup.rpsChoice rpsChoice; //RPS choice
    private Room startRoom;

    /**
     * Player Constructor
     *
     * @param connectionID a connection ID unique to each player
     */
    public Player(long connectionID) {
        this.connectionID = connectionID;
        players.add(this);

        this.hitpoints = 20;
        this.maxHitpoints = 20;
        this.brawn = 10;
        this.spiffness = 10;
        this.smarts = 10;
        this.moxy = 10;
        calculateMaxHitpoints();
    }

    /**
     * Sets whether the player has disconnected or not
     *
     * @param disconnected whether disconnected
     */
    public void setDisconnected(boolean disconnected) {
        this.disconnected = disconnected;
    }

    /**
     * Tells whether the player has disconnected or not
     *
     * @return whether disconnected
     */
    public boolean isDisconnected() {
        return this.disconnected;
    }

    /**
     * Respawns the player on death
     */
    public void respawn() {
        this.hitpoints = maxHitpoints;
        this.setLocation(startRoom);
        this.target = null;
        this.combatGroup = null;
    }

    /**
     * Gets the room the player goes to when they die
     *
     * @return the Room
     */
    public Room getStartRoom() {
        return startRoom;
    }

    /**
     * Sets the Room the player goes to when they die
     *
     * @param startRoom the new Room
     */
    public void setStartRoom(Room startRoom) {
        this.startRoom = startRoom;
    }

    /**
     * Calculates Max Hit Points for the player
     */
    public void calculateMaxHitpoints() {
        int newMax = (2 * moxy) + (2 * brawn) + 10;
        int diff = newMax - this.maxHitpoints;
        this.maxHitpoints = newMax;
        this.hitpoints += diff;
    }

    /**
     * Gets the possible attack commands for a player
     *
     * @return the ArrayList of commands a player can use
     */
    public ArrayList<AttackCommands> getPossibleAttackCommands() {
        ArrayList<AttackCommands> commands = new ArrayList<>();
        commands.add(AttackCommands.hit);
        for (Weapon weapon : this.equipped) {
            commands.add(weapon.getAttackCommand());
        }

        return commands;
    }

    /**
     * Sets the update event listener for the player
     *
     * @param listener the update listener
     */
    public void setUpdateEventListener(UpdateListener listener) {
        this.updateEventListener = listener;
    }

    /**
     * Updates the player
     */
    public void update() {
        if (updateEventListener != null) {
            updateEventListener.handle(new UpdateEvent(this));
        }
    }

    /**
     * Gets an Array list of players
     *
     * @return Array list of the player
     */
    // Todo update method to return a copy of the ArrayList
    public static ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * Sets the connection ID of the player
     *
     * @param connectionID
     */
    public void setConnectionID(long connectionID) {
        this.connectionID = connectionID;
    }

    /**
     * Gets the Equipped weapons of a player
     *
     * @return the Equipped Weapons of the player
     */
    // Todo update method to return a copy of the ArrayList
    public ArrayList<Weapon> getEquipped() {
        return this.equipped;
    }

    /**
     * Gets the Inventory of a player
     *
     * @return the inventory
     */
    // Todo update method to return a copy of the ArrayList
    public ArrayList<Holdable> getInventory() {
        return this.items;
    }

    /**
     * Checks to see if the player is the Admin
     *
     * @return true if the admin
     */
    public boolean isAdmin() {
        return admin;
    }

    /**
     * Sets a player as an admin
     *
     * @param admin if it is an admin or not
     */
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    /**
     * Gets the username of the player
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the player
     *
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password for the player
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the player
     *
     * @param password the password to be set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Sets the player's state
     *
     * @param newState the state to be set
     */
    public void setState(PlayerStates newState) {
        this.state = newState;
    }

    /**
     * Gets the player's state
     *
     * @return the player's state
     */
    public PlayerStates getState() {
        return this.state;
    }

    /**
     * Gets the last command of the player
     *
     * @return
     */
    public Commands getLastCommand() {
        return lastCommand;
    }

    /**
     * sets the last command of the player
     *
     * @param lastCommand
     */
    public void setLastCommand(Commands lastCommand) {
        this.lastCommand = lastCommand;
    }

    /**
     * Gets the connection ID of the player
     *
     * @return the connection ID
     */
    public long getConnectionID() {
        return connectionID;
    }

    /**
     * Gets the location of the Player
     *
     * @return the location by room
     */
    public Room getLocation() {
        return location;
    }

    /**
     * Sets the location of the player
     *
     * @param location the location of the player
     */
    public void setLocation(Room location) {
        if (this.location != null) {
            this.location.removePlayer(this);
        }
        this.lastLocation = this.location;
        this.location = location;

        this.location.addPlayer(this);
        StandardFrame frame = new StandardFrame();
        frame.add(location.getDescription());
        this.setLastFrame(frame);
        this.location.updateNPCs();
    }

    /**
     * Gets the last location of teh player
     *
     * @return the last location of the player
     */
    public Room getLastLocation() {
        return this.lastLocation;
    }

    /**
     * Checks to see if they player is online
     *
     * @return true it they are online
     */
    public boolean isOnline() {
        return online;
    }

    /**
     * Sets a player to be online or not
     *
     * @param online online or not
     */
    public void setOnline(boolean online) {
        this.online = online;
    }

    /**
     * Blocks a player
     *
     * @param player the player to block
     */
    public void blockClient(Player player) {
        if (!isBlocked(player)) {
            blockedPlayers.add(player);
        }
    }

    /**
     * Unblocks a player
     *
     * @param player the player to be unblocked
     */
    public void unblockClient(Player player) {
        if (isBlocked(player)) {
            blockedPlayers.remove(player);
        }
    }

    /**
     * Sets the Info Event Listener
     *
     * @param listener the listener
     */
    public void setInfoEventListener(InfoListener listener) {
        this.infoEventListener = listener;
    }

    /**
     * Sends a message to this player
     *
     * @param message the message to send
     */
    public void sendMessage(String message) {
        InfoEvent event = new InfoEvent(this, message);
        if (this.infoEventListener != null) {
            this.infoEventListener.handle(event);
        }
    }

    /**
     * Checks to see if a player is blocked
     *
     * @param player the player to check
     * @return if the player is blocked or not
     */
    public boolean isBlocked(Player player) {
        return (blockedPlayers.indexOf(player) > -1);
    }

    /**
     * Finds the client and returns the player
     *
     * @param connectionID the connection idea of the client
     * @return the player
     */
    public static Player findClient(long connectionID) {
        for (Player player : players) {
            if (player.connectionID == connectionID) {
                return player;
            }
        }
        return null;
    }

    //Combatant stuff

    /**
     * Changes the hit points of the player
     *
     * @param amount the amount of the player
     * @return the int of hit points left
     */
    @Override
    public int modifyHitpoints(int amount) {
        hitpoints += amount;
        if (hitpoints <= 0) {
            hitpoints = 0;
        } else if (hitpoints >= maxHitpoints) {
            hitpoints = maxHitpoints;
        }

        return hitpoints;
    }

    /**
     * Gets the hit points of the player
     *
     * @return the hit points
     */
    @Override
    public int getHitPoints() {
        return hitpoints;
    }

    /**
     * Sets the max hit points of the player
     *
     * @param amount the amount to set it to
     */
    @Override
    public void setMaxHitpoints(int amount) {
        this.maxHitpoints = amount;
    }

    /**
     * Gets the max hit points of the Player
     *
     * @return the max hit points
     */
    @Override
    public int getMaxHitpoints() {
        return this.maxHitpoints;
    }

    /**
     * Gets the player's brawn
     *
     * @return brawn
     */
    @Override
    public int getBrawn() {
        return brawn;
    }

    /**
     * Sets the player's brawn
     *
     * @param amount the amount to set it to
     */
    @Override
    public void setBrawn(int amount) {
        this.brawn = amount;
    }

    /**
     * Gets the player spiffness
     *
     * @return spiffness
     */
    @Override
    public int getSpiffness() {
        return spiffness;
    }

    /**
     * Sets the player's spiffness
     *
     * @param amount the amount to set it to
     */
    @Override
    public void setSpiffness(int amount) {
        this.spiffness = amount;
    }

    /**
     * Gets the player's smerts
     *
     * @return smerts
     */
    @Override
    public int getSmarts() {
        return smarts;
    }

    /**
     * Sets the player's smerts
     *
     * @param amount the amount to change it to
     */
    @Override
    public void setSmarts(int amount) {
        this.smarts = amount;
    }

    /**
     * Gets the player's moxy
     *
     * @return moxy
     */
    @Override
    public int getMoxy() {
        return moxy;
    }

    /**
     * Sets the player's moxy
     *
     * @param amount the amount to set it to
     */
    @Override
    public void setMoxy(int amount) {
        this.moxy = amount;
    }

    /**
     * Sets the pending block to the player
     *
     * @param amount the amount to set it to
     */
    @Override
    public void setPendingBlock(int amount) {
        pendingBlock = amount;
    }

    /**
     * Gets the pending block to the player
     *
     * @return the pending block
     */
    @Override
    public int getPendingBlock() {
        return pendingBlock;
    }

    /**
     * Sets the pending damage to the player
     *
     * @param amount the amount to set it to
     */
    @Override
    public void setPendingDamage(int amount) {
        pendingDamage = amount;
    }

    /**
     * Gets the pending damage to the player
     *
     * @return the pending damage
     */
    @Override
    public int getPendingDamage() {
        return pendingDamage;
    }

    /**
     * Sets the pending heal to the player
     *
     * @param amount the amount to set it to
     */
    @Override
    public void setPendingHeal(int amount) {
        pendingHeal = amount;
    }

    /**
     * Gets the pending heal to the player
     *
     * @return the pending heal
     */
    @Override
    public int getPendingHeal() {
        return pendingHeal;
    }

    /**
     * Gets the player's target
     *
     * @return the player target
     */
    @Override
    public Combatant getTarget() {
        return this.target;
    }

    /**
     * Sets the player's target
     *
     * @param target the target
     */
    @Override
    public void setTarget(Combatant target) {
        this.target = target;
    }

    /**
     * Sets the player's combatGroup
     *
     * @param combatGroup the combatGroup
     */
    @Override
    public void setCombatGroup(CombatGroup combatGroup) {
        this.combatGroup = combatGroup;
    }

    /**
     * Gets the player's CombatGroup
     *
     * @return the player's CombatGroup
     */
    @Override
    public CombatGroup getCombatGroup() {
        return this.combatGroup;
    }

    /**
     * Get's the player's name
     *
     * @return the player's name
     */
    @Override
    public String getName() {
        return this.username;
    }

    /**
     * Gets the words used by the player in combat
     *
     * @return the words
     */
    // Todo have method return a copy of the ArrayList
    @Override
    public ArrayList<String> getWords() {
        return this.words;
    }

    /**
     * Gets the weapons of a player
     *
     * @return weapons
     */
    //Todo have method return a copy of the ArrayList
    @Override
    public ArrayList<Weapon> getWeapons() {
        return this.equipped;
    }

    /**
     * Gets the combat decision of the player
     *
     * @return the combat decision
     */
    @Override
    public CombatGroup.rpsChoice getCombatDecision() {
        return this.rpsChoice;
    }

    /**
     * Sets the combat decision of the player.
     *
     * @param decision
     */
    @Override
    public void setCombatDecision(CombatGroup.rpsChoice decision) {
        this.rpsChoice = decision;
    }

    /**
     * Gets the player's defensive stat given the attack stat
     *
     * @param stat the stat attacking
     * @return the amount of defense
     */
    @Override
    public int getStatByReference(StatHandler.Stats stat) {
        if (stat == StatHandler.Stats.moxy) {
            return smarts;
        } else {
            return spiffness;
        }
    }

    /**
     * If a player is Unconscious
     *
     * @return if they are unconscious or not
     */
    @Override
    public boolean isUnconscious() {
        return this.getHitPoints() <= 0;
    }

    //This is needed
    private static class ClientNotFoundException extends RuntimeException {
    }

    /**
     * Gets the last frame the player saw
     *
     * @return the last frame the player saw
     */
    public Frame getLastFrame() {
        return lastFrame;
    }

    /**
     * Sets the last frame the player saw
     *
     * @param frame the last frame
     */
    public void setLastFrame(Frame frame) {
        this.lastFrame = frame;
    }

    /**
     * Removes a player from players
     *
     * @param player
     */
    public static void removePlayer(Player player) {
        players.remove(player);
    }
}
