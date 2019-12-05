package PlayerHandler.GamePieces;

import PlayerHandler.Commands;
import PlayerHandler.Player;

public class Item implements Holdable {
    String shortDescription = "";
    String longDescription = "";
    String[] validNames;
    InteractListener interactListener;

    public Item(String shortDescription, String longDescription) {

    }


    @Override
    public String getShortDescription() {
        return shortDescription;
    }

    @Override
    public String examine(Player player) {
        return longDescription;
    }

    @Override
    public String interact(Player player, Commands command) {
        InteractEvent event = new InteractEvent(player, command);
        if (interactListener == null) {
            return null;
        }
        return interactListener.handle(event);
    }

    public void setInteractEventListener(InteractListener listener) {
        this.interactListener = listener;
    }


    @Override
    public boolean isValidName(String name) {

        return false;
    }
}
