package Server.Generator;

import Server.CombatHandler.AttackCommands;
import Server.CombatHandler.DamageHandler;
import Server.CombatHandler.Weapons.StatHandler;
import Server.CombatHandler.Weapons.Weapon;
import Server.CombatHandler.Weapons.WeaponUseListener;

import java.util.HashMap;

/**
 * Randomly generates stats and descriptions for weapons
 * <p>
 * Date Last Modified: 12/14/2019
 *
 * @author Daniel Masker, Ben Hodsdon, Emma Smith, Joseph Teahen
 * <p>
 * CS1131, fall 2019
 * Lab Section 2
 */
public class WeaponGenerator {
    private HashMap<String, AttackCommands> commandHashMap = new HashMap<>(); //hash map for commands
    private HashMap<String, Integer> diceHashMap = new HashMap<>(); //hash map for dice sides
    private HashMap<String, Integer> gradeHashMap = new HashMap<>(); //hash map for grade value
    private HashMap<String, WeaponUseListener> listenerHashMap = new HashMap<>(); //hash map for listeners
    private HashMap<AttackCommands, StatHandler.Stats> statsHashMap = new HashMap<>(); //has map for stats

    /**
     * Establishes every weapon component to a hashmap to allow for randomized description and stat assignment
     */
    public WeaponGenerator() {
        //sets up stats hash map
        statsHashMap.put(AttackCommands.smash, StatHandler.Stats.brawn);
        statsHashMap.put(AttackCommands.slash, StatHandler.Stats.brawn);
        statsHashMap.put(AttackCommands.cast, StatHandler.Stats.smerts);
        statsHashMap.put(AttackCommands.stab, StatHandler.Stats.spiffness);
        statsHashMap.put(AttackCommands.shoot, StatHandler.Stats.spiffness);
        statsHashMap.put(AttackCommands.chuck, StatHandler.Stats.spiffness);
        statsHashMap.put(AttackCommands.play, StatHandler.Stats.moxy);

        //sets up command hash map
        commandHashMap.put("Hammer", AttackCommands.smash);
        commandHashMap.put("Club", AttackCommands.smash);
        commandHashMap.put("Mallet", AttackCommands.smash);
        commandHashMap.put("Greatsword", AttackCommands.slash);
        commandHashMap.put("Warhammer", AttackCommands.smash);
        commandHashMap.put("Sword", AttackCommands.slash);
        commandHashMap.put("Axe", AttackCommands.slash);
        commandHashMap.put("Mace", AttackCommands.smash);
        commandHashMap.put("Staff", AttackCommands.cast);
        commandHashMap.put("Wand", AttackCommands.cast);
        commandHashMap.put("Ring", AttackCommands.cast);
        commandHashMap.put("Amulet", AttackCommands.cast);
        commandHashMap.put("Charm", AttackCommands.cast);
        commandHashMap.put("Token", AttackCommands.cast);
        commandHashMap.put("Totem", AttackCommands.cast);
        commandHashMap.put("Dirk", AttackCommands.stab);
        commandHashMap.put("Dagger", AttackCommands.stab);
        commandHashMap.put("Spear", AttackCommands.stab);
        commandHashMap.put("Shortbow", AttackCommands.shoot);
        commandHashMap.put("Longbow", AttackCommands.shoot);
        commandHashMap.put("Crossbow", AttackCommands.shoot);
        commandHashMap.put("Throwing Knives", AttackCommands.chuck);
        commandHashMap.put("Hatchets", AttackCommands.chuck);
        commandHashMap.put("Brass Knuckes", AttackCommands.punch);
        commandHashMap.put("Flute", AttackCommands.play);
        commandHashMap.put("Lute", AttackCommands.play);
        commandHashMap.put("Panpipe", AttackCommands.play);
        commandHashMap.put("Lyre", AttackCommands.play);
        commandHashMap.put("Banjo", AttackCommands.play);
        commandHashMap.put("Cowbell", AttackCommands.play);
        commandHashMap.put("Kazoo", AttackCommands.play);

        //sets up dice hash map
        diceHashMap.put("Hammer", 6);
        diceHashMap.put("Club", 5);
        diceHashMap.put("Mallet", 4);
        diceHashMap.put("Greatsword", 12);
        diceHashMap.put("Warhammer", 10);
        diceHashMap.put("Sword", 8);
        diceHashMap.put("Axe", 7);
        diceHashMap.put("Mace", 9);
        diceHashMap.put("Brass Knuckes", 12);
        diceHashMap.put("Staff", 12);
        diceHashMap.put("Wand", 10);
        diceHashMap.put("Ring", 8);
        diceHashMap.put("Amulet", 9);
        diceHashMap.put("Charm", 5);
        diceHashMap.put("Token", 4);
        diceHashMap.put("Totem", 6);
        diceHashMap.put("Dirk", 12);
        diceHashMap.put("Dagger", 10);
        diceHashMap.put("Spear", 9);
        diceHashMap.put("Shortbow", 9);
        diceHashMap.put("Longbow", 10);
        diceHashMap.put("Crossbow", 12);
        diceHashMap.put("Throwing Knives", 12);
        diceHashMap.put("Hatchets", 10);
        diceHashMap.put("Flute", 6);
        diceHashMap.put("Lute", 7);
        diceHashMap.put("Panpipe", 5);
        diceHashMap.put("Lyre", 8);
        diceHashMap.put("Banjo", 9);
        diceHashMap.put("Cowbell", 12);
        diceHashMap.put("Kazoo", 10);

        //sets up grad hash map
        gradeHashMap.put("Ramshackle", -5);
        gradeHashMap.put("Patchwork", -4);
        gradeHashMap.put("Ungodly", -3);
        gradeHashMap.put("Rusty", -3);
        gradeHashMap.put("Worn", -2);
        gradeHashMap.put("Pig-Iron", -2);
        gradeHashMap.put("Black-Iron", -2);
        gradeHashMap.put("Sketchy", -1);
        gradeHashMap.put("Barbarian", -1);
        gradeHashMap.put("Polished", -1);
        gradeHashMap.put("Medieval", -1);
        gradeHashMap.put("Orcish", 0);
        gradeHashMap.put("Imperial", 0);
        gradeHashMap.put("Celtic", 0);
        gradeHashMap.put("Dwarven", 0);
        gradeHashMap.put("Elven", 0);
        gradeHashMap.put("Numenorean", 0);
        gradeHashMap.put("Angellic", 0);
        gradeHashMap.put("Infernal", 0);
        gradeHashMap.put("Decent", 0);
        gradeHashMap.put("Hardened", 1);
        gradeHashMap.put("Sturdy", 1);
        gradeHashMap.put("Demonic", 1);
        gradeHashMap.put("Good", 1);
        gradeHashMap.put("Pagan", 2);
        gradeHashMap.put("Masterwork", 2);
        gradeHashMap.put("Valyrian", 2);
        gradeHashMap.put("Mastercraft", 3);
        gradeHashMap.put("Celestial", 3);
        gradeHashMap.put("Godly", 4);
        gradeHashMap.put("Legendary", 5);

        /**
         * Sets up listener for each adjective
         *
         * Each one handles all the weapons damage and does some extra affect
         */
        listenerHashMap.put("Wicked", e -> {
            if (e.getSource().getTarget() == null) {
                return;
            }
            int damage = e.getWeapon().rollDice() + e.getWeapon().getModifier();
            damage += e.getSource().getStatByReference(e.getWeapon().getStat());
            int damagePlayer = damage - e.getSource().getStatByReference(StatHandler.findDefensiveStat(e.getWeapon().getStat()));
            damage -= e.getSource().getTarget().getStatByReference(StatHandler.findDefensiveStat(e.getWeapon().getStat()));
            damagePlayer /= 2;
            damage *= 2;

            if (damagePlayer < 0) {
                damagePlayer = 0;
            }
            if (damage < 0) {
                damage = 0;
            }
            e.getSource().setPendingDamage(e.getSource().getPendingDamage() + damagePlayer);
            e.getSource().getTarget().setPendingDamage(e.getSource().getTarget().getPendingDamage() + damage);
        });
        listenerHashMap.put("Roiling", e -> {
            if (e.getSource().getTarget() == null) {
                return;
            }
            int damage = e.getWeapon().rollDice() + e.getWeapon().getModifier();
            damage += e.getSource().getStatByReference(e.getWeapon().getStat());
            damage -= e.getSource().getTarget().getStatByReference(StatHandler.Stats.moxy);

            if (damage < 0) {
                damage = 0;
            }
            e.getSource().getTarget().setPendingDamage(e.getSource().getTarget().getPendingDamage() + damage);
        });
        listenerHashMap.put("Suspicious", e -> {
            if (e.getSource().getTarget() == null) {
                return;
            }
            int damage = e.getWeapon().rollDice() + e.getWeapon().getModifier();
            damage += e.getSource().getStatByReference(e.getWeapon().getStat());
            damage -= e.getSource().getTarget().getStatByReference(StatHandler.findDefensiveStat(e.getWeapon().getStat())) * 2;

            if (damage < 0) {
                damage = 0;
            }
            e.getSource().getTarget().setPendingDamage(e.getSource().getTarget().getPendingDamage() + damage);
        });
        listenerHashMap.put("Piercing", e -> {
            if (e.getSource().getTarget() == null) {
                return;
            }
            int damage = e.getWeapon().rollDice() + e.getWeapon().getModifier();
            damage += e.getSource().getStatByReference(e.getWeapon().getStat());
            damage -= e.getSource().getTarget().getStatByReference(StatHandler.findDefensiveStat(e.getWeapon().getStat()));
            e.getSource().getTarget().modifyHitpoints(-damage);
        });
        listenerHashMap.put("Blinding", e -> {
            if (e.getSource().getTarget() == null) {
                return;
            }
            int damage = e.getWeapon().rollDice() + e.getWeapon().getModifier();
            damage += e.getSource().getStatByReference(e.getWeapon().getStat());
            damage -= e.getSource().getTarget().getStatByReference(StatHandler.findDefensiveStat(e.getWeapon().getStat())) / 2;

            if (damage < 0) {
                damage = 0;
            }
            e.getSource().getTarget().setPendingDamage(e.getSource().getTarget().getPendingDamage() + damage);
        });
        listenerHashMap.put("Shocking", e -> {
            if (e.getSource().getTarget() == null) {
                return;
            }
            int damage = e.getWeapon().rollDice() + e.getWeapon().getModifier();
            damage += e.getSource().getStatByReference(e.getWeapon().getStat());
            damage -= e.getSource().getTarget().getStatByReference(StatHandler.findDefensiveStat(e.getWeapon().getStat()));
            damage += DamageHandler.rollDice(4);

            if (damage < 0) {
                damage = 0;
            }
            e.getSource().getTarget().setPendingDamage(e.getSource().getTarget().getPendingDamage() + damage);
        });
        listenerHashMap.put("Chilling", e -> {
            if (e.getSource().getTarget() == null) {
                return;
            }
            int damage = e.getWeapon().rollDice() + e.getWeapon().getModifier();
            damage += e.getSource().getStatByReference(e.getWeapon().getStat());
            damage -= e.getSource().getTarget().getStatByReference(StatHandler.findDefensiveStat(e.getWeapon().getStat()));
            damage += DamageHandler.rollDice(4);

            if (damage < 0) {
                damage = 0;
            }
            e.getSource().getTarget().setPendingDamage(e.getSource().getTarget().getPendingDamage() + damage);
        });
        listenerHashMap.put("Toxic", e -> {
            if (e.getSource().getTarget() == null) {
                return;
            }
            int damage = e.getWeapon().rollDice() + e.getWeapon().getModifier();
            damage += e.getSource().getStatByReference(e.getWeapon().getStat());
            damage -= e.getSource().getTarget().getStatByReference(StatHandler.findDefensiveStat(e.getWeapon().getStat()));
            damage += DamageHandler.rollDice(4);

            if (damage < 0) {
                damage = 0;
            }
            e.getSource().getTarget().setPendingDamage(e.getSource().getTarget().getPendingDamage() + damage);
        });
        listenerHashMap.put("Seductive", e -> {
            if (e.getSource().getTarget() == null) {
                return;
            }
            int damage = e.getWeapon().rollDice() + e.getWeapon().getModifier();
            damage += e.getSource().getStatByReference(e.getWeapon().getStat());
            damage -= e.getSource().getTarget().getStatByReference(StatHandler.findDefensiveStat(e.getWeapon().getStat()));

            if (damage < 0) {
                damage = 0;
            }
            e.getSource().getTarget().setPendingDamage(e.getSource().getTarget().getPendingDamage() + damage);
        });
        listenerHashMap.put("Venomous", e -> {
            if (e.getSource().getTarget() == null) {
                return;
            }
            int damage = e.getWeapon().rollDice() + e.getWeapon().getModifier();
            damage += e.getSource().getStatByReference(e.getWeapon().getStat());
            damage -= e.getSource().getTarget().getStatByReference(StatHandler.findDefensiveStat(e.getWeapon().getStat()));
            damage += DamageHandler.rollDice(4);

            if (damage < 0) {
                damage = 0;
            }
            e.getSource().getTarget().setPendingDamage(e.getSource().getTarget().getPendingDamage() + damage);
        });
        listenerHashMap.put("Crushing", e -> {
            if (e.getSource().getTarget() == null) {
                return;
            }
            int damage = e.getWeapon().rollDice() + e.getWeapon().getModifier();
            damage += e.getSource().getStatByReference(e.getWeapon().getStat());
            damage -= e.getSource().getTarget().getStatByReference(StatHandler.findDefensiveStat(e.getWeapon().getStat()));
            damage += DamageHandler.rollDice(6);

            if (damage < 0) {
                damage = 0;
            }
            e.getSource().getTarget().setPendingDamage(e.getSource().getTarget().getPendingDamage() + damage);
        });
        listenerHashMap.put("Dismissive", e -> {
            if (e.getSource().getTarget() == null) {
                return;
            }
            int damage = e.getWeapon().rollDice() + e.getWeapon().getModifier();
            int statDam = e.getSource().getStatByReference(e.getWeapon().getStat());
            statDam -= e.getSource().getTarget().getStatByReference(StatHandler.findDefensiveStat(e.getWeapon().getStat()));
            statDam *= 2;
            damage += statDam;

            if (damage < 0) {
                damage = 0;
            }
            e.getSource().getTarget().setPendingDamage(e.getSource().getTarget().getPendingDamage() + damage);
        });
        listenerHashMap.put("Flirtatious", e -> {
            if (e.getSource().getTarget() == null) {
                return;
            }
            int damage = e.getWeapon().rollDice() + e.getWeapon().getModifier();
            damage += e.getSource().getStatByReference(e.getWeapon().getStat());
            damage -= e.getSource().getTarget().getStatByReference(StatHandler.findDefensiveStat(e.getWeapon().getStat()));
            damage += e.getSource().getStatByReference(StatHandler.Stats.moxy) / 2;

            if (damage < 0) {
                damage = 0;
            }
            e.getSource().getTarget().setPendingDamage(e.getSource().getTarget().getPendingDamage() + damage);
        });
        listenerHashMap.put("Acidic", e -> {
            if (e.getSource().getTarget() == null) {
                return;
            }
            int damage = e.getWeapon().rollDice() + e.getWeapon().getModifier();
            damage += e.getSource().getStatByReference(e.getWeapon().getStat());
            damage -= e.getSource().getTarget().getStatByReference(StatHandler.findDefensiveStat(e.getWeapon().getStat()));
            damage += DamageHandler.rollDice(6);

            if (damage < 0) {
                damage = 0;
            }
            e.getSource().getTarget().setPendingDamage(e.getSource().getTarget().getPendingDamage() + damage);
        });

    }

    /**
     * Handles stat-specific weapon descriptors
     *
     * @param grade
     * @param weapon
     * @param adjective
     * @param verb
     * @return
     */
    public Server.CombatHandler.Weapons.Weapon weapon(String grade, String weapon, String adjective, String verb) {
        Weapon returnedWeapon;

        returnedWeapon = new Weapon(weapon, diceHashMap.get(weapon), commandHashMap.get(weapon), statsHashMap.get(commandHashMap.get(weapon)), grade,
                gradeHashMap.get(grade), adjective, listenerHashMap.get(adjective), verb);

        return returnedWeapon;
    }

    /**
     * Returns randomized description of a weapon
     *
     * @return
     */
    public Server.CombatHandler.Weapons.Weapon weapon() {
        String grade = String.valueOf(RandomFileParser.RandomString("Text/grade.txt"));
        String weapon = String.valueOf(RandomFileParser.RandomString("Text/weapon.txt"));
        String adjective = String.valueOf(RandomFileParser.RandomString("Text/adjective.txt"));
        String verb = String.valueOf(RandomFileParser.RandomString("Text/verb.txt"));

        return weapon(grade, weapon, adjective, verb);
    }

}
