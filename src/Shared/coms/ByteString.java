package Shared.coms;

import Exceptions.ByteUnderflowException;
import sun.security.pkcs11.wrapper.CK_TLS12_KEY_MAT_PARAMS;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class ByteString {
    private short size = -1;
    private byte[] characters;
    private short pointer = 0;
    private String string;
    private boolean complete = false;

    public ByteString() {

    }

    public static void main(String[] args) {

    }

    public static byte[] getBytesFromString(String string) {
        byte[] output = new byte[string.length() * 2];
        byte[] _temp = new byte[2];
        for(int i = 0; i < string.length(); i++) {
            ByteBuffer.wrap(_temp).putChar(string.charAt(i));
            output[(i + 1) * 2 - 2] = _temp[0];
            output[(i + 1) * 2 - 1] = _temp[1];
        }
        return output;
    }

    public ByteString(String string) {
        this.string = string;
        complete = true;
        byte[] characters = getBytesFromString(string);
        size = (short) (characters.length / 2);
    }

    public byte[] getBytes() {
        ByteBuffer buffer = ByteBuffer.allocate(2 + (2 * size));
        buffer.putShort(size);
        buffer.put(characters);
        return buffer.array();
    }

    public boolean isComplete() {
        return complete;
    }

    public String getString() {
        return string;
    }

    public boolean read(InputStream input) throws IOException {
        if (!complete) {
            if (size == -1) {
                if (input.available() > 1) {
                    byte[] _temp = new byte[2];
                    int readBytes = input.read(_temp);
                    if (readBytes < 2) {
                        throw new ByteUnderflowException();
                    }
                    this.size = ByteBuffer.wrap(_temp).getShort();
                    characters = new byte[this.size * 2];
                    return read(input);
                }
                return false;
            } else if (pointer < characters.length){
                if (input.available() > 0) {
                    int readBytes = input.read(characters, pointer, characters.length - pointer);
                    pointer += (short)readBytes;
                    if(pointer == characters.length) {
                        return read(input);
                    }
                }
                return false;
            } else {
                StringBuilder builder = new StringBuilder();
                ByteBuffer bytesToChars = ByteBuffer.wrap(characters);
                for(int i = 0; i < this.size; i++) {
                    builder.append(bytesToChars.getChar());
                }
                this.string = builder.toString();
                complete = true;
                return true;
            }
        } else {
            return true;
        }
    }
}
