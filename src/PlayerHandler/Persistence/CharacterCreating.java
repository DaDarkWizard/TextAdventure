package PlayerHandler.Persistence;

import GamePieces.Room;
import PlayerHandler.Player;
import PlayerHandler.PlayerStates;
import PlayerHandler.UI.StandardFrame;

import javax.swing.plaf.nimbus.State;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * This class initializes character creation
 *
 * Date Last Modified: 12/14/2019
 * @author Daniel Masker, Ben Hodsdon, Emma Smith, Joseph Teahen
 *
 * CS1131, fall 2019
 * Lab Section 2
 */
public class CharacterCreating {
    private static ArrayList<CharacterCreating> characterCreators = new ArrayList<>();

    private CreateCharacterState state;

    private Player player;
    private Player newPlayer;

    private String username;
    private String password;


    boolean statsInitialized = false;

    public enum CreateCharacterState {
        getUsername,
        confirmUsername,
        getPassword,
        confirmPassword,
        createCharacterBegin,
        createCharacterGetStats,
        createCharacterEnd
    }

    /**
     * Initializes the character creation panel
     * @param player
     */
    public void CreateCharacter(Player player) {
        if (!(player.getUsername() == null || player.getUsername().equals("") || player.getUsername().equals("Noob"))) {
            throw new PlayerInitializedException();
        }

        StandardFrame frame = new StandardFrame();
        player.setLastFrame(frame);

        frame.addLine("Welcome to the character creation screen!");
        frame.newLine();
        frame.addLine("On this screen, you will create a character.");
        frame.addLine("First, enter a username you would like to use.");
        frame.addLine("Usernames and passwords are CaSe SeNsItIvE!");
        frame.newLine();
        this.state = CreateCharacterState.getUsername;
        player.update();
        player.setState(PlayerStates.characterCreation);
        this.player = player;
        characterCreators.add(this);
    }

    /**
     * Adds a username to persistent memory
     * @param username
     */
    public void addUsername(String username) {
        if (!(new File("Players/" + username + ".player").exists())) {
            this.username = username;
            player.getLastFrame().addLine("Your username is: " + username, true);
            player.getLastFrame().addLine("Is this okay? (yes or no)\n", true);
            this.state = CreateCharacterState.confirmUsername;
        } else {
            player.getLastFrame().addLine("That username is already taken!", true);
            player.getLastFrame().addLine("Please enter another one: \n", true);
        }
    }

    /**
     * Confirms that a username isn't already in use
     * @param confirm
     */
    public void confirmUsername(boolean confirm) {
        if (new File("Players/" + username + ".player").exists()) {
            player.getLastFrame().addLine("Ope! You were too slow.", true);
            player.getLastFrame().addLine("Try entering a new username: ", true);
            this.state = CreateCharacterState.getUsername;
            return;
        }
        if (confirm) {
            this.state = CreateCharacterState.getPassword;
            player.getLastFrame().addLine("Username Confirmed!", true);
            player.getLastFrame().addLine("Your username is now: " + this.username + "\n", true);
            File path = new File("Players/");
            File file = new File("Players/" + username + ".player");
            try {
                path.mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            player.getLastFrame().addLine("Please enter an access code: \n", true);
        } else {
            this.state = CreateCharacterState.getUsername;
            player.getLastFrame().addLine("Please enter your desired username:\n");
        }
    }

    /**
     * Adds a password to a user profile
     * @param password
     */
    public void addPassword(String password) {
        this.password = password;
        player.getLastFrame().addLine("Password entered!", true);
        player.getLastFrame().addLine("Please Enter your password again to confirm: \n");
        this.state = CreateCharacterState.confirmPassword;
    }

    /**
     * Confirms that a password is valid
     * @param password
     */
    public void confirmPassword(String password) {
        if (password.equals(this.password)) {
            player.getLastFrame().addLine("Password confirmed!", true);
            player.getLastFrame().addLine("Your account has been created. Now it is time to make a character!", true);
            this.state = CreateCharacterState.createCharacterBegin;
            this.newPlayer = new Player(System.currentTimeMillis());
            newPlayer.setUsername(this.username);
            newPlayer.setPassword(this.password);
            createCharacterMessage();
        } else {
            player.getLastFrame().addLine("That password is incorrect.", true);
            player.getLastFrame().addLine("Let's try again. Enter a password: \n", true);
            this.state = CreateCharacterState.getPassword;
        }
    }

    /**
     * Initializes and compiles character stat creation
     */
    public void createCharacterMessage() {
        player.getLastFrame().addLine("Please enter the stats you would like.", true);
        player.getLastFrame().addLine("You choose values for Brawn, Spiffness, Smarts, and Moxy.", true);
        player.getLastFrame().addLine("A minimum of 5 must be entered for each value.", true);
        player.getLastFrame().addLine("Choose how to distribute your 40 points.", true);
        player.getLastFrame().addLine("Enter them as follows:", true);
        player.getLastFrame().addLine("15 10 5 10", true);
        this.state = CreateCharacterState.createCharacterGetStats;
    }

    public void enterStats(int[] stats) {
        int sum = 0;
        boolean moreThanFour = true;

        for (int i = 0; i < 4; i++) {
            sum += stats[i];
            if (stats[i] < 5) {
                moreThanFour = false;
            }
        }

        if (sum != 40) {
            player.getLastFrame().addLine("That's not 40!", true);
            player.getLastFrame().addLine("Please try to listen more carefully.", true);
            this.state = CreateCharacterState.createCharacterBegin;
            createCharacterMessage();
            return;
        }

        if (!moreThanFour) {
            player.getLastFrame().addLine("That's not at least 5 per stat!", true);
            player.getLastFrame().addLine("Please try to listen more carefully.", true);
            this.state = CreateCharacterState.createCharacterBegin;
            createCharacterMessage();
            return;
        }

        newPlayer.setBrawn(stats[0]);
        newPlayer.setSpiffness(stats[1]);
        newPlayer.setSmarts(stats[2]);
        newPlayer.setMoxy(stats[3]);

        newPlayer.setConnectionID(player.getConnectionID());
        newPlayer.setLastFrame(player.getLastFrame());
        Player.removePlayer(player);
        newPlayer.setState(PlayerStates.characterCreation);
        this.player = this.newPlayer;
        this.newPlayer = null;
        this.state = CreateCharacterState.createCharacterEnd;

        try {
            File file = new File("Players/" + username + ".player");
            PrintWriter writer = new PrintWriter(file);
            writer.println(password);
            writer.println(stats[0]);
            writer.println(stats[1]);
            writer.println(stats[2]);
            writer.println(stats[3]);
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        player.getLastFrame().addLine("Your character has been successfully created!", true);
        player.getLastFrame().addLine("Type look to continue.", true);
    }

    public static ArrayList<CharacterCreating> getCharacterCreators() {
        return characterCreators;
    }

    public static CharacterCreating findCharacterCreatorByPlayer(Player player) {
        for (CharacterCreating characterCreating : characterCreators) {
            if (characterCreating.getPlayer().equals(player)) {
                return characterCreating;
            }
        }
        return null;
    }

    public Player getPlayer() {
        return this.player;
    }

    public CreateCharacterState getState() {
        return this.state;
    }
}
