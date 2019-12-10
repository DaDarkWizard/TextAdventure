package GamePieces;

import PlayerHandler.Commands;
import PlayerHandler.Player;

public class Furniture implements Interactable {
    private String shortDescription = "";
    private String longDescription = "";

    private InteractListener interactListener;

    @Override
    public String getShortDescription() {
        return this.shortDescription;
    }

    @Override
    public String examine(Player player) {
        return this.longDescription;
    }

    @Override
    public String interact(Player player, Commands command) {
        InteractEvent event = new InteractEvent(player, command);
        if (interactListener == null) {
            return null;
        }
        return interactListener.handle(event);
    }

    @Override
    public boolean isValidName(String name) {
        return false;
    }

    @Override
    public void setInteractEventListener(InteractListener listener) {

    }
}
