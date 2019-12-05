package PlayerHandler.GamePieces;

import PlayerHandler.Commands;
import PlayerHandler.Player;

import java.util.EventObject;

public class InteractEvent extends EventObject {
    Player sender;
    Commands command;

    public InteractEvent(Player sender, Commands command) {
        super(sender);
        this.sender = sender;
        this.command = command;
    }
}
