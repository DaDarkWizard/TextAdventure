import CombatHandler.Weapons.Weapon;
import java.io.FileNotFoundException;
import java.util.Random;

public class WeaponGenerator {

    private final String Weapon;

    public WeaponGenerator(String Weapon) throws FileNotFoundException {
        this.Weapon = Weapon;
    }

    public static String Weapon() throws FileNotFoundException {

        String grade = String.valueOf(new RandomFileParser("Text/grade.txt"));
        String weapon = String.valueOf(new RandomFileParser("Text/weapon.txt"));
        String adjective = String.valueOf(new RandomFileParser("Text/adjective.txt"));
        String verb = String.valueOf(new RandomFileParser("Text/verb.txt"));

        String item = grade + " " + weapon + " of " + adjective + " " + verb;

        return item;
    }
}
