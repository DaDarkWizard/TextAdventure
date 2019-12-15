package World.Tutorial;

import GamePieces.Holdable;
import NPCHandler.DefaultNPC;
import NPCHandler.NPC;
import PlayerHandler.Player;
import PlayerHandler.UI.StandardFrame;

/**
 * NPC to stop player from exiting without key
 * <p>
 * Date Last Modified: 12/14/2019
 *
 * @author Daniel Masker, Ben Hodsdon, Emma Smith, Joseph Teahen
 * <p>
 * CS1131, Fall 2019
 * Lab Section 2
 */
public class MoneyAndGloryNPC extends DefaultNPC {
    /**
     * Constructor to set up events
     */
    public MoneyAndGloryNPC() {
        super("Powerful Mist", Integer.MAX_VALUE / 2);

        //Blocks the player if they don't have the key
        this.npcMeetSomeoneListener = event -> {
            Player player = event.getSource().getRoom().getPlayers().get(0);
            boolean hasKey = false;
            for (Holdable holdable : player.getInventory()) {
                if (holdable.getShortDescription().equals("Golden Key")) {
                    hasKey = true;
                }
            }

            if (!hasKey) {
                player.setLocation(player.getLastLocation());
                StandardFrame frame = new StandardFrame();
                frame.add(player.getLocation().getDescription(), true);
                frame.addLine("You attempt to enter the room, but a powerful force pushes you back.\n" +
                        "'Only one with the Golden Key may enter!'");
                player.setLastFrame(frame);
                player.update();
            }
        };
    }

    /**
     * Creates an NPC with this Template\
     *
     * @return a new NPC
     */
    public NPC createNPC() {
        return new NPC(this);
    }
}
