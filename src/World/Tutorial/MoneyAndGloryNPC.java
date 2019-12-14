package World.Tutorial;

import GamePieces.Holdable;
import NPCHandler.DefaultNPC;
import NPCHandler.NPC;
import PlayerHandler.Player;
import PlayerHandler.UI.StandardFrame;

public class MoneyAndGloryNPC extends DefaultNPC {
    public MoneyAndGloryNPC() {
        super("Powerful Mist", Integer.MAX_VALUE / 2);
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
                //player.update();
            }
        };
        this.npcRunListener = event -> {

        };
    }

    public NPC createNPC() {
        return new NPC(this);
    }
}
