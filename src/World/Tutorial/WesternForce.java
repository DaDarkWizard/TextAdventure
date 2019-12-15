package World.Tutorial;

/**
 * A tutorial NPC for the West Room
 * <p>
 * Date Last Modified: 12/14/2019
 *
 * @author Daniel Masker, Ben Hodsdon, Emma Smith, Joseph Teahen
 * <p>
 * CS1131, Fall 2019
 * Lab Section 2
 */
public class WesternForce extends InfoGiver {

    /**
     * Constructor
     */
    public WesternForce() {
        super("Great Western Force");
        this.addLine("You are about to enter combat with what is known as a 'Grue'.");
        this.addLine("I have provided two weapons here for you to use. Before you continue, let me explain combat.");
        this.addLine("When you first begin combat, you will asked to choose a target.");
        this.addLine("The targets will be listed in the lower right portion of the screen.");
        this.addLine("In order to select one, simply enter the target's listed number");
        this.addLine("Once combat begins, FRANTICALLY BEGIN TYPING.");
        this.addLine("You will have a list of commands you can use in the lower right portion of your screen.");
        this.addLine("Each command maps to a different equipped weapon, so choose carefully!");
        this.addLine("Using hit will allow you to punch the creature with your fists.");
        this.addLine("Inventory (number) will let you use an unequipped item in your inventory.");
        this.addLine("Type it without the parentheses, as shown: Inventory 1");
        this.addLine("Once combat comes to an end, you will have the choice of choosing to either Fight, Flee, or Talk.");
        this.addLine("Typing 'fight', 'flee', or 'talk' will make your decision.");
        this.addLine("'Fight' will continue your battle with others, 'flee' will send you back to your previous room,\n");
        this.addLine("and 'talk' will allow you to stay and talk it out, if the others are willing.");
        this.addLine("Good luck on your travels!");
    }
}
