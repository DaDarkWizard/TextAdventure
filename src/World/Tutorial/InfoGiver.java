package World.Tutorial;

import NPCHandler.NPCRunListener;

import java.util.ArrayList;

/**
 * NPC that has a dialogue
 * <p>
 * Date Last Modified: 12/15/2019
 *
 * @author Daniel Masker, Ben Hodsdon, Emma Smith, Joseph Teahen
 * <p>
 * CS1131, Fall 2019
 * Lab Section 2
 */
public class InfoGiver extends UndyingNPC {
    private ArrayList<String> lines = new ArrayList<String>();  //This NPCs dialogue

    /**
     * Constructor to setup the template
     *
     * @param name the name of the NPC
     */
    public InfoGiver(String name) {
        super(name);
        this.dataStorage = new ArrayList<>();
        //Just picks a random target in combat
        this.npcFindTargetListener = event -> {
            int randomTarget = (int) (Math.random() * event.getSource().getCombatGroup().getCombatants(event.getSource()).size());
            event.getSource().setTarget(event.getSource().getCombatGroup().getCombatants(event.getSource()).get(randomTarget));
        };
        this.dataStorage.add((long) 0);
        this.dataStorage.add(lines);
        this.dataStorage.add(new boolean[2]);
        this.dataStorage.add(new boolean[lines.size()]);
        this.dataStorage.add(null);
        //Runs through all its lines, then set the event to one that could be passed through data storage
        this.npcRunListener = event -> {

            //When this guy started babbling
            long startTime = (long) event.getSource().getDataStorage().get(0);
            //Can't fix this :( The lines to say
            ArrayList<String> storedLines = (ArrayList<String>) event.getSource().getDataStorage().get(1);
            //Which lines have been said
            boolean[] linesSaid = (boolean[]) event.getSource().getDataStorage().get(3);
            //Whether it's started or finished
            boolean[] stopStart = (boolean[]) event.getSource().getDataStorage().get(2);

            //If it's run through, set up this event with the new one
            if (stopStart[1]) {
                if (event.getSource().getDataStorage().size() > 4 &&
                        event.getSource().getDataStorage().get(4) instanceof NPCRunListener) {
                    event.getSource().setNPCRunListener((NPCRunListener) event.getSource().getDataStorage().get(4));
                }
            }

            //Checks if anyone's listening
            if (event.getSource().getRoom().getPlayers().size() < 1) {
                stopStart[0] = false;
                event.getSource().getDataStorage().set(2, stopStart);
                return;
            }

            //Checks if it can start
            if (!stopStart[0] && !stopStart[1]) {
                stopStart[0] = true;
                startTime = System.currentTimeMillis();
                event.getSource().getDataStorage().set(0, startTime);
                event.getSource().getDataStorage().set(2, stopStart);
            }

            //Get how much time has passed since the start
            long timeElapsed = System.currentTimeMillis() - startTime;

            //Speak if not stopped
            if (!stopStart[1]) {

                //Runs through all dialogue options and speaks if enough time has passed
                for (int i = 0; i < storedLines.size(); i++) {

                    long speakTime = 2000; //This is how long they'll wait before speaking
                    speakTime += (i * 2000); //This is how often they will say a line in milliseconds
                    if (timeElapsed > speakTime && !linesSaid[i]) {
                        linesSaid[i] = true;
                        event.getSource().getDataStorage().set(3, linesSaid);
                        event.getSource().say(storedLines.get(i));
                        if (i == storedLines.size() - 1) {
                            stopStart[1] = true;
                            event.getSource().getDataStorage().set(2, stopStart);
                        }
                    }

                }
            }
        };
        addLines();
    }

    /**
     * Updates the data storage with the current lines
     */
    private void addLines() {
        ArrayList<Object> newDataStorage = new ArrayList<>();
        newDataStorage.add((long) 0);
        newDataStorage.add(lines);
        newDataStorage.add(new boolean[2]);
        newDataStorage.add(new boolean[lines.size()]);
        newDataStorage.add(this.dataStorage.get(4));
        this.dataStorage = newDataStorage;
    }

    /**
     * Add a line to the dialogue
     *
     * @param line the line to add
     */
    public void addLine(String line) {
        lines.add(line);
        addLines();
    }
}
