package PlayerHandler.UI;

/**
 * Contains the abstract for frame management
 *
 * Date Last Modified: 12/14/2019
 * @author Daniel Masker, Ben Hodsdon, Emma Smith, Joseph Teahen
 *
 * CS1131, fall 2019
 * Lab Section 2
 */
public abstract class Frame {

    /**
     * Gets output from the frame
     * @return the frame
     */
    public abstract String getOutput();

    /**
     * Adds a line to the frame
     * @param line the line necessary
     * @return if it adds or not
     */
    public abstract boolean addLine(String line);

    /**
     * Adds a line to the frame
     * @param line the line necessary
     * @param force if it deletes a line at the top so it can fit at the bottom or not
     * @return if it adds or not
     */
    public abstract boolean addLine(String line, boolean force);

    /**
     * Adds a string to the frame
     * @param text the text necessary
     * @return if it adds or not
     */
    public abstract boolean add(String text);

    /**
     * Adds a string to the frame
     * @param text the text necessary
     * @param force if it deletes a line at the top so it can fit at the bottom or not
     * @return if it adds or not
     */
    public abstract boolean add(String text, boolean force);

    /**
     * Adds a paragraph to the frame
     * @param text the paragraph necessary
     * @param force if it deletes a line at the top so it can fit at the bottom or not
     * @return if it adds or not
     */
    public abstract boolean addParagraph(String text, boolean force);

    /**
     * Adds a paragraph to the frame
     * @param text the paragraph necessary
     * @return if it adds or not
     */
    public abstract boolean addParagraph(String text);

    /**
     * Adds a new line
     * @return if it adds or not
     */
    public abstract boolean newLine();

    /**
     * Clears the frame
     */
    public abstract void clearFrame();

    /**
     * Returns true if empty
     * @return true if empty false if not
     */
    public abstract boolean isEmpty();
}
