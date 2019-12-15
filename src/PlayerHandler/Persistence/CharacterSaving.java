package PlayerHandler.Persistence;

import PlayerHandler.Player;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Writes a player's profile to file
 *
 * Date Last Modified: 12/14/2019
 * @author Daniel Masker, Ben Hodsdon, Emma Smith, Joseph Teahen
 *
 * CS1131, fall 2019
 * Lab Section 2
 */
public class CharacterSaving {
    public CharacterSaving(Player player) {
        if (player.getUsername() == null || player.getPassword() == null) {
            throw new PlayerUninitializedException();
        }

        File file = new File("Players/" + player.getUsername() + ".player");
        if (!file.exists()) {
            throw new PlayerUnknownException();
        }

        PrintWriter writer = null;
        try {
            writer = new PrintWriter(file);
            writer.println(player.getPassword());
            writer.println(player.getBrawn());
            writer.println(player.getSpiffness());
            writer.println(player.getSmarts());
            writer.println(player.getMoxy());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
