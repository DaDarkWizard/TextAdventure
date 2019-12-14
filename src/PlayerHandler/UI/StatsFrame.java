package PlayerHandler.UI;

import PlayerHandler.Player;

public class StatsFrame extends SizableFrame {

    public StatsFrame(Player player) {

        super(9, 107);
        this.addLine("[Stats]:", true);
        this.newLine();
        this.addLine("Brawn: " + player.getBrawn(), true);
        this.addLine("Spiffness: " + player.getSpiffness(), true);
        this.addLine("Smerts: " + player.getSmarts(), true);
        this.addLine("Moxy: " + player.getMoxy(), true);
        this.addLine("Max Hit Points: " + player.getMaxHitpoints(), true);
        this.addLine("Current Hit Points " + player.getHitPoints(), true);
        this.newLine();
        this.addLine("Equipped Weapons: \n");
    }
}
