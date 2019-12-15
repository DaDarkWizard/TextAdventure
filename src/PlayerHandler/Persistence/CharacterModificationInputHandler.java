package PlayerHandler.Persistence;

import GamePieces.Room;
import PlayerHandler.Player;
import PlayerHandler.PlayerStates;
import PlayerHandler.UI.Frame;

import java.util.Scanner;

/**
 * Handles profile input
 *
 * Date Last Modified: 12/14/2019
 * @author Daniel Masker, Ben Hodsdon, Emma Smith, Joseph Teahen
 *
 * CS1131, fall 2019
 * Lab Section 2
 */
public class CharacterModificationInputHandler {

    private Room spawn;

    public CharacterModificationInputHandler(Room spawn) {
        this.spawn = spawn;
    }

    public Frame handleInput(String input, Player player) {
        switch (player.getState()) {
            case characterCreation:
                handleCharacterCreation(input, player);
                break;
            case characterRestoration:
                handleCharacterRestoration(input, player);
                break;
        }
        return player.getLastFrame();
    }

    /**
     * Processes character restoration
     * @param input
     * @param player
     */
    private void handleCharacterRestoration(String input, Player player) {
        CharacterLoading characterLoading = CharacterLoading.findCharacterLoadingByPlayer(player);

        if (characterLoading == null) {
            System.out.println("Handlede");
            return;
        } else if (characterLoading.getState() == null) {
            System.out.println("Handlede2");
            return;
        }

        switch (characterLoading.getState()) {
            case getUsername:
                characterLoading.addUsername(input);
                break;
            case getPassword:
                characterLoading.addPassword(input);
                break;
            case newCharacterQuestion:
                input = input.trim();
                input = input.toLowerCase();
                if (input.equals("yes") || input.equals("y")) {
                    characterLoading.askUsername();
                } else if (input.equals("no") || input.equals("n")) {
                    new CharacterCreating().CreateCharacter(player);
                    CharacterLoading.removeCharacterLoading(characterLoading);
                } else {
                    player.getLastFrame().addLine("That isn't a valid answer!", true);
                    player.getLastFrame().addLine("Enter yes to try again or no to create a new character.", true);
                }
                break;
            case restoreDone:
                if (input.trim().toLowerCase().equals("look")) {
                    CharacterLoading.removeCharacterLoading(characterLoading);
                    player.setLocation(spawn);
                    player.setState(PlayerStates.normal);
                } else {
                    player.getLastFrame().addLine("That's not 'look' Try again!", true);
                }
                break;
        }
    }

    /**
     * Ensures that profile input is legal and valid
     * @param input
     * @param player
     */
    private void handleCharacterCreation(String input, Player player) {
        CharacterCreating characterCreating = CharacterCreating.findCharacterCreatorByPlayer(player);
        if (characterCreating == null || characterCreating.getState() == null) {
            return;
        }
        boolean hasWhitespace;
        switch (characterCreating.getState()) {

            case getUsername:
                hasWhitespace = false;
                for (Character c : input.toCharArray()) {
                    if (Character.isWhitespace(c)) {
                        hasWhitespace = true;
                    }
                }
                if (input.length() < 1 || hasWhitespace) {
                    player.getLastFrame().addLine("No whitespace!", true);
                }
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
                hasWhitespace = false;
                for (char c : input.toCharArray()) {
                    if (Character.isWhitespace(c)) {
                        hasWhitespace = true;
                    }
                }
                if (hasWhitespace) {
                    player.getLastFrame().addLine("Your password can't contain whitespace!", true);
                    player.getLastFrame().addLine("Please try again.", true);
                } else {
                    characterCreating.addPassword(input);
                }
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
            case createCharacterEnd:
                if (input.toLowerCase().trim().equals("look")) {
                    CharacterCreating.getCharacterCreators().remove(characterCreating);
                    player.setLocation(spawn);
                    player.setState(PlayerStates.normal);
                } else {
                    player.getLastFrame().addLine("That's not 'look' Try again!", true);
                }
                break;
        }
    }
}

