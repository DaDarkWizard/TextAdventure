package CombatHandler.Weapons;

import java.util.HashMap;

/**
 * Assigns and retrieves descriptions to weapons
 *
 * Date Last Modified: 12/14/2019
 * @author Daniel Masker, Ben Hodsdon, Emma Smith, Joseph Teahen
 *
 * CS1131, fall 2019
 * Lab Section 2
 */
public class DescriptionMaker {
    private HashMap<String, String> nameDescription = new HashMap<>();
    private HashMap<String, String> gradeDescription = new HashMap<>();
    private HashMap<String, String> adjectiveDescription = new HashMap<>();
    private HashMap<String, String> verbDescription = new HashMap<>();

    public DescriptionMaker() {
        nameDescription.put("Hammer", "A carpenter's hammer. Does 1D6 damage.");
        nameDescription.put("Club", "A crude wooden club. Does 1D5 damage.");
        nameDescription.put("Mallet", "A rubber mallet. Does 1D4 damage.");
        nameDescription.put("Greatsword","A metal greatsword. Does 1D12 damage.");
        nameDescription.put("Warhammer"," A metal warhammer. Does 1D10 damage.");
        nameDescription.put("Sword","A standard sword of the watch. Does 1D8 damage.");
        nameDescription.put("Axe","A common axe. Does 1D7 damage.");
        nameDescription.put("Mace","A standard mace of the watch. Does 1D9 damage.");
        nameDescription.put("Staff","A wooden quarterstaff. Does 1D12 damage.");
        nameDescription.put("Wand","A wooden wand. Does 1D10 damage.");
        nameDescription.put("Ring","A simple ring. Does 1D8 damage.");
        nameDescription.put("Amulet","A simple amulet. Does 1D9 damage.");
        nameDescription.put("Charm","A hand-carved wooden charm. Does 1D5 damage.");
        nameDescription.put("Token","A hand-carved wooden token. Does 1D4 damage.");
        nameDescription.put("Totem","A hand-carved wooden totem. Does 1D6 damage.");
        nameDescription.put("Dirk","A crude metal blade. Does 1D12 damage.");
        nameDescription.put("Dagger","A common knife. Does 1D10 damage.");
        nameDescription.put("Spear","A fire-hardened wooden spear. Does 1D9 damage.");
        nameDescription.put("Shortbow","A simple shortbow. Does 1D9 damage.");
        nameDescription.put("Longbow","A simple longbow. Does 1D10 damage.");
        nameDescription.put("Crossbow","A common crossbow of the watch. Does 1D12 damage.");
        nameDescription.put("Throwing Knives","A set of small knives. Does 1D12 damage.");
        nameDescription.put("Hatchets","A common carpenter's axe. Does 1D10 damage.");
        nameDescription.put("Brass Knuckles","A hardened cuff. Does 1D12 damage.");
        nameDescription.put("Flute","A wooden flute. Does 1D6 damage.");
        nameDescription.put("Lute","A simple string instrument. Does 1D7 damage.");
        nameDescription.put("Panpipe","A plain set of reed pipes. Does 1D5 damage.");
        nameDescription.put("Lyre","A common lyre. Does 1D8 damage.");
        nameDescription.put("Banjo","A common banjo. Does 1D9 damage.");
        nameDescription.put("Cowbell","A sturdy metal cowbell. Does 1D12 damage.");
        nameDescription.put("Kazoo","An obnoxious kazoo. Does 1D10 damage.");
    }

    public String getNameDescription(String name) {
        return this.nameDescription.getOrDefault(name, "Name Description.");
    }

    public String getGradeDescription(String grade) {
        return this.gradeDescription.getOrDefault(grade, "Grade Description.");
    }

    public String getAdjectiveDescription(String adjective) {
        return this.adjectiveDescription.getOrDefault(adjective, "Adjective Description.");
    }

    public String getVerbDescription(String verb) {
        return this.verbDescription.getOrDefault(verb, "Verb Description.");
    }
}
