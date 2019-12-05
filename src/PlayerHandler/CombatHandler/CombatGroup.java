package PlayerHandler.CombatHandler;

import PlayerHandler.CombatHandler.Weapons.Weapon;
import PlayerHandler.Player;
import PlayerHandler.PlayerStates;

import java.util.ArrayList;

public class CombatGroup {
    private ArrayList<Combatant> combatants = new ArrayList<>();
    private int decisions = 0;
    private state combatState;
    private int countup = 0;
    private long combatStartCount;

    private long combatStartTime;

    public enum state {
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

    public CombatGroup(ArrayList<Combatant> combatants) {
        this.combatants = combatants;
        for (Combatant combatant : this.combatants) {
            if (combatant instanceof Player) {
                ((Player) combatant).setState(PlayerStates.combat);
            }
            combatant.setCombatGroup(this);
        }
        this.combatStartTime = System.currentTimeMillis();
        this.combatStartCount = combatStartTime;
        this.combatState = state.initialize;
    }

    public void RunCombat() {
        switch (combatState) {
            case initialize:
                countup = 0;
                StringBuilder targetMessage = new StringBuilder();
                targetMessage.append("Combat is about to start! Choose a target:\n");
                for (int i = 0; i < combatants.size(); i++) {
                    targetMessage.append("  ").append(i + 1).append(". ").append(combatants.get(i)).append("\n");
                }

                messageCombatants(targetMessage.toString());
                messageCombatants("5");
                countup++;
                break;
            case startCombat:
                String output = "";
                output += 5 - countup;
                if (countup < 5 && System.currentTimeMillis() - combatStartCount > countup * 1000) {
                    messageCombatants(output);
                    countup++;
                } else if (System.currentTimeMillis() - combatStartCount > countup * 1000) {
                    output = "START!!!";
                    messageCombatants(output);
                    combatState = state.words;
                    combatStartCount = System.currentTimeMillis();
                }
                break;
            case words:
                if (System.currentTimeMillis() - combatStartCount > 5000) {
                    combatState = state.calculate;
                }
                break;
            case calculate:
                for (Combatant combatant : combatants) {
                    boolean fail = false;
                    for (String word : combatant.getWords()) {
                        AttackCommands command = parseAttackCommand(word);
                        if (command != null) {
                            if (!fail) {
                                for (Weapon weapon : combatant.getWeapons()) {
                                    if (weapon.getAttackCommand() == command) {
                                        weapon.useWeapon(combatant);
                                    }
                                }
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
                    if (combatants.size() < 2) {
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
                        messageCombatants("Looks like there's still some fighting spirit!");
                        combatState = state.initialize;
                    }
                }
                break;
        }
    }

    private void messageCombatants(String message) {
        for (Combatant combatant : combatants) {
            if (combatant instanceof Player) {
                ((Player) combatant).sendMessage(message);
            }
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

    public void removeCombatant(Combatant combatant) {
        this.combatants.remove(combatant);
        combatant.setCombatGroup(null);
    }
}
