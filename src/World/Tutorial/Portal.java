package World.Tutorial;

import GamePieces.Furniture;
import PlayerHandler.Persistence.CharacterCreating;

public class Portal extends Furniture {
    public Portal() {
        super("Swirling Portal of Darkness", "Peering into the portal, you can see the darkness\n" +
                        "of the abyss staring back at you. Why must portals be so menacing?\n" +
                        "I guess it has something to do with ripping a hole in the Space-Time Continuum.",
                e -> {
                    new CharacterCreating().CreateCharacter(e.getPlayer());
                    return null;
                });
        this.validNames.add("portal");
        this.validNames.add("swirling portal");
    }
}
