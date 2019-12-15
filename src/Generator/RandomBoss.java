package Generator;

import NPCHandler.Boss;

/**
 * Random boss name generator
 *
 * Date Last Modified: 12/14/2019
 * @author Daniel Masker, Ben Hodsdon, Emma Smith, Joseph Teahen
 *
 * CS1131, fall 2019
 * Lab Section 2
 */
public class RandomBoss {
    /**
     * Constructs a random boss
     * @return the boss
     */
    public Boss boss(){
            String name = RandomFileParser.RandomString("Text/bossName.txt");
            String monster = RandomFileParser.RandomString("Text/bossMonsterNames.txt");
            String color = RandomFileParser.RandomString("Text/color.txt");
            return new Boss(name + " the " + color + " " + monster);
    }
}
