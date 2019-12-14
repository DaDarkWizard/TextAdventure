package Generator;

import NPCHandler.Boss;

public class RandomBoss {
        public Boss boss(){
            String name = RandomFileParser.RandomString("Text/bossName.txt");
            String monster = RandomFileParser.RandomString("Text/bossMonsterName.txt");
            String color = RandomFileParser.RandomString("Text/color.txt");
            return new Boss(name + " the " + color + " " + monster);
        }
}
