package Server.CombatHandler;

import Server.CombatHandler.Weapons.TooFewCombatantsException;
import Server.NPCHandler.NPC;
import Server.PlayerHandler.Player;
import Server.PlayerHandler.PlayerStates;
import Server.PlayerHandler.UI.CombatFrame;
import Server.PlayerHandler.UI.Frame;
import Server.PlayerHandler.UI.StandardFrame;

import java.util.ArrayList;

/**
 * The class handles all of combat
 * <p>
 * Date Last Modified: 12/14/2019
 *
 * @author Daniel Masker, Ben Hodsdon, Emma Smith, Joseph Teahen
 * <p>
 * CS1131, fall 2019
 * Lab Section 2
 */

public class CombatGroup {
    private final long combatFightTime = 10000; //Length of time to fight (type words)
    private final long combatReadyTime = 5000;  //Length of time to get ready (choose opponent)
    private ArrayList<Combatant> combatants;    //All combatants in the group
    private int decisions = 0;
    private state combatState;                  //State of the CombatGroup
    private int countup = 0;                    //Count for countdown
    private long combatStartCount;              //Stores when timer began
    public static ArrayList<CombatGroup> CombatGroups = new ArrayList<>();  //Static arraylist to hold
    //CombatGroups that exist
    private ArrayList<Player> players = new ArrayList<>();                  //All players in the group
    private Combatant initiant;                                             //Combatant that started the fight

    private long combatStartTime;                                           //Stores when combat begam

    /**
     * Enum for different states of the combat state machine
     */
    public enum state {
        firstround,
        initialize,
        startCombat,
        words,
        calculate,
        rps,
        ended
    }

    /**
     * Different choices for the "Rock Paper Scissors" at the end of combat
     */
    public enum rpsChoice {
        fight,
        flee,
        talk
    }

    /**
     * Initialize the combat group
     *
     * @param combatants Combatants that will fight
     * @param initiant   Person that started the fight
     */
    public CombatGroup(ArrayList<Combatant> combatants, Combatant initiant) {
        //Todo Make this conversion a deep copy
        this.combatants = new ArrayList<>( combatants );       //Set all combatants from input
        this.initiant = initiant;           //Set initiant from input

        //Must have combatants in group
        if (combatants == null) {
            throw new IllegalArgumentException("Combat group needs combatants");
        }

        //Must have more than one combatant in a group
        if (combatants.size() < 2) {
            throw new TooFewCombatantsException("Combat group needs at least 2 combatants.");
        }

        Player.displayPlayers( Player.players.get( 0 ) );       // display list of players

        //Get all combatants and handle them
        for (Combatant combatant : this.combatants) {
            //Add them to the player array
            if (combatant instanceof Player) {
                players.add((Player) combatant);
                System.out.println( "Player " + combatant.getName() + " is added to battle!");

            }
            //Set their combat group to this
            combatant.setCombatGroup(this);
        }

        //Handle player-specific things
        for (Player player : players) {
            //Set state to combat
            player.setState(PlayerStates.combat);
            System.out.println( "Player " + player.getName() + " is set to combat!");
            //Give them a new combat frame
            player.setLastFrame(new CombatFrame(player));
        }

        //Record when combat began
        this.combatStartTime = System.currentTimeMillis();
        System.out.println( "Battle time start:" + this.combatStartTime );
        //Separate record for timers
        this.combatStartCount = combatStartTime;
        //Set the state so it's ready to run
        this.combatState = state.firstround;
        System.out.println("Battle firstround set.");
        //Add to Combat Groups so the Combat thread can run it
        CombatGroups.add(this);
        updatePlayer();
    }

    /**
     * Runs a frame of combat
     */

