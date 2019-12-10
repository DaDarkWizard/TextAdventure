package CombatHandler.Weapons;

import CombatHandler.AttackCommands;
import GamePieces.Room;

public abstract class BaseShield extends Weapon {
    public BaseShield(String shortDescription, String longDescription, String[] validNames, Room room) {
        super(shortDescription, longDescription, validNames, room, AttackCommands.block);
    }
}
