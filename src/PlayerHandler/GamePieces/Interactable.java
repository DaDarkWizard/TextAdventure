package PlayerHandler.GamePieces;

import PlayerHandler.Player;
import PlayerHandler.Commands;

public interface Interactable {
    String getShortDescription();

    String examine(Player player);

    String interact(Player player, Commands command);

    boolean isValidName(String name);

    void setInteractEventListener(InteractListener listener);
}
