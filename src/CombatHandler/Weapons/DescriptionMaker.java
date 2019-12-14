package CombatHandler.Weapons;

import java.util.HashMap;

public class DescriptionMaker {
    private HashMap<String, String> nameDescription = new HashMap<>();
    private HashMap<String, String> gradeDescription = new HashMap<>();
    private HashMap<String, String> adjectiveDescription = new HashMap<>();
    private HashMap<String, String> verbDescription = new HashMap<>();

    public DescriptionMaker() {
        nameDescription.put("Hammer", "A carpenter's hammer. Does 1D6 damage.");
        nameDescription.put("Mallet", "A rubber mallet. Does 1D4 damage.");
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
