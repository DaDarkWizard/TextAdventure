package Server.World.Tutorial;

import Server.NPCHandler.NPCRunListener;

/**
 * Second dialogue Dragon Slayer Ghost
 * <p>
 * Date Last Modified: 12/14/2019
 *
 * @author Daniel Masker, Ben Hodsdon, Emma Smith, Joseph Teahen
 * <p>
 * CS1131, Fall 2019
 * Lab Section 2
 */
public class DragonSlayerGhost2 extends InfoGiver {

    /**
     * Constructor to set up dialogue
     */
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
        //Custom dialogue
        this.dataStorage.set(4, (NPCRunListener) event -> {
            if ((boolean) event.getSource().getDataStorage().get(5)) {
                event.getSource().getDataStorage().set(5, false);
                event.getSource().getRoom().getPlayers().get(0).sendMessage("[Notice] You hear the voice of the Great Southern Force:\n" +
                        "He's trying to say dequip the sword, it only works against dragons.");
            }
        });
    }
}
