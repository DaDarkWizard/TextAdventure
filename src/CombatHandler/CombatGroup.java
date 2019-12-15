package CombatHandler;

import CombatHandler.Weapons.TooFewCombatantsException;
import NPCHandler.NPC;
import PlayerHandler.Player;
import PlayerHandler.PlayerStates;
import PlayerHandler.UI.CombatFrame;
import PlayerHandler.UI.Frame;
import PlayerHandler.UI.StandardFrame;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * The class handles all of combat 
 *
 * Date Last Modified: 12/14/2019
 * @author Daniel Masker, Ben Hodsdon, Emma Smith, Joseph Teahen
 *
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
        rps
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
        this.combatants = combatants;       //Set all combatants from input
        this.initiant = initiant;           //Set initiant from input

        //Must have combatants in group
        if (combatants == null) {
            throw new IllegalArgumentException("Combat group needs combatants");
        }

        //Must have more than one combatant in a group
        if (combatants.size() < 2) {
            throw new TooFewCombatantsException("Combat group needs at least 2 combatants.");
        }

        //Get all combatants and handle them
        for (Combatant combatant : this.combatants) {
            //Add them to the player array
            if (combatant instanceof Player) {
                players.add((Player) combatant);
            }
            //Set their combat group to this
            combatant.setCombatGroup(this);
        }

        //Handle player-specific things
        for (Player player : players) {
            //Set state to combat
            player.setState(PlayerStates.combat);
            //Give them a new combat frame
            player.setLastFrame(new CombatFrame(player));
        }

        //Record when combat began
        this.combatStartTime = System.currentTimeMillis();
        //Seperate record for timers
        this.combatStartCount = combatStartTime;
        //Set the state so it's ready to run
        this.combatState = state.firstround;
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
                    int damage = combatant.getPendingDamage();
                    damage -= combatant.getPendingBlock();
                    if (damage < 0) {
                        damage = 0;
                    }
                    damage -= combatant.getPendingHeal();

                    combatant.modifyHitpoints(damage * -1);
                    if (combatant.getHitPoints() < 1) {
                        messageCombatants(combatant.getName() + " is defeated!");
                    }
                }
                for (int i = 0; i < combatants.size(); i++) {
                    if (combatants.get(i).getHitPoints() < 1) {
                        if (combatants.get(i) instanceof Player) {
                            ((Player) combatants.get(i)).sendMessage("You have fallen unconscious.");
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
                    } else {
                        snagRoom();
                        messageCombatants("Looks like there's still some fighting spirit!");
                        combatState = state.initialize;
                    }
                }
                break;
        }
    }

    private void messageCombatants(String message) {
        for (Player player : players) {
            player.sendMessage(message);
        }
    }
    
    /**
    * Gets the Combat State 
    * @return combat state
    */

    public state getCombatState() {
        return this.combatState;
    }

    /**
    * Getting all combatants and NPC's 
    * @return combatants 
    */
    
    public ArrayList<Combatant> getCombatants() {
        return this.combatants;
    }

    /**
    * removes the specified combatant 
    * @return output
    */
    
    public ArrayList<Combatant> getCombatants(Combatant combatant) {
        ArrayList<Combatant> output = new ArrayList<>();
        for (Combatant value : combatants) {
            if (value != combatant) {
                output.add(value);
            }
        }
        return output;
    }

    /**
    * Parses Attack Command to string
    * @return attackCommands 
    * @return null
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
        for (Player player : players) {
            player.update();
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
}
