package PlayerHandler.CombatHandler;

import PlayerHandler.CombatHandler.Weapons.Weapon;
import PlayerHandler.Player;
import PlayerHandler.PlayerStates;
import PlayerHandler.UI.CombatFrame;
import PlayerHandler.UI.StandardFrame;

import java.util.ArrayList;

public class CombatGroup {
    private final long combatFightTime = 10000;
    private final long combatReadyTime = 5000;
    private ArrayList<Combatant> combatants = new ArrayList<>();
    private int decisions = 0;
    private state combatState;
    private int countup = 0;
    private long combatStartCount;
    public static ArrayList<CombatGroup> CombatGroups = new ArrayList<>();
    private ArrayList<Player> players = new ArrayList<>();
    private Combatant initiant;

    private long combatStartTime;

    public enum state {
        firstround,
        initialize,
        startCombat,
        words,
        calculate,
        rps
    }

    public enum rpsChoice {
        fight,
        flee,
        talk
    }

    public CombatGroup(ArrayList<Combatant> combatants, Combatant initiant) {
        this.combatants = combatants;
        for (Combatant combatant : this.combatants) {
            if (combatant instanceof Player) {
                players.add((Player) combatant);
            }
            combatant.setCombatGroup(this);
            this.initiant = initiant;
        }

        for (Player player : players) {
            player.setState(PlayerStates.combat);
            player.setLastFrame(new CombatFrame());
        }
        this.combatStartTime = System.currentTimeMillis();
        this.combatStartCount = combatStartTime;
        this.combatState = state.firstround;
        CombatGroups.add(this);
    }

    public void RunCombat() {
        switch (combatState) {
            case firstround:
                messageCombatants(initiant.getName() + " has initiated combat!");
                messageCombatants("Get ready to fight!");
                combatState = state.initialize;
                break;
            case initialize:
                countup = 0;
                StringBuilder targetMessage = new StringBuilder();
                targetMessage.append("Combat is about to start! Choose a target:\n");
                for (int i = 0; i < combatants.size(); i++) {
                    targetMessage.append("  ").append(i + 1).append(". ").append(combatants.get(i).getName()).append("\n");
                }

                messageCombatants(targetMessage.toString());
                messageCombatants(Long.toString(combatReadyTime / 1000));
                combatState = state.startCombat;
                countup++;
                break;
            case startCombat:
                String output = "";
                output += (combatReadyTime - countup) / 1000;
                if (countup < combatReadyTime && System.currentTimeMillis() - combatStartCount > countup) {
                    messageCombatants(output);
                    countup += 1000;
                } else if (System.currentTimeMillis() - combatStartCount > countup) {
                    output = "START!!!";
                    messageCombatants(output);
                    combatState = state.words;
                    combatStartCount = System.currentTimeMillis();
                }
                break;
            case words:
                if (System.currentTimeMillis() - combatStartCount > combatReadyTime) {
                    combatState = state.calculate;
                }
                break;
            case calculate:
                for (Player player : players) {
                    player.sendMessage("You used the words " + player.getWords().toString());
                }
                for (Combatant combatant : combatants) {
                    boolean fail = false;
                    for (String word : combatant.getWords()) {
                        AttackCommands command = parseAttackCommand(word);
                        if (command != null) {
                            if (!fail) {
                                DamageHandler.calcAttack(combatant, command);
                            } else {
                                fail = false;
                            }
                        } else {
                            fail = true;
                        }
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
                messageCombatants("Time for Flight, Flee, or Talk!");
                combatState = state.rps;
                break;
            case rps:
                if (System.currentTimeMillis() - combatStartCount > 5000) {
                    if (combatants.size() < 2 || players.size() < 1) {
                        messageCombatants("There are too few people for combat!");
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

    public state getCombatState() {
        return this.combatState;
    }

    public ArrayList<Combatant> getCombatants() {
        return this.combatants;
    }

    private AttackCommands parseAttackCommand(String command) {
        switch (command) {
            case "hit":
                return AttackCommands.hit;
            case "stab":
                return AttackCommands.stab;
            case "slash":
                return AttackCommands.slash;
            case "block":
                return AttackCommands.block;
            case "potion":
                return AttackCommands.potion;
        }
        return null;
    }

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
    public void removeCombatant(Combatant combatant) {
        this.combatants.remove(combatant);
        combatant.setCombatGroup(null);
        if (combatant instanceof Player) {
            ((Player) combatant).setState(PlayerStates.normal);
            players.remove(combatant);
        }
    }
}
