package Shared.coms.messages;

import Shared.coms.ByteString;
import jdk.jshell.spi.ExecutionControl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FatalError implements Message {
    private static final byte id = (byte)0x02;
    private final ByteString message;

    public FatalError(String message) {
        this.message = new ByteString(message);
    }

    @Override
    public byte getMessageId() {
        return id;
    }

    @Override
    public boolean read(InputStream input) throws IOException {
        throw new RuntimeException();
    }

    @Override
    public boolean write(OutputStream output) throws IOException {
        output.write(new byte[]{id});
        output.write(message.getBytes());
        return true;
    }

    @Override
    public boolean isComplete() {
        return false;
    }
}
