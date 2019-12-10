package GamePieces;

import PlayerHandler.Commands;
import PlayerHandler.Player;

import java.util.Arrays;

public class Item implements Holdable {
    String shortDescription = "";
    String longDescription = "";
    String[] validNames;
    Room room = null;
    InteractListener interactListener;

    public Item(String shortDescription, String longDescription, String[] validNames, Room room) {
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.validNames = validNames;
        this.room = room;
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
        this.room = null;
        player.getInventory().add(this);
        return "You picked up " + this.shortDescription;
    }

    @Override
    public String drop(Player player) {
        this.room = player.getLocation();
        player.getInventory().remove(this);
        return "You dropped " + this.shortDescription;
    }
}
