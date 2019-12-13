package PlayerHandler.Persistence;

import PlayerHandler.Player;
import PlayerHandler.UI.Frame;

import java.util.Scanner;

public class CharacterModificationInputHandler {
    public Frame handleInput(String input, Player player) {
        switch (player.getState()) {
            case characterCreation:
                handleCharacterCreation(input, player);
                break;
            case characterRestoration:
                break;
        }
        return player.getLastFrame();
    }

    public void handleCharacterCreation(String input, Player player) {
        CharacterCreating characterCreating = CharacterCreating.findCharacterCreatorByPlayer(player);
        if (characterCreating.getState() == null) {
            return;
        }
        switch (characterCreating.getState()) {
            case getUsername:
                characterCreating.addUsername(input);
                break;
            case confirmUsername:
                input = input.toLowerCase();
                input = input.trim();
                if (input.equals("yes") || input.equals("y")) {
                    characterCreating.confirmUsername(true);
                } else if (input.equals("no") || input.equals("n")) {
                    characterCreating.confirmUsername(false);
                } else {
                    player.getLastFrame().addLine("That is not a valid answer. Please enter yes or no.", true);
                }
                break;
            case getPassword:
                boolean hasWhitespace = false;
                for (char c : input.toCharArray()) {
                    if (Character.isWhitespace(c)) {
                        hasWhitespace = true;
                    }
                }
                if (hasWhitespace) {
                    player.getLastFrame().addLine("Your password can't contain whitespace!", true);
                }
                characterCreating.addPassword(input);
                break;
            case confirmPassword:
                characterCreating.confirmPassword(input);
                break;
            case createCharacterBegin:
                characterCreating.createCharacterMessage();
                break;
            case createCharacterGetStats:
                Scanner scanner = new Scanner(input);
                int[] inputs = new int[4];
                for (int i = 0; i < 4; i++) {
                    inputs[i] = scanner.nextInt();
                }
                characterCreating.enterStats(inputs);
                break;
        }
    }
}

