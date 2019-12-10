package PlayerHandler.GamePieces;

import PlayerHandler.Player;
import PlayerHandler.Commands;

public interface Holdable extends Interactable {
    Holdable pickup(Player player);
    void drop(Player player);
}
