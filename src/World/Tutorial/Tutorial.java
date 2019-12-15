package World.Tutorial;

import CombatHandler.Weapons.Weapon;
import GamePieces.Item;
import GamePieces.Room;
import Generator.WeaponGenerator;
import NPCHandler.Dragon;
import NPCHandler.Grue;
import NPCHandler.NPC;

public class Tutorial {
    private Room start, startWest, startNorth, startEast, startSouth, monsterRoom, dragonSlayer, dragonRoom, moneyAndGlory, end;
    private Item key;

    private NPC dragon, grue;

    public Tutorial() {
        createRooms();
        addItems();
        addNPCs();

        CrankyOldMan crankyOldMan = new CrankyOldMan();
        startNorth.addNPC(new NPC(crankyOldMan));

        dragon = new NPC(new Dragon("Dragon"));
        dragonRoom.addNPC(dragon);

        grue = new NPC(new Grue("Grue"));
        monsterRoom.addNPC(grue);


    }

    private void addNPCs() {
        moneyAndGlory.addNPC(new MoneyAndGloryNPC().createNPC());
        start.addNPC(new NPC(new StartNPC()));

        InfoGiver northForce = new InfoGiver("Great Northern Force");
        northForce.addLine("Head to the east, you fool.");
        startNorth.addNPC(new NPC(northForce));

        InfoGiver northEastForce = new InfoGiver("Great Northeastern Force");
        northEastForce.addLine("If you type 'inventory' you can view your items.");
        northEastForce.addLine("Don't mind the Great Northern Force, he's in a bad mood.");
        northEastForce.addLine("Head on south to continue.");
        startEast.addNPC(new NPC(northEastForce));

        InfoGiver southernForce = new SouthernForce();
        startSouth.addNPC(new NPC(southernForce));

        startWest.addNPC(new NPC(new WesternForce()));

        dragonSlayer.addNPC(new NPC(new DragonSlayerGhost()));
    }

    private void addItems() {
        key = new Key();
        dragonRoom.getInteractables().add(key);
        WeaponGenerator weaponGenerator = new WeaponGenerator();
        startWest.getInteractables().add(weaponGenerator.weapon());
        startWest.getInteractables().add(weaponGenerator.weapon());
    }

    private void createRooms() {
        start = new Room("Tutorial Level",
                "You are in a square blue room with smooth walls.\nIt smells vaguely of adventure.\n" +
                        "On the walls is scrawled 'Welcome to Generic Dungeon Crawler 2019!'\n",
                "This should never be seen");

        startWest = new Room("West Room",
                "You are in a triangle orange room with hairy walls.\nIt smells vaguely of the ocean.\n",
                "A room to the West. It looks like it's orange?");
        startNorth = new Room("North Room",
                "You are in a rectangle yellow room with bricked walls.\nIt smells vaguely of rotting fish.\n",
                "A room to the North. It looks like it's yellow?");
        startEast = new Room("East Room",
                "You are in a circle red room with sharp walls.\nIt smells vaguely of burnt toast.\n",
                "A room to the East. It looks like it's red?");
        startSouth = new Room("South Room",
                "You are in a kite green room with sticky walls.\nIt smells vaguely of wet paint on a hot day.\n",
                "A room to the South. It looks like it's green?");
        monsterRoom = new Room("Monster Room",
                "You are in a regular old square purple room with silky walls.\nIt smells vaguely of perfume of the ones you love.",
                "A room to the West. It looks like it's purple? You can hear growling.");
        dragonSlayer = new Room("Dragon Slayer Room",
                "You are in a asteroid white room with icy walls.\nIt smells vaguely of sulfur.",
                "A room to the South. It looks like its's white?");
        dragonRoom = new Room("Dragon Room",
                "You are in a pentagram black room with pointy walls.\nIt smells vaguely of rotting onions.",
                "A room to the East. It looks like it's black? You can hear a roar.");
        moneyAndGlory = new Room("Money and Glory Room",
                "You are in a octagon gold room with shiny walls\nIt smells vaguely of glory.",
                "A room to the West. It looks like its gold?");

        start.setNorth(startNorth);
        start.setWest(moneyAndGlory);

        moneyAndGlory.setEast(start);

        startNorth.setEast(startEast);
        startNorth.setSouth(start);

        startEast.setSouth(startSouth);
        startEast.setWest(startNorth);

        startSouth.setWest(startWest);
        startSouth.setNorth(startEast);

        startWest.setWest(monsterRoom);
        startWest.setEast(startSouth);

        monsterRoom.setSouth(dragonSlayer);
        monsterRoom.setEast(startWest);

        dragonSlayer.setEast(dragonRoom);
        dragonSlayer.setNorth(monsterRoom);

        dragonRoom.setWest(dragonSlayer);
    }


    public Room getStart() {
        return this.start;
    }

    public Room getEnd() {
        return this.end;
    }
}
