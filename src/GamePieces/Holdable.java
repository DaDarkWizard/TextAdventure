package GamePieces;

import PlayerHandler.Player;

public interface Holdable extends Interactable {
    Holdable pickup(Player player);
    void drop(Player player);
}