    public void RunCombat() {
        switch (combatState) {
            case firstround:
                messageCombatants(initiant.getName() + " has initiated combat!");
                messageCombatants("Get ready to fight!");
                for (Player player : players) {
                    if (!(player.getLastFrame() instanceof CombatFrame)) {
                        player.setLastFrame(new CombatFrame(player));
                    }
                    CombatFrame frame = (CombatFrame) player.getLastFrame();
                }
                combatState = state.initialize;
                break;
            case initialize:
                System.out.println("Starting combat");
                countup = 0;
                StringBuilder targetMessage = new StringBuilder();
                targetMessage.append("Combat is about to start!\nChoose a target.\n");
                combatStartCount = System.currentTimeMillis();
                messageCombatants(targetMessage.toString());
                combatState = state.startCombat;

                for (Player player : players) {
                    if (!(player.getLastFrame() instanceof CombatFrame)) {
                        player.setLastFrame(new CombatFrame(player));
                    }
                    CombatFrame frame = (CombatFrame) player.getLastFrame();
                    frame.updateStartTimer(" Start in: 5 ");
                }
                updatePlayer();


                break;
            case startCombat:
                String output = "";
                output += (combatReadyTime - countup) / 1000;
                if (countup < combatReadyTime && System.currentTimeMillis() - combatStartCount > countup) {
                    countup += 1000;
                    for (Player player : players) {
                        if (!(player.getLastFrame() instanceof CombatFrame)) {
                            player.setLastFrame(new CombatFrame(player));
                        }
                        CombatFrame frame = (CombatFrame) player.getLastFrame();
                        frame.updateStartTimer(" Start in: " + (5000 - countup) / 1000 + " ");
                    }
                    updatePlayer();
                } else if (System.currentTimeMillis() - combatStartCount > countup) {
                    for (Player player : players) {
                        if (!(player.getLastFrame() instanceof CombatFrame)) {
                            player.setLastFrame(new CombatFrame(player));
                        }
                        CombatFrame frame = (CombatFrame) player.getLastFrame();
                        frame.updateStartTimer("   Start!!!   ");
                    }
                    combatState = state.words;
                    combatStartCount = System.currentTimeMillis();
                    updatePlayer();
                }
                break;
            case words:
                if (System.currentTimeMillis() - combatStartCount > combatReadyTime) {
                    combatState = state.calculate;
                }
                break;
            case calculate:
                for (Player player : players) {
                    boolean fail = false;
                    for (String word : player.getWords()) {
                        AttackCommands command = parseAttackCommand(word);
                        if (command != null) {
                            if (!fail) {
                                DamageHandler.calcAttack(player, command);
                            } else {
                                fail = false;
                            }
                        } else {
                            fail = true;
                        }
                    }
                }

                for (Combatant combatant : combatants) {
                    if (combatant instanceof NPC) {
                        System.out.println(combatant.getName());
                        ((NPC) combatant).findTarget();
                        ((NPC) combatant).makeAttack();
                    }
                }

                for (Combatant combatant : combatants) {
                    System.out.println( "Name: " + combatant.getName() );
                    System.out.println( "Initial Hit Points: " + combatant.getHitPoints() );
                    int damage = combatant.getPendingDamage();
                    System.out.println("Initial damage: " + damage);
                    damage -= combatant.getPendingBlock();
                    if (damage < 0) {
                        damage = 0;
                    }
                    System.out.println("Damage after block: " + damage);
                    damage -= combatant.getPendingHeal();
                    System.out.println("Damage after possible heal: " + damage);
                    combatant.modifyHitpoints(damage * -1);
                    System.out.println( "Final Hit Points: " + combatant.getHitPoints() );
                    combatant.setPendingDamage( 0 );        // reset pending damage to 0
                    combatant.setPendingHeal( 0 );          // reset pending heal to 0
                    if (combatant.getHitPoints() < 1) {
                        messageCombatants(combatant.getName() + " is defeated!");
                    }
                }
                for (int i = 0; i < combatants.size(); i++) {
                    if (combatants.get(i).getHitPoints() < 1) {
                        if (combatants.get(i) instanceof Player) {
                            ((Player) combatants.get(i)).respawn();
                            ((Player) combatants.get(i)).sendMessage("You have died!");
                        }
                        removeCombatant(combatants.get(i));
                        i--;
                    }
                }
                combatStartCount = System.currentTimeMillis();
                messageCombatants("Time for Fight, Flee, or Talk!");
                //Remove attacks after combat
                for (Player player : players) {
                    player.getWords().clear();
                }
                combatState = state.rps;
                break;
            case rps:
                if (System.currentTimeMillis() - combatStartCount > 5000) {
                    if (combatants.size() < 2 || players.size() < 1) {
                        messageCombatants("There are too few people for combat!");
                        for (Player player : players) {
                            Frame frame = new StandardFrame();
                            frame.add(player.getLocation().getDescription());
                            player.setLastFrame(frame);
                            player.update();
                        }
                        for (int i = 0; i < combatants.size(); i++) {
                            removeCombatant(combatants.get(i));
                            i--;
                        }
                    }
                    displayCombatants();                // display the Combatant list to the console
                    boolean fighting = false;
                    for (Combatant combatant : combatants) {
                        if (combatant.getCombatDecision() == rpsChoice.fight) {
                            fighting = true;
                        }
                    }
                    if (!fighting) {


                        for (Player player : players) {
                            Frame frame = new StandardFrame();
                            frame.add(player.getLocation().getDescription());
                            player.setLastFrame(frame);
                        }
                        messageCombatants("Everyone has disengaged from combat!");
                        for (int i = 0; i < combatants.size(); i++) {
                            removeCombatant(combatants.get(i));
                            i--;
                        }
                        combatState = state.ended;
                    } else {
                        snagRoom();
                        messageCombatants("Looks like there's still some fighting spirit!");
                        combatState = state.initialize;
                    }
                }
                break;
            case ended:
                System.out.println( "The battle is over!" );
                CombatGroups.remove( this );
        }
    }

