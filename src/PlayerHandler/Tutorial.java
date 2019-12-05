package PlayerHandler;

import PlayerHandler.GamePieces.Room;

import java.io.File;

public class Tutorial {
    private Room start;
    private Room startWest;
    private Room end;

    public Tutorial() {
        this.start = new Room("Tutorial Level",
                "Welcome to Generic Dungeon Crawler 2019!\n" +
                        "This tutorial will help you get familiar with the controls.\n" +
                        "If you know what you're doing, type 'restore' to retrieve your data.\n\n" +
                        "Let's start by learning the controls. GDC is not case sensitive\n" +
                        "To move, simply type 'go (direction)' or just '(direction)'\n" +
                        "Try 'go north' now.",
                "This should never be seen");

        start.setNorth(new Room("Tutorial Level",
                "Okay, now try 'go east'",
                "A room to the north. You should go there!"));

        start.getNorth().setEast(new Room("Tutorial Level",
                "Nice job! Next is 'south'",
                "A room to the east. Isn't it grand? Go there to continue."));

        start.getNorth().getEast().setSouth(new Room("Tutorial Level",
                "I think you've got the hang of this! Last one is 'go west'",
                "A room farther 'south' then you've ever been."));

        startWest = new Room("Tutorial Level",
                "",
                "A room to the West.");

        start.getNorth().getEast().getSouth().setWest(startWest);

        end = new Room("Tutorial Level",
                "The final room",
                "You can see the end.");
        startWest.setSouth(end);
        startWest.setNorth(end);
        startWest.setEast(end);
        startWest.setWest(end);

        end.setNorth(end);
        end.setSouth(end);
        end.setWest(end);
        end.setEast(end);
    }

    public Room getStart() {
        return this.start;
    }

    public Room getEnd() {
        return this.end;
    }
}
