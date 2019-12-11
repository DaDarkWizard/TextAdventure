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
                    if (choice > 0 && choice < player.getCombatGroup().getCombatants().size()) {
                        player.setTarget(player.getCombatGroup().getCombatants().get(choice - 1));
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
                        //Todo make flee go back to room you came from if possible, if not possible still in combat
                        player.setState(PlayerStates.normal);
                        player.getCombatGroup().removeCombatant(player);
                        if (scanner.hasNext()) {
                            techAdventure.handle(new ConnectionEvent(ConnectionEventCode.CONNECTION_ESTABLISHED,
                                    player.getConnectionID(), "go " + scanner.nextLine()));


                        } else {
                            int direction = (int) (Math.random() * 4.0);
                            Room exitRoom = null;
                            switch (direction) {
                                case 0:
                                    exitRoom = player.getLocation().getEast();
                                    break;
                                case 1:
                                    exitRoom = player.getLocation().getNorth();
                                    break;
                                case 2:
                                    exitRoom = player.getLocation().getSouth();
                                    break;
                                case 3:
                                    exitRoom = player.getLocation().getWest();
                                    break;
                            }
                            if (exitRoom == null) {
                                player.sendMessage("In a panic you flee, but smack into a wall!");
                                player.sendMessage("Looks like you're stuck here for now..");
                            } else {
                                player.setLocation(exitRoom);
                            }
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
