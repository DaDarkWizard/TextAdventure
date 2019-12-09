package PlayerHandler.UI;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class StandardFrame extends Frame {
    private ArrayList<String> lines = new ArrayList<>();
    public final int height = 28;
    public final int width = 100;
    public StandardFrame() {

    }

    public void trim() {
        while (lines.size() > 30) {
            lines.remove(0);
        }
    }

    @Override
    public String getOutput() {
        trim();
        StringBuilder output = new StringBuilder();
        int count = 0;
        for (String line : lines) {
            output.append(line).append("\r\n");
            count++;
        }
        for (int i = count; i < height; i++) {
            output.append("\r\n");
        }
        return output.toString();
    }

    @Override
    public boolean addParagraph(String text) {
        return addParagraph(text, false);
    }

    public boolean addParagraph(String text, boolean force) {
        ArrayList<String> newLines = new ArrayList<>();
        while (text.contains("\n")) {
            if (text.indexOf("\n") == 0) {
                newLines.add("");
            } else {
                newLines.add(text.substring(0, text.indexOf("\n")));
            }
            if (text.indexOf("\n") + 2 >= text.length()) {
                text = "";
            } else {
                text = text.substring(text.indexOf("\n") + 1);
            }
        }
        newLines.add(text);
        newLines.add("");
        if (newLines.size() + lines.size() > height) {
            if (!force) {
                return false;
            } else {
                lines.addAll(newLines);
                while (lines.size() > height) {
                    lines.remove(0);
                }
                return true;
            }
        } else {
            lines.addAll(newLines);
            return true;
        }
    }

    //@Override
    public boolean addLine(String line) {
        return addLine(line, false);
    }

    //@Override
    public boolean addLine(String line, boolean force) {
        String newLine;
        StringBuilder newBuilder = new StringBuilder();
        for (int i = 0; i < width; i++) {
            newBuilder.append(" ");
        }
        newLine = newBuilder.toString();

        boolean worked = add(line + newLine, force);
        if (worked) {
            lines.set(lines.size() - 1, "");
        }

        return worked;
    }

    @Override
    public boolean add(String text) {
        return add(text, false);
    }

    @Override
    public boolean add(String text, boolean forceAdd) {
        ArrayList<String> newLines = new ArrayList<>();
        if (lines.size() > 0) {
            text = lines.get(lines.size() - 1) + text;
        }
        while (text.length() > width) {
            newLines.add(text.substring(0, width));
            text = text.substring(width + 1);
        }
        newLines.add(text);

        if (lines.size() + newLines.size() - 1 > height) {
            if (!forceAdd) {
                return false;
            } else {
                lines.set(lines.size() - 1, newLines.get(0));
                for (int i = 1; i < newLines.size(); i++) {
                    lines.add(newLines.get(i));
                }
                while (lines.size() > height) {
                    lines.remove(0);
                }
                return true;
            }
        }
        if (lines.size() > 0) {
            lines.set(lines.size() - 1, newLines.get(0));
        } else {
            lines.add(newLines.get(0));
        }
        for (int i = 1; i < newLines.size(); i++) {
            lines.add(newLines.get(i));
        }
        return true;
    }

    @Override
    public boolean newLine() {
        if (lines.size() >= width) {
            return false;
        }
        lines.add("");
        return true;
    }

    @Override
    public void clearFrame() {
        lines = new ArrayList<>();
    }

    @Override
    public boolean isEmpty() {
        return lines.isEmpty();
    }
}
