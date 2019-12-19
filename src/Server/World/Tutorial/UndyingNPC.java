package Server.World.Tutorial;

import Server.NPCHandler.DefaultNPC;

/**
 * Makes an NPC that is extremely hard to kill
 * <p>
 * Date Last Modified: 12/14/2019
 *
 * @author Daniel Masker, Ben Hodsdon, Emma Smith, Joseph Teahen
 * <p>
 * CS1131, Fall 2019
 * Lab Section 2
 */
public class UndyingNPC extends DefaultNPC {
    /**
     * Constructor for the NPCTemplate
     *
     * @param name the name of the NPC
     */
    public UndyingNPC(String name) {
        super(name, Integer.MAX_VALUE / 2);
    }
}
