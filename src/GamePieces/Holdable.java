package GamePieces;

import PlayerHandler.Player;

public interface Holdable extends Interactable {
    String pickup(Player player);
    String drop(Player player);
}
