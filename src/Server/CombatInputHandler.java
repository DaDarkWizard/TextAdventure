package Server;

import Server.CombatHandler.CombatGroup;
import Server.PlayerHandler.Player;
import Server.PlayerHandler.PlayerStates;
import Server.PlayerHandler.UI.CombatFrame;

import java.util.Scanner;

/**
 * Handles input for Combat
 * <p>
 * Date Last Modified: 12/15/2019
 *
 * @author Daniel Masker, Ben Hodsdon, Emma Smith, Joseph Teahen
 * <p>
 * CS1131, Fall 2019
 * Lab Section 2
 */
public class CombatInputHandler {

    /**
     * Handles raw player input
     *
     * @param input  the player input
     * @param player the player inputting
     */
    void handleInput(String input, Player player) {
        //Adds the input to the screen's log if it already exists
        if (player.getLastFrame() instanceof CombatFrame) {
            ((CombatFrame) player.getLastFrame()).addToCombatLog(input);
        }
        //Where in combat is the player?
        switch (player.getCombatGroup().getCombatState()) {
            //The player is choosing a target
            case startCombat:
                try {
                    int choice = Integer.parseInt(input);
                    if (choice > 0 && choice < player.getCombatGroup().getCombatants(player).size() + 1) {
                        player.setTarget(player.getCombatGroup().getCombatants(player).get(choice - 1));
                        player.sendMessage("You are attacking: " + player.getTarget().getName());
                    } else {
                        player.sendMessage("That is not a valid target!");
                    }

                } catch (NumberFormatException ex) {
                    player.sendMessage("[" + input + "]: " + "must be an integer.");
                }
                break;
            //Adds input as an attack
            case words:
                player.getWords().add(input.toLowerCase());
                player.update();
                break;
            //Player is choosing whether to fight, flee, or keep attacking
            case rps:
                Scanner scanner = new Scanner(input.toLowerCase());
                switch (scanner.next()) {
                    case "fight":
                        player.setCombatDecision(CombatGroup.rpsChoice.fight);
                        break;
                    case "flee":
                        //make flee go back to room you came from if possible, if not possible still in combat

                        if (player.getLastLocation() != null) {
                            player.setState(PlayerStates.normal);
                            player.getCombatGroup().removeCombatant(player);
                            player.setLocation(player.getLastLocation());
                            player.update();
                        } else {
                            player.sendMessage("[flee]: You are unable to flee!");
                        }

                        break;
                    case "talk":
                        player.setCombatDecision(CombatGroup.rpsChoice.talk);
                        break;
                }
        }
    }
}
