package PlayerHandler.Persistence;

import PlayerHandler.Player;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

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
