package World.Tutorial;

import NPCHandler.NPCRunListener;

public class DragonSlayerGhost2 extends InfoGiver {
    public DragonSlayerGhost2() {
        super("Sir Michael the Rubix's Cube Knight");
        this.addLine("My friend, you have won! But your trials aren't done.");
        this.addLine("There's a last fight, yes, only one - ");
        this.addLine("Before trials are ended and you can praise the sun.");
        this.addLine("I see what you should fear! A Dire Bat here");
        this.addLine("just passed to the north, wings at high gear.");
        this.addLine("and now I must warn you that the sword you hold dear.");
        this.addLine("Though it did just fine against the beast as the sign");
        this.addLine("said it would, 'gainst the bat would cause you to dine");
        this.addLine("in Valhalla if you tried to hold the line.");
        this.dataStorage.add(true);
        this.dataStorage.set(4, (NPCRunListener) event -> {
            if ((boolean) event.getSource().getDataStorage().get(5)) {
                event.getSource().getDataStorage().set(5, false);
                event.getSource().getRoom().getPlayers().get(0).sendMessage("[Notice] You hear the voice of the Great Southern Force:\n" +
                        "He's trying to say dequip the sword, it only works against dragons.");
            }
        });
    }
}
