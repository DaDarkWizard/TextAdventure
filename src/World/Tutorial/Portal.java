package World.Tutorial;

import GamePieces.Furniture;
import PlayerHandler.Persistence.CharacterCreating;

/**
 * Portal for starting character on creation
 * <p>
 * Date Last Modified: 12/14/2019
 *
 * @author Daniel Masker, Ben Hodsdon, Emma Smith, Joseph Teahen
 * <p>
 * CS1131, Fall 2019
 * Lab Section 2
 */
public class Portal extends Furniture {
    /**
     * Constructor
     */
    public Portal() {
        super("Swirling Portal of Darkness", "Peering into the portal, you can see the darkness\n" +
                        "of the abyss staring back at you. Why must portals be so menacing?\n" +
                        "I guess it has something to do with ripping a hole in the Space-Time Continuum.",
                e -> {
                    new CharacterCreating().CreateCharacter(e.getPlayer());
                    e.getPlayer().update();
                    return null;
                });
        this.validNames.add("portal");
        this.validNames.add("swirling portal");
    }
}
