package GamePieces;

import PlayerHandler.Commands;
import PlayerHandler.Player;

import java.util.ArrayList;

public class Furniture implements Interactable {
    private String shortDescription;
    private String longDescription;
    private ArrayList<String> validNames = new ArrayList<>();
    private InteractListener interactListener;

    public Furniture(String shortDescription, String longDescription, InteractListener interactListener) {
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.interactListener = interactListener;
        validNames.add(shortDescription.toLowerCase());
    }

    @Override
    public String getLongDescription() {
        return this.longDescription;
    }

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
        return validNames.contains(name.trim().toLowerCase());
    }

    @Override
    public void setInteractEventListener(InteractListener listener) {

    }
}
