package PlayerHandler.UI;

import java.util.ArrayList;

public class SizableFrame extends Frame {
    private ArrayList<String> lines = new ArrayList<>();
    private int height;
    private int width;

    public SizableFrame(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public String getLine(int line) {
        if (line > lines.size() - 1) {
            return "";
        }
        return this.lines.get(line);
    }

    @Override
    public String getOutput() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < lines.size(); i++) {
            stringBuilder.append(lines.get(i));
            if (i < lines.size() - 1) {
                stringBuilder.append("\n");
            }
        }

        return stringBuilder.toString();
    }

    @Override
    public boolean addLine(String line) {
        return addLine(line, false);
    }

    @Override
    public boolean addLine(String line, boolean force) {
        return add(line + "\n", force);
    }

    @Override
    public boolean add(String text) {
        return add(text, false);
    }

    @Override
    public boolean add(String text, boolean force) {
        String s = "";
        if (lines.size() < 1) {
            lines.add("");
        }

        ArrayList<String> newLines = new ArrayList<>();
        newLines.add(lines.get(lines.size() - 1));

        while (text.length() > width || text.contains("\n")) {
            if (text.contains("\n") && text.indexOf("\n") <= width) {
                if (text.length() > 1) {
                    newLines.set(newLines.size() - 1,
                            newLines.get(newLines.size() - 1) +
                                    text.substring(0, text.indexOf("\n") - 1));
                    if (text.indexOf("\n") != text.length() - 1) {
                        text = text.substring(text.indexOf("\n + 1"));
                    }
                } else {
                    text = "";
                }
                newLines.add("");
            } else {
                newLines.set(newLines.size() - 1,
                        newLines.get(newLines.size() - 1) +
                                text.substring(0, width - 1));
                text = text.substring(width);
                newLines.add("");
            }
        }
        newLines.set(newLines.size() - 1, text);

        if (!force && newLines.size() + lines.size() - 1 > height) {
            return false;
        } else {
            lines.set(lines.size() - 1, newLines.get(0));
            newLines.remove(0);
            lines.addAll(newLines);
            while (lines.size() > height) {
                lines.remove(0);
            }
            return true;
        }
    }

    @Override
    public boolean addParagraph(String text, boolean force) {
        text = "\n" + text + "\n";
        return add(text, force);
    }

    @Override
    public boolean addParagraph(String text) {
        return addParagraph(text, false);
    }

    @Override
    public boolean newLine() {
        return add("\n", true);
    }

    @Override
    public void clearFrame() {

    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
