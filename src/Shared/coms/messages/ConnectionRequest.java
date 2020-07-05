package Shared.coms.messages;

import Shared.coms.ByteString;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ConnectionRequest implements Message {
    private static final byte id = (byte)0x01;
    private ByteString version;
    private byte[] bytes;
    private final boolean bytesMade = false;

    public ConnectionRequest() {

    }

    public ConnectionRequest(String version) {
        this.version = new ByteString(version);
    }

    @Override
    public byte getMessageId() {
        return id;
    }

    public boolean isComplete() {
        return version.isComplete();
    }

    public String getVersion() {
        return version.getString();
    }

    public boolean read(InputStream inputStream) throws IOException {
        if (!isComplete()) {
            return version.read(inputStream);
        } else {
            return true;
        }
    }

    public boolean write(OutputStream output) throws IOException {
        byte[] _temp = new byte[1];
        _temp[0] = id;
        output.write(_temp);
        output.write(version.getBytes());
        return true;
    }
}
