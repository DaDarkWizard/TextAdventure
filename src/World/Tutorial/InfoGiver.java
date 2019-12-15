package World.Tutorial;

import NPCHandler.NPCRunListener;
import PlayerHandler.Player;

import java.util.ArrayList;

public class InfoGiver extends UndyingNPC {
    ArrayList<String> lines = new ArrayList<String>();

    public InfoGiver(String name) {
        super(name);
        this.dataStorage = new ArrayList<>();
        this.npcFindTargetListener = event -> {
            int randomTarget = (int) (Math.random() * event.getSource().getCombatGroup().getCombatants(event.getSource()).size());
            event.getSource().setTarget(event.getSource().getCombatGroup().getCombatants(event.getSource()).get(randomTarget));
        };
        this.dataStorage.add((long) 0);
        this.dataStorage.add(lines);
        this.dataStorage.add(new boolean[2]);
        this.dataStorage.add(new boolean[lines.size()]);
        this.dataStorage.add(null);
        this.npcRunListener = event -> {

            long startTime = (long) event.getSource().getDataStorage().get(0);
            //Can't fix this :(
            ArrayList<String> storedLines = (ArrayList<String>) event.getSource().getDataStorage().get(1);
            boolean[] linesSaid = (boolean[]) event.getSource().getDataStorage().get(3);
            boolean[] stopStart = (boolean[]) event.getSource().getDataStorage().get(2);

            if (stopStart[1]) {
                if (event.getSource().getDataStorage().size() > 4 &&
                        event.getSource().getDataStorage().get(4) instanceof NPCRunListener) {
                    event.getSource().setNPCRunListener((NPCRunListener) event.getSource().getDataStorage().get(4));
                }
            }

            if (event.getSource().getRoom().getPlayers().size() < 1) {
                stopStart[0] = false;
                event.getSource().getDataStorage().set(2, stopStart);
                return;
            }

            if (!stopStart[0] && !stopStart[1]) {
                stopStart[0] = true;
                startTime = System.currentTimeMillis();
                event.getSource().getDataStorage().set(0, startTime);
                event.getSource().getDataStorage().set(2, stopStart);
            }


            long timeElapsed = System.currentTimeMillis() - startTime;
            if (!stopStart[1]) {
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

    private void addLines() {
        ArrayList<Object> newDataStorage = new ArrayList<>();
        newDataStorage.add((long) 0);
        newDataStorage.add(lines);
        newDataStorage.add(new boolean[2]);
        newDataStorage.add(new boolean[lines.size()]);
        newDataStorage.add(this.dataStorage.get(4));
        this.dataStorage = newDataStorage;
    }

    public void addLine(String line) {
        lines.add(line);
        addLines();
    }
}
