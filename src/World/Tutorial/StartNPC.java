package World.Tutorial;

/**
 * A tutorial NPC
 * <p>
 * Date Last Modified: 12/14/2019
 *
 * @author Daniel Masker, Ben Hodsdon, Emma Smith, Joseph Teahen
 * <p>
 * CS1131, Fall 2019
 * Lab Section 2
 */
public class StartNPC extends InfoGiver {
    /**
     * Constructor
     */
    public StartNPC() {
        super("Force of Beginnings");
        this.addLine("Welcome to your new home, adventurer.");
        this.addLine("Let's get you oriented.");
        this.addLine("Start by heading to the north, using the command 'north'");
    }
}
