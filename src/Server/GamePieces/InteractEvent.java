package Server.GamePieces;

import Server.PlayerHandler.Commands;
import Server.PlayerHandler.Player;

import java.util.EventObject;

/**
 * This class makes an InteractEvent
 * <p>
 * Date Last Modified: 12/14/2019
 *
 * @author Daniel Masker, Ben Hodsdon, Emma Smith, Joseph Teahen
 * <p>
 * CS1131, fall 2019
 * Lab Section 2
 */
public class InteractEvent extends EventObject {
    Player sender; //the player interacting
    Commands command; //the command to interact

    /**
     * The constructor for InteractEvent
     *
     * @param sender  the player
     * @param command the command
     */
    public InteractEvent(Player sender, Commands command) {
        super(sender);
        this.sender = sender;
        this.command = command;
    }

    /**
     * The getter for player
     *
     * @return the player
     */
    public Player getPlayer() {
        return this.sender;
    }

    /**
     * The command getter
     *
     * @return the command
     */
    public Commands getCommand() {
        return this.command;
    }
}
