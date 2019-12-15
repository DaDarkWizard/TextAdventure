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

    public abstract String getOutput();

    public abstract boolean addLine(String line);

    public abstract boolean addLine(String line, boolean force);

    public abstract boolean add(String text);

    public abstract boolean add(String text, boolean force);

    public abstract boolean addParagraph(String text, boolean force);

    public abstract boolean addParagraph(String text);

    public abstract boolean newLine();

    public abstract void clearFrame();

    public abstract boolean isEmpty();
}
