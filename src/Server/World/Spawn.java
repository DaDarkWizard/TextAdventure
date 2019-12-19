package Server.World;

import Server.GamePieces.Room;

/**
 * Spawn area for account-owning players to socialize
 * <p>
 * Date Last Modified: 12/13/2019
 *
 * @author Daniel Masker, Ben Hodsdon, Emma Smith, Joseph Teahen
 * <p>
 * CS1131, Fall 2019
 * Lab Section 2
 */
public class Spawn extends Room {
    public static Room spawn;

    /**
     * Initializes Spawn
     */
    public Spawn() {
        super("Spawn",
                "You are in spawn. This is the end of the game so far\n" +
                        "If you'd like to track our progress, follow us on our github:\n" +
                        "https://github.com/DaDarkWizard/TextAdventure", "You see the spawn.");
        spawn = this;
    }
}
