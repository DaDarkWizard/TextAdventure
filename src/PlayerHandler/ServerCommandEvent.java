package PlayerHandler;

import java.util.EventObject;

public class ServerCommandEvent extends EventObject {

    private Player sender;
    private Commands command;
    private String[] args;

    public ServerCommandEvent(Player sender, Commands command, String[] args) {
        super(sender);
        this.sender = sender;
        this.command = command;
        this.args = args;
    }

    public Player getClient() {
        return this.sender;
    }

    public Commands getCommand() {
        return this.command;
    }

    public String[] getArgs() {
        return this.args;
    }
}
