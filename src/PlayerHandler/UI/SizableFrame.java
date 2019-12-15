package PlayerHandler.UI;

import java.util.ArrayList;
/**
 * The class creates a sizable frame
 *
 * Date Last Modified: 12/14/2019
 * @author Daniel Masker, Ben Hodsdon, Emma Smith, Joseph Teahen
 *
 * CS1131, fall 2019
 * Lab Section 2
 */
public class SizableFrame extends Frame {
    private ArrayList<String> lines = new ArrayList<>(); //arrayList of lines
    protected int height; //the height of the frame
    private int width; //the width of the frame

    /**
     * SizableFrame constructor
     *
     * @param height the height
     * @param width  the width
     */
    public SizableFrame(int height, int width) {
        this.height = height;
        this.width = width;
    }

    /**
     * Gets the line
     *
     * @param line the line's index
     * @return the line
     */
    public String getLine(int line) {
        if (line > lines.size() - 1) {
            return "";
        }
        return this.lines.get(line);
    }


    /**
     * Gets the frame as a string
     *
     * @return the frame as a string
     */
    @Override
    public String getOutput() {
        StringBuilder stringBuilder = new StringBuilder();
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

        return stringBuilder.toString();
    }


    /**
     * Adds a line to the frame
     *
     * @param line the line necessary
     * @return if it adds or not
     */
    @Override
    public boolean addLine(String line) {
        return addLine(line, false);
    }

    /**
     * Adds a line to the frame
     *
     * @param line  the line necessary
     * @param force if it deletes a line at the top so it can fit at the bottom or not
     * @return if it adds or not
     */
    @Override
    public boolean addLine(String line, boolean force) {
        return add(line + "\n", force);
    }

    /**
     * Adds text to the frame
     *
     * @param text the text necessary
     * @return if it adds or not
     */
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
                newLines.set(newLines.size() - 1, newLine.substring(0, width));
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

    /**
     * Adds a paragraph to the frame
     *
     * @param text  the paragraph necessary
     * @param force if it deletes a line at the top so it can fit at the bottom or not
     * @return if it adds or not
     */
    @Override
    public boolean addParagraph(String text, boolean force) {
        text = "\n" + text + "\n";
        return add(text, force);
    }

    /**
     * Adds a paragraph to the frame
     *
     * @param text the paragraph necessary
     * @return if it adds or not
     */
    @Override
    public boolean addParagraph(String text) {
        return addParagraph(text, false);
    }

    /**
     * Adds a line to the frame
     *
     * @return if it adds or not
     */
    @Override
    public boolean newLine() {
        return add("\n", true);
    }

    /**
     * Clears the frame
     */
    @Override
    public void clearFrame() {
        ArrayList<String> list = new ArrayList<>();
        list.add("");
        lines = list;
    }

    /**
     * if it is empty
     *
     * @return false
     */
    @Override
    public boolean isEmpty() {
        return false;
    }

    /**
     * Gets the size of the lines arrayList
     *
     * @return the size of the lines arrayList
     */
    public int getCurrentSize() {
        return lines.size();
    }
}
