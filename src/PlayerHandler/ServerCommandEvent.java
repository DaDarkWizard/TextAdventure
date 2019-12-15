package PlayerHandler;

import java.util.EventObject;
/**
 * This class makes a sever command event
 * <p>
 * Date Last Modified: 12/14/2019
 *
 * @author Daniel Masker, Ben Hodsdon, Joe Teahen, Emma Smith
 * <p>
 * CS 1131, Fall 2019
 * Lab Section 2
 */
public class ServerCommandEvent extends EventObject {

    private Player sender; //the player who sent the command
    private Commands command; //the command sent
    private String[] args; //the args of the program

    /**
     * Constructor
     * @param sender the player who sent the command
     * @param command the command sent
     * @param args the args of the program
     */
    public ServerCommandEvent(Player sender, Commands command, String[] args) {
        super(sender);
        this.sender = sender;
        this.command = command;
        this.args = args;
    }

    /**
     * Gets the client
     * @return the client
     */
    public Player getClient() {
        return this.sender;
    }

    /**
     * Gets the command
     * @return the command
     */
    public Commands getCommand() {
        return this.command;
    }

    /**
     * Gets the args
     * @return
     */
    public String[] getArgs() {
        return this.args;
    }
}
