package PlayerHandler.Persistence;

import PlayerHandler.Player;
import PlayerHandler.PlayerStates;
import PlayerHandler.UI.StandardFrame;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

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
    }

    public void askUsername() {
        player.getLastFrame().addLine("Please enter your username: ", true);
        this.state = RestoreCharacterState.getUsername;
    }

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

    public void addPassword(String password) {
        File file = new File("Players/" + username + ".player");
        try {
            Scanner scanner = new Scanner(file);
            if (scanner.next().equals(password)) {
                player.getLastFrame().addLine("Password confirmed! Character loaded. Press enter to continue.", true);
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

    public static void removeCharacterLoading(CharacterLoading loading) {
        characterLoaders.remove(loading);
    }

    public static CharacterLoading findCharacterLoadingByPlayer(Player player) {
        for (CharacterLoading characterLoader : characterLoaders) {
            if (characterLoader.getPlayer().equals(player)) {
                return characterLoader;
            }
        }
        return null;
    }

    public CharacterLoading.RestoreCharacterState getState() {
        return this.state;
    }

    public Player getPlayer() {
        return this.player;
    }

}
