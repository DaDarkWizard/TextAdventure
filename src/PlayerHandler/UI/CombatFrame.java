package PlayerHandler.UI;

import CombatHandler.AttackCommands;
import PlayerHandler.Player;

import java.util.ArrayList;

public class CombatFrame extends Frame {
    private ArrayList<AttackCommands> attackCommands = new ArrayList<>();
    private int startTimer = 5;
    private Player player;
    private SizableFrame console = new SizableFrame(11, 31);
    private ArrayList<String> combatLog = new ArrayList<>();
    public final int height = 28;
    public final int width = 100;

    public CombatFrame(Player player) {
        this.player = player;
    }

    public void updateStartTimer(int startTimer) {
        this.startTimer = startTimer;
    }

    public void addAttackCommand(AttackCommands commands) {
        attackCommands.add(commands);
    }


    private String getStatString(int line) {
        switch (line) {
            case 2:
                return "Stats   ";
            case 3:
                return "";
            case 4:
                return String.format("%6s:  %2d", "Max HP", player.getMaxHitpoints());
            case 5:
                return String.format("%6s:  %2d", "HP", player.getHitPoints());
            case 6:
                return String.format("%6s:  %2d", "Brawn", player.getBrawn());
            case 7:
                return String.format("%6s:  %2d", "Spiff", player.getSpiffness());
            case 8:
                return String.format("%6s:  %2d", "Smarts", player.getSmarts());
            case 9:
                return String.format("%6s:  %2d", "Moxy", player.getMoxy());
        }
        return null;
    }

    private String getCombatLogString(int line) {
        if (line < 14) {
            throw new IllegalArgumentException();
        } else if (line < 15) {
            return String.format("Combat %-38s", combatLog.size() > 0 ? "> " + combatLog.get(0) : "");
        } else if (line < 16) {
            return String.format(" log:  %-38s", combatLog.size() > 1 ? "> " + combatLog.get(0) : "");
        } else {
            return String.format("       %-38s", combatLog.size() > line - 14 ? "> " + combatLog.get(line - 14) : "");
        }
    }

    private String getCommandsPossibleString(int line) {
        if (line < 13) {
            return "            Commands";
        } else if (line < 14) {
            return "";
        } else if (line < 15) {
            return " inventory (number)";
        } else {
            if (player.getPossibleAttackCommands().size() > line - 15) {
                return " " + player.getPossibleAttackCommands().get(line - 15).toString();
            } else {
                return "";
            }
        }
    }

    private String getConsoleString(int line) {
        return console.getLine(line);
    }

    private String getStartString() {
        if (startTimer > 0) {
            return String.format("Start in:%2d", startTimer);
        } else {
            return "  START!!! ";
        }
    }

    private String getInventoryString(int line) {
        if (line < 0) {
            throw new IllegalArgumentException();
        } else if (line == 0) {
            return "Inventory            ";
        } else if (line == 1) {
            return "";
        } else {
            if (player.getInventory().size() > line - 2) {
                return String.format("%2d. %-27.27s", line - 1, player.getInventory().get(line - 2).getShortDescription());
            } else {
                return "";
            }
        }
    }

    private String getCommandsUsedString(int line) {
        if (line < 12) {
            return String.format("Commands 1.%-7.7s2.%-7.7s3.%-7.7s4.%-7.7s",
                    player.getWords().size() > 0 ? player.getWords().get(0) : "",
                    player.getWords().size() > 1 ? player.getWords().get(1) : "",
                    player.getWords().size() > 2 ? player.getWords().get(2) : "",
                    player.getWords().size() > 3 ? player.getWords().get(3) : "");
        } else {
            return String.format("  Used:  5.%-7.7s6.%-7.7s7.%-7.7s8.%-7.7s",
                    player.getWords().size() > 4 ? player.getWords().get(4) : "",
                    player.getWords().size() > 5 ? player.getWords().get(5) : "",
                    player.getWords().size() > 6 ? player.getWords().get(6) : "",
                    player.getWords().size() > 7 ? player.getWords().get(7) : "");
        }
    }

    private String findFormat(int line) {
        if (line < 0) {
            throw new IllegalArgumentException();
        } else if (line == 0) {
            return String.format("|%-31s| %11s |%31s|",
                    getConsoleString(line), getStartString(), getInventoryString(line));
        } else if (line == 1) {
            return String.format("|%-31s|-------------|%31s", getConsoleString(line), getInventoryString(line));
        } else if (line < 10) {
            return String.format("|%-31s| %11s |%31s|", getConsoleString(line), getStatString(line), getInventoryString(line));
        } else if (line < 11) {
            StringBuilder dash = new StringBuilder();
            for (int i = 0; i < 45; i++) {
                dash.append("-");
            }
            return String.format("|%45s|%31s|", dash.toString(), getInventoryString(line));
        } else if (line < 13) {
            return String.format("|%45s|%31s|", getCommandsUsedString(line), getInventoryString(line));
        } else if (line < 14) {
            StringBuilder dash1 = new StringBuilder();
            StringBuilder dash2 = new StringBuilder();
            for (int i = 0; i < 45; i++) {
                dash1.append("-");
                if (i < 31) {
                    dash2.append("-");
                }
            }
            return String.format("|%45s|%31s|", dash1.toString(), dash2.toString());
        } else if (line < 23) {
            return String.format("|%45s|%-31s|", getCombatLogString(line), getCommandsPossibleString(line));
        } else if (line < 24) {
            StringBuilder dash1 = new StringBuilder();
            StringBuilder dash2 = new StringBuilder();
            for (int i = 0; i < 45; i++) {
                dash1.append("=");
                if (i < 31) {
                    dash2.append("=");
                }
            }
            return String.format("|%45s|%31s|", dash1.toString(), dash2.toString());
        } else {
            return "";
        }
    }

    @Override
    public String getOutput() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < height; i++) {
            output.append(findFormat(i));
            if (i < height - 1) {
                output.append("\n");
            }
        }
        return output.toString();
    }

    private void addToConsole(String s) {
        console.add(s, true);
    }

    @Override
    public boolean addLine(String line) {
        return this.addLine(line, true);
    }

    @Override
    public boolean addLine(String line, boolean force) {
        return console.addLine(line, force);
    }

    @Override
    public boolean add(String text) {
        return console.add(text, true);
    }

    @Override
    public boolean add(String text, boolean force) {
        return console.add(text, force);
    }

    @Override
    public boolean addParagraph(String text, boolean force) {
        return console.addParagraph(text, force);
    }

    @Override
    public boolean addParagraph(String text) {
        return this.addParagraph(text, true);
    }

    @Override
    public boolean newLine() {
        return console.newLine();
    }

    @Override
    public void clearFrame() {
        console.clearFrame();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
