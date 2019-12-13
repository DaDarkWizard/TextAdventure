package PlayerHandler.Persistence;

import PlayerHandler.Player;
import PlayerHandler.PlayerStates;
import PlayerHandler.UI.StandardFrame;

import java.util.ArrayList;

public class CharacterLoading {
    public static ArrayList<CharacterLoading> characterLoaders = new ArrayList<>();

    private Player player;
    private Player newPlayer;
    private RestoreCharacterState restoreCharacterState;

    private String username;
    private String password;

    private enum RestoreCharacterState {
        getUsername,
        getPassword,
        failed,
        success
    }


    public void RestoreCharacter(Player player) {
        StandardFrame frame = new StandardFrame();
        player.setLastFrame(frame);

        frame.addLine("Welcome to the character restoration screen!");
        frame.newLine();
        frame.addLine("On this screen, you will restore a character.");
        frame.addLine("First, enter a username you would like to use.");
        frame.newLine();
        player.update();
        player.setState(PlayerStates.characterRestoration);
    }

    public CharacterLoading findCharacterLoadingByPlayer(Player player) {
        for (CharacterLoading characterLoader : characterLoaders) {
            if (characterLoader.getPlayer().equals(player)) {
                return characterLoader;
            }
        }
        return null;
    }

    public Player getPlayer() {
        return this.player;
    }

}
