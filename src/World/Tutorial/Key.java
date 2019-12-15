package World.Tutorial;

import GamePieces.Item;

/**
 * A key to let players in the portal room
 * <p>
 * Date Last Modified: 12/14/2019
 *
 * @author Daniel Masker, Ben Hodsdon, Emma Smith, Joseph Teahen
 * <p>
 * CS1131, Fall 2019
 * Lab Section 2
 */
public class Key extends Item {

    /**
     * Constructor
     */
    public Key() {
        super("Golden Key", "It is a golden intricate key with runic lettering.",
                new String[]{"key", "golden key"});
    }
}
