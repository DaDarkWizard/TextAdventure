package PlayerHandler;

import CombatHandler.AttackCommands;
import CombatHandler.CombatGroup;
import CombatHandler.Combatant;
import GamePieces.Holdable;
import GamePieces.Room;
import CombatHandler.Weapons.Weapon;
import PlayerHandler.UI.Frame;
import PlayerHandler.UI.StandardFrame;
import PlayerHandler.UI.UpdateListener;

import java.util.ArrayList;

public class Player implements Combatant {
    public static ArrayList<Player> players = new ArrayList<>();

    //General stuff
    private long connectionID;
    private Commands lastCommand;
    private PlayerStates state = PlayerStates.initializing;
    private boolean admin = false;
    private String username;
    private String password;
    private Room location;
    private Room lastLocation;
    private boolean online = true;
    private ArrayList<Player> blockedPlayers = new ArrayList<>();
    private ArrayList<Holdable> items = new ArrayList<>();
    private InfoListener infoEventListener;
    private UpdateListener updateEventListener;
    private Frame lastFrame;

    //For combat
    private ArrayList<Weapon> equipped = new ArrayList<>();
    private int hitpoints;
    private int maxHitpoints;
    private int brawn, spiffness, smarts, moxy;
    private Combatant target;
    private CombatGroup combatGroup;
    private ArrayList<String> words = new ArrayList<>();
    private int pendingDamage;
    private int pendingHeal;
    private int pendingBlock;
    private CombatGroup.rpsChoice rpsChoice;

    public Player(long connectionID) {
        this.connectionID = connectionID;
        players.add(this);

        this.hitpoints = 20;
        this.maxHitpoints = 20;
        this.brawn = 10;
        this.spiffness = 10;
        this.smarts = 10;
        this.moxy = 10;
    }

    public ArrayList<AttackCommands> getPossibleAttackCommands() {
        ArrayList<AttackCommands> commands = new ArrayList<>();
        commands.add(AttackCommands.hit);
        for (Weapon weapon : this.equipped) {
            commands.add(weapon.getAttackCommand());
        }

        return commands;
    }

    public void setUpdateEventListener(UpdateListener listener) {
        this.updateEventListener = listener;
    }

    public void update() {
        if (updateEventListener != null) {
            updateEventListener.handle(new UpdateEvent(this));
        }
    }

    public static ArrayList<Player> getPlayers() {
        return players;
    }
    public void setConnectionID(long connectionID) {
        this.connectionID = connectionID;
    }

    public ArrayList<Weapon> getEquipped() {
        return this.equipped;
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
        if (this.location != null) {
            this.location.removePlayer(this);
        }
        this.lastLocation = location;
        this.location = location;
        StandardFrame frame = new StandardFrame();
        frame.add(location.getDescription());
        this.setLastFrame(frame);
        this.location.addPlayer(this);
    }

    public Room getLastLocation() {
        return this.lastLocation;
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

    public void setInfoEventListener(InfoListener listener) {
        this.infoEventListener = listener;
    }

    public void sendMessage(String message) {
        InfoEvent event = new InfoEvent(this, message);
        if (this.infoEventListener != null) {
            this.infoEventListener.handle(event);
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
        return null;
    }

    //Combatant stuff

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

    @Override
    public int getHitPoints() {
        return hitpoints;
    }

    @Override
    public void setMaxHitpoints(int amount) {
        this.maxHitpoints = amount;
    }

    @Override
    public int getMaxHitpoints() {
        return this.maxHitpoints;
    }

    @Override
    public int getBrawn() {
        return brawn;
    }

    @Override
    public void setBrawn(int amount) {
        this.brawn = amount;
    }

    @Override
    public int getSpiffness() {
        return spiffness;
    }

    @Override
    public void setSpiffness(int amount) {
        this.spiffness = amount;
    }

    @Override
    public int getSmarts() {
        return smarts;
    }

    @Override
    public void setSmarts(int amount) {
        this.smarts = amount;
    }

    @Override
    public int getMoxy() {
        return moxy;
    }

    @Override
    public void setMoxy(int amount) {
        this.moxy = amount;
    }

    @Override
    public void setPendingBlock(int amount) {
        pendingBlock = amount;
    }

    @Override
    public int getPendingBlock() {
        return pendingBlock;
    }

    @Override
    public void setPendingDamage(int amount) {
        pendingDamage = amount;
    }

    @Override
    public int getPendingDamage() {
        return pendingDamage;
    }

    @Override
    public void setPendingHeal(int amount) {
        pendingHeal = amount;
    }

    @Override
    public int getPendingHeal() {
        return pendingHeal;
    }

    @Override
    public Combatant getTarget() {
        return this.target;
    }

    @Override
    public void setTarget(Combatant target) {
        this.target = target;
    }

    @Override
    public void setCombatGroup(CombatGroup combatGroup) {
        this.combatGroup = combatGroup;
    }

    @Override
    public CombatGroup getCombatGroup() {
        return this.combatGroup;
    }

    @Override
    public String getName() {
        return this.username;
    }

    @Override
    public ArrayList<String> getWords() {
        return this.words;
    }

    @Override
    public ArrayList<Weapon> getWeapons() {
        return this.equipped;
    }

    @Override
    public CombatGroup.rpsChoice getCombatDecision() {
        return this.rpsChoice;
    }

    @Override
    public void setCombatDecision(CombatGroup.rpsChoice decision) {
        this.rpsChoice = decision;
    }

    @Override
    public boolean isUnconscious() {
        return this.getHitPoints() <= 0;
    }

    private static class ClientNotFoundException extends RuntimeException {
    }

    public Frame getLastFrame() {
        return lastFrame;
    }

    public void setLastFrame(Frame frame) {
        this.lastFrame = frame;
    }

    //Todo Implement player removal correctly
    public static void removePlayer(Player player) {
        players.remove(player);
    }
}
