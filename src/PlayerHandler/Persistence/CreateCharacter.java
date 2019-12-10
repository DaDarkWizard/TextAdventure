package PlayerHandler.Persistence;

import PlayerHandler.Player;
import PlayerHandler.UI.StandardFrame;

import java.util.ArrayList;

public class CreateCharacter {
    public static ArrayList<CreateCharacter> characterCreations = new ArrayList<>();

    public CreateCharacter(Player player) {
        if (player.getUsername() == null || player.getUsername().equals("")) {
            throw new PlayerInitializedException();
        }

        StandardFrame frame = new StandardFrame();
        player.setLastFrame(frame);

        frame.addLine("Welcome to the character creation screen!");
        frame.newLine();
        frame.addLine("On this screen, you will create a character.");
        frame.addLine("First, enter a username you would like to use.");
        player.update();
    }

    public void run() {

    }

}