    /**
     * Allows for messages between players
     */

    private void messageCombatants(String message) {
        for (Player player : players) {
            player.sendMessage(message);
        }
    }

    /**
     * Gets the Combat State
     *
     * @return combat state
     */

    public state getCombatState() {
        return this.combatState;
    }

    /**
    * Getting all combatants and NPC's 

    * @return combatants
    */
    //Todo make this method do a deep copy of the ArrayList
    public ArrayList<Combatant> getCombatants() {
        return new ArrayList<>( this.combatants);
    }

    /**
     * removes the specified combatant
     *
     * @return output
     */
    //Todo check if this method makes a deep copy
    //Todo Why is this not named "removeCombatant"?
    public ArrayList<Combatant> getCombatants(Combatant combatant) {
        ArrayList<Combatant> output = new ArrayList<>();
        for (Combatant value : combatants) {
            //Todo check this, this may be checking if they are the same memory reference, not having the same data
            if (value != combatant) {          // adds all combatants but the one as a parameter to the ArrayList output
                output.add(value);
            }
        }
        return output;
    }

    /**
     * Parses Attack Command to string
     *
     * @return attackCommands or null if an exception
     */

    private AttackCommands parseAttackCommand(String command) {
        try {
            AttackCommands attackCommands = AttackCommands.valueOf(command);
            return attackCommands;
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    /**
     * Snags all the players in the room into combat
     */
    private void snagRoom() {
        for (Combatant combatant : players.get(0).getLocation().getCombatants()) {
            if (combatants.indexOf(combatant) < 0) {
                combatants.add(combatant);
                if (combatant instanceof Player) {
                    ((Player) combatant).setState(PlayerStates.combat);
                    ((Player) combatant).setLastFrame(new StandardFrame());
                    ((Player) combatant).sendMessage("You have been dragged into combat!");
                }
            }
        }
    }

    /**
     * sends a new frame to all the players
     */
    public void updatePlayer() {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).isDisconnected()) {
                removeCombatant(players.get(i));
                i--;
            } else {
                players.get(i).update();
            }
        }
    }

    /**
     * Removes the players from combat
     */
    public void removeCombatant(Combatant combatant) {
        this.combatants.remove(combatant);
        combatant.setCombatGroup(null);
        if (combatant instanceof Player) {
            ((Player) combatant).setState(PlayerStates.normal);
            players.remove(combatant);
        }
    }

    /**
     * Method to display the active combatant list to the console
     *
     */
    public void displayCombatants() {
        String output = "Displaying Active Combatants:\n";
        for( Combatant combat : combatants)
        {
            output += combat.getName() + "\n";
        }
        System.out.println( output );
    }



}
