package World.Tutorial;

public class StartNPC extends InfoGiver {
    public StartNPC() {
        super("Force of Beginnings");
        this.addLine("Welcome to your new home, adventurer.");
        this.addLine("Let's get you oriented.");
        this.addLine("Start by heading to the north, using the command 'north'");
    }
}
