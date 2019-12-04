package ClientHandler;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class InputHandler {
    private String[] east = {"e", "east", "right"};
    private String[] west = {"w", "west", "left"};
    private String[] north = {"n", "north", "up"};
    private String[] south = {"s", "south", "down"};

    public Commands getCommand(String command) {
        if (Arrays.binarySearch(east, command) > -1) {
            return Commands.east;
        } else if (Arrays.binarySearch(west, command) > -1) {
            return Commands.west;
        } else if (Arrays.binarySearch(north, command) > -1) {
            return Commands.north;
        } else if (Arrays.binarySearch(south, command) > -1) {
            return Commands.south;
        } else if (command.equals("go")) {
            return Commands.skip;
        }
        return null;
    }

    public String handleInput(String input) {
        String output;                          //The output to send to the client

        //Normalize input
        input = input.toLowerCase();

        Scanner scanner = new Scanner(input);
        Commands command = getCommand(scanner.next());
        if (command == null) {
            return "That command isn't valid!";
        }
        switch (command) {
            case skip:
                StringBuilder refresh = new StringBuilder();
                while (scanner.hasNext()) {
                    refresh.append(scanner.next());
                }
                output = handleInput(refresh.toString());
                break;
            case east:
            case west:
            case south:
            case north:
                output = "You moved " + command.toString();
                break;
            default:
                output = "You shouldn't be getting this.";
        }
        return output;
    }
}
