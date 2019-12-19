package Server.CombatHandler.Weapons;

import java.util.HashMap;

/**
 * Assigns and retrieves descriptions to weapons
 * <p>
 * Date Last Modified: 12/14/2019
 *
 * @author Daniel Masker, Ben Hodsdon, Emma Smith, Joseph Teahen
 * <p>
 * CS1131, fall 2019
 * Lab Section 2
 */
public class DescriptionMaker {
    private HashMap<String, String> nameDescription = new HashMap<>(); //name hashmap
    private HashMap<String, String> gradeDescription = new HashMap<>(); //grade hashmap
    private HashMap<String, String> adjectiveDescription = new HashMap<>(); //adjective hashmap
    private HashMap<String, String> verbDescription = new HashMap<>(); //verb hashmap

    public DescriptionMaker() {
        //puts the relations between dice and weapon
        nameDescription.put("Hammer", "A carpenter's hammer. Does 1D6 damage.");
        nameDescription.put("Club", "A crude wooden club. Does 1D5 damage.");
        nameDescription.put("Mallet", "A rubber mallet. Does 1D4 damage.");
        nameDescription.put("Greatsword", "A metal greatsword. Does 1D12 damage.");
        nameDescription.put("Warhammer", " A metal warhammer. Does 1D10 damage.");
        nameDescription.put("Sword", "A standard sword of the watch. Does 1D8 damage.");
        nameDescription.put("Axe", "A common axe. Does 1D7 damage.");
        nameDescription.put("Mace", "A standard mace of the watch. Does 1D9 damage.");
        nameDescription.put("Staff", "A wooden quarterstaff. Does 1D12 damage.");
        nameDescription.put("Wand", "A wooden wand. Does 1D10 damage.");
        nameDescription.put("Ring", "A simple ring. Does 1D8 damage.");
        nameDescription.put("Amulet", "A simple amulet. Does 1D9 damage.");
        nameDescription.put("Charm", "A hand-carved wooden charm. Does 1D5 damage.");
        nameDescription.put("Token", "A hand-carved wooden token. Does 1D4 damage.");
        nameDescription.put("Totem", "A hand-carved wooden totem. Does 1D6 damage.");
        nameDescription.put("Dirk", "A crude metal blade. Does 1D12 damage.");
        nameDescription.put("Dagger", "A common knife. Does 1D10 damage.");
        nameDescription.put("Spear", "A fire-hardened wooden spear. Does 1D9 damage.");
        nameDescription.put("Shortbow", "A simple shortbow. Does 1D9 damage.");
        nameDescription.put("Longbow", "A simple longbow. Does 1D10 damage.");
        nameDescription.put("Crossbow", "A common crossbow of the watch. Does 1D12 damage.");
        nameDescription.put("Throwing Knives", "A set of small knives. Does 1D12 damage.");
        nameDescription.put("Hatchets", "A common carpenter's axe. Does 1D10 damage.");
        nameDescription.put("Brass Knuckles", "A hardened cuff. Does 1D12 damage.");
        nameDescription.put("Flute", "A wooden flute. Does 1D6 damage.");
        nameDescription.put("Lute", "A simple string instrument. Does 1D7 damage.");
        nameDescription.put("Panpipe", "A plain set of reed pipes. Does 1D5 damage.");
        nameDescription.put("Lyre", "A common lyre. Does 1D8 damage.");
        nameDescription.put("Banjo", "A common banjo. Does 1D9 damage.");
        nameDescription.put("Cowbell", "A sturdy metal cowbell. Does 1D12 damage.");
        nameDescription.put("Kazoo", "An obnoxious kazoo. Does 1D10 damage.");
    }

    /**
     * Gets the name of the description
     *
     * @param name the name of the weapon
     * @return
     */
    public String getNameDescription(String name) {
        return this.nameDescription.getOrDefault(name, "Name Description.");
    }
}
