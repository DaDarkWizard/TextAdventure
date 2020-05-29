package Shared.coms.messages;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;

public class ConnectionApproved implements Message {
    private static final byte id = 0x03;
    private int clientID;
    private boolean complete = false;

    public ConnectionApproved(int clientID) {
        this.clientID = clientID;
        complete = true;
    }

    @Override
    public byte getMessageId() {
        return id;
    }

    @Override
    public boolean read(InputStream input) throws IOException {
        throw new NotImplementedException();
    }

    @Override
    public boolean write(OutputStream output) throws IOException {
        output.write(new byte[]{id});
        output.write(ByteBuffer.allocate(4).putInt(clientID).array());
        return false;
    }

    @Override
    public boolean isComplete() {
        return complete;
    }
}
