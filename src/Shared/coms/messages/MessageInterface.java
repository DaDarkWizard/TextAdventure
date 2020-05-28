package Shared.coms.messages;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface MessageInterface {
    byte getMessageId();
    boolean read(InputStream input) throws IOException;
    boolean write(OutputStream output) throws IOException;
}
