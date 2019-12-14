package World;

import GamePieces.Room;

public class Spawn extends Room {
    public static Room spawn;

    /**
     * Initializes Spawn
     */
    public Spawn() {
        super("Spawn", "You are in spawn.", "You see the spawn.");
        spawn = this;
    }
}
