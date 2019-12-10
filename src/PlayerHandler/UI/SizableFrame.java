package PlayerHandler.UI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.ToDoubleBiFunction;

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
        System.out.println(lines.size());
        for (int i = 0; i < lines.size(); i++) {
            stringBuilder.append(lines.get(i));
            if (i < lines.size() - 1) {
                stringBuilder.append("\n");
            }
        }

        if (lines.size() < height) {
            for (int i = 0; i < height - lines.size(); i++) {
                stringBuilder.append("\n");
            }
        }

        //System.out.println(stringBuilder.toString());
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

    /**
     * Adds a string to the frame
     * DO NOT TOUCH WORKS AS EXPECTED
     *
     * @param text  String to add to the frame
     * @param force Whether or not to force if it doesn't fit
     * @return Whether or not the string is added correctly
     */
    @Override
    public boolean add(String text, boolean force) {
        String s = "";
        if (lines.size() < 1) {
            lines.add("");
        }

        ArrayList<String> newLines = new ArrayList<>();
        newLines.add(lines.get(lines.size() - 1));

        //add all text to newLines
        String[] textSplit = text.split("\n", -1);
        for (int i = 0; i < textSplit.length; i++) {
            String newLine = newLines.get(newLines.size() - 1) + textSplit[i];
            while (newLine.length() > width) {
                newLines.set(newLines.size() - 1, newLine.substring(0, width - 1));
                newLine = newLine.substring(width);
                newLines.add("");
            }
            newLines.set(newLines.size() - 1, newLine);
            if (i < textSplit.length - 1) {
                newLines.add("");
            }
        }

        //Handle adding newlines
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
