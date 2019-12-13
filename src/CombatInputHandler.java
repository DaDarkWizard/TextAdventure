import CombatHandler.CombatGroup;
import GamePieces.Room;
import PlayerHandler.Player;
import PlayerHandler.PlayerStates;
import PlayerHandler.UI.CombatFrame;
import PlayerHandler.UI.StandardFrame;

import java.util.Scanner;

public class CombatInputHandler {
    private TechAdventure techAdventure;

    public CombatInputHandler(TechAdventure techAdventure) {
        this.techAdventure = techAdventure;
    }

    StandardFrame handleInput(String input, Player player) {
        if (player.getLastFrame() instanceof CombatFrame) {
            ((CombatFrame) player.getLastFrame()).addToCombatLog(input);
        }
        switch (player.getCombatGroup().getCombatState()) {
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
            case words:
                player.getWords().add(input.toLowerCase());
                player.update();
                break;
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
                        } else {
                            player.sendMessage("[flee]: You are unable to flee!");
                        }

                        break;
                    case "talk":
                        player.setCombatDecision(CombatGroup.rpsChoice.talk);
                        break;
                }
        }
        return null;
    }
}
