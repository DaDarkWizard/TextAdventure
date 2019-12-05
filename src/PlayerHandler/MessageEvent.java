package PlayerHandler;

import java.util.EventObject;

public class MessageEvent extends EventObject {
    private Player receiver;
    private String message;

    public MessageEvent(Player source, Player receiver, String message) {
        super(source);
        this.source = source;
        this.receiver = receiver;
        this.message = message;
    }

    public Player getClient() {
        return this.receiver;
    }

    public String getMessage() {
        return this.message;
    }
}
