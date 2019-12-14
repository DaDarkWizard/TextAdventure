package World.Tutorial;

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

        addLines();
    }

    private void addLines() {
        this.npcRunListener = event -> {
            if (!((boolean[]) this.dataStorage.get(2))[0] && !((boolean[]) this.dataStorage.get(2))[1]) {
                this.dataStorage.set(0, System.currentTimeMillis());
                this.dataStorage.set(2, new boolean[]{true, false});
            }

            ArrayList<String> storedLines;
            boolean[] linesSaid;
            boolean[] stopStart;

            if (!(dataStorage.get(1) instanceof ArrayList) || ((ArrayList) this.dataStorage.get(1)).size() < 1 ||
                    !(((ArrayList) this.dataStorage.get(1)).get(0) instanceof ArrayList)) {
                dataStorage.set(1, new ArrayList<String>());
                //Can't fix this either. :(
                storedLines = (ArrayList<String>) dataStorage.get(1);
            } else {
                //Can't fix this. :(
                storedLines = (ArrayList<String>) dataStorage.get(1);
            }


            for (int i = 0; i < lines.size()
        };
    }

    public void addLine(String line) {
        lines.add(line);
        addLines();
    }
}
