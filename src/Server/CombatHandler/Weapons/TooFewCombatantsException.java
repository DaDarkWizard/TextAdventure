package Server.CombatHandler.Weapons;

/**
 * Exception for when an attempt is made to initiate combat but not enough people are in the room
 * <p>
 * Date Last Modified: 12/10/2019
 *
 * @author Daniel Masker, Joe Teahen, Ben Hodsdon, Emma Smith
 * <p>
 * CS 1131, Fall 2019
 * Lab Section 2
 */
public class TooFewCombatantsException extends RuntimeException {

    /**
     * Constructor to just make one
     */
    public TooFewCombatantsException() {
        super();
    }

    /**
     * Constructor to give a reason why
     *
     * @param description the reason why this happened
     */
    public TooFewCombatantsException(String description) {
        super(description);
    }
}
