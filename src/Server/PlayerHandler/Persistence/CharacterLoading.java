package Server.PlayerHandler.Persistence;

import Server.PlayerHandler.Player;
import Server.PlayerHandler.PlayerStates;
import Server.PlayerHandler.UI.StandardFrame;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class processes profile restoration
 * <p>
 * Date Original Last Modified: 12/14/2019
 * Added method comments: 12/20/2019 - Michael Clinesmith
 *
 * @author Daniel Masker, Ben Hodsdon, Emma Smith, Joseph Teahen
 * <p>
 * CS1131, fall 2019
 * Lab Section 2
 */
public class CharacterLoading {
    public static ArrayList<CharacterLoading> characterLoaders = new ArrayList<>();

    private Player player;
    private RestoreCharacterState restoreCharacterState;

    private String username;
    private String password;

    private RestoreCharacterState state;

    public enum RestoreCharacterState {
        getUsername,
        getPassword,
        newCharacterQuestion,
        restoreDone
    }

    /**
     * Initializes character restoration
     *
     * @param player
     */
    public void RestoreCharacter(Player player) {
        StandardFrame frame = new StandardFrame();
        player.setLastFrame(frame);

        frame.addLine("Welcome to the character restoration screen!");
        frame.newLine();
        frame.addLine("On this screen, you will restore a character.");
        frame.addLine("First, enter your username:");
        frame.newLine();
        player.update();
        player.setState(PlayerStates.characterRestoration);
        this.state = RestoreCharacterState.getUsername;
        this.player = player;
        characterLoaders.add(this);
    }

    /**
     * Retrieves username from user
     */
    public void askUsername() {
        player.getLastFrame().addLine("Please enter your username: ", true);
        this.state = RestoreCharacterState.getUsername;
    }

    /**
     * Verifies that a username is able to be processed
     *
     * @param username
     */
    public void addUsername(String username) {
        File file = new File("Players/" + username + ".player");
        if (file.exists()) {
            player.getLastFrame().addLine("Please enter your password: ", true);
            this.username = username;
            this.state = RestoreCharacterState.getPassword;
        } else {
            player.getLastFrame().addLine("That user doesn't exist. Would you like to try again?", true);
            player.getLastFrame().addLine("Type yes to try again, or no to create a new character.", true);
            this.state = RestoreCharacterState.newCharacterQuestion;
        }
    }

    /**
     * Unlocks a valid user profile
     *
     * @param password
     */
    public void addPassword(String password) {
        File file = new File("Players/" + username + ".player");
        try {
            Scanner scanner = new Scanner(file);
            if (scanner.next().equals(password)) {
                player.getLastFrame().addLine("Password confirmed! Character loaded. Type look to continue.", true);
                loadPlayer();
                this.state = RestoreCharacterState.restoreDone;
            } else {
                player.getLastFrame().addLine("That password is incorrect! Please try again.", true);
                player.getLastFrame().addLine("Please enter your username: ", true);
                this.state = RestoreCharacterState.getUsername;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            addUsername(username);
        }
    }

    /**
     * Rebuilds a player profile from stored stats
     */
    public void loadPlayer() {
        File file = new File("Players/" + username + ".player");
        Scanner scanner;
        try {
            scanner = new Scanner(file);
            scanner.next();
            player.setUsername(username);
            player.setPassword(password);
            player.setBrawn(scanner.nextInt());
            player.setSpiffness(scanner.nextInt());
            player.setSmarts(scanner.nextInt());
            player.setMoxy(scanner.nextInt());
            while (scanner.hasNext()) {
                switch (scanner.next()) {
                    case ("item"):
                        break;
                    case ("weapon"):
                        break;
                    case ("keyItem"):
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method called to remove the CharacterLoading object when the character is finished loading
     *
     * @param loading CharacterLoading: An object used to load a player
     */
    public static void removeCharacterLoading(CharacterLoading loading) {
        characterLoaders.remove(loading);
    }

    /**
     * Method called to find the CharacterLoading object associated with the player
     *
     * @param player Player: the player used to find the associated CharacterLoading
     * @return CharacterLoading: The associated CharacterLoading with the player
     */
    public static CharacterLoading findCharacterLoadingByPlayer(Player player) {
        for (CharacterLoading characterLoader : characterLoaders) {
            if (characterLoader.getPlayer().equals(player)) {
                return characterLoader;
            }
        }
        return null;
    }

    /**
     * Method called to find the state of the CharacterLoading object
     * @return ReturnCharacterState: The state of the object
     */
    public CharacterLoading.RestoreCharacterState getState() {
        return this.state;
    }

    /**
     * Method called to get the player of the CharacterLoading object
     * @return Player: the player of the object
     */
    public Player getPlayer() {
        return this.player;
    }

}
