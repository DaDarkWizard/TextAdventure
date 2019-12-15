package PlayerHandler;

import PlayerHandler.Commands;
import PlayerHandler.InputHandler;

import java.util.Scanner;

/**
 * Handles server input
 *
 * Date Last Modified: 12/14/2019
 * @author Daniel Masker, Ben Hodsdon, Emma Smith, Joseph Teahen
 *
 * CS1131, fall 2019
 * Lab Section 2
 */
public class CommandInputHandler {

    /**
     * Handles Input from commands from the player
     * @param input the command
     * @return the command it typed if any
     */
    public Commands handleInput(String input) {

        if (input.trim().equals("")) {
            return null;
        }
        input = input.toLowerCase().trim();
        Commands commands = InputHandler.getCommand(new Scanner(input).next());
        if (commands == null) {
            return null;
        }
        switch (commands) {
            case SHUTDOWN:
            case SERVERMESSAGE:
            case IPADDRESS:
            case EXIT:
            case cheatcodesareboss42:
                return commands;
            default:
                return null;
        }
    }
}
