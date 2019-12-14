package World.Tutorial;

import NPCHandler.DefaultNPC;

public class UndyingNPC extends DefaultNPC {
    public UndyingNPC(String name) {
        super(name, Integer.MAX_VALUE / 2);
    }
}
