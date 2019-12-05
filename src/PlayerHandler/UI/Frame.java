package PlayerHandler.UI;

public abstract class Frame {
    public final int height = 28;
    public final int width = 100;

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
