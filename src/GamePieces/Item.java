package GamePieces;

import PlayerHandler.Commands;
import PlayerHandler.Player;

import java.util.Arrays;

public class Item implements Holdable {
    protected String shortDescription = "";
    String longDescription = "";
    String[] validNames;
    InteractListener interactListener;

    public Item(String shortDescription, String longDescription, String[] validNames) {
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.validNames = validNames;
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

        return (Arrays.binarySearch(validNames, name) > -1);
    }

    //Todo convert pickup and drop to event handlers
    @Override
    public String pickup(Player player) {
        player.getInventory().add(this);
        player.getLocation().removeInteractable(this);
        return "You picked up " + this.shortDescription;
    }

    @Override
    public String drop(Player player) {
        player.getInventory().remove(this);
        player.getLocation().addInteractable(this);
        return "You dropped " + this.shortDescription;
    }
}
