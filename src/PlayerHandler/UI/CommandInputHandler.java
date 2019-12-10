package PlayerHandler.UI;

import PlayerHandler.Commands;
import PlayerHandler.InputHandler;

import java.util.Scanner;

public class CommandInputHandler {
    public CommandInputHandler() {

    }

    public Commands handleInput(String input) {


        Commands commands = InputHandler.getCommand(new Scanner(input).next());
        if (commands == null) {
            return null;
        }
        switch (commands) {
            case SHUTDOWN:
            case SERVERMESSAGE:
            case IPADDRESS:
            case EXIT:
                return commands;
            default:
                return null;
        }
    }
}
