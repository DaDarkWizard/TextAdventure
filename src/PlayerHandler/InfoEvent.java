package PlayerHandler;

import java.util.EventObject;

public class InfoEvent extends EventObject {
    private String message;
    private Player source;

    public InfoEvent(Player source, String message) {
        super(source);
        this.source = source;
        this.message = message;
    }

    @Override
    public Player getSource() {
        return source;
    }

    public String getMessage() {
        return message;
    }
}
