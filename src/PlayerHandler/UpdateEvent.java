package PlayerHandler;

import java.util.EventObject;

public class UpdateEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    private Player source;

    public UpdateEvent(Player source) {
        super(source);
        this.source = source;
    }

    @Override
    public Player getSource() {
        return source;
    }
}
