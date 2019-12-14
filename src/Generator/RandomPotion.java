package Generator;

import CombatHandler.AttackCommands;
import CombatHandler.Weapons.Weapon;

public class RandomPotion {
        public Weapon potion(){
            String color = RandomFileParser.RandomString("Text/color.txt");
            return new Weapon(color + " Potion");
        }
}
