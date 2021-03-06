package Shared.coms;

import Exceptions.ByteUnderflowException;


import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class ByteString {
    private short size = -1;
    private byte[] characters;
    private short pointer = 0;
    private String string;
    private boolean complete = false;
    private byte[] bytes;
    boolean bytesMade = false;

    /**
     * Constructor for receiving a message with the ByteString
     */
    public ByteString() {

    }

    /**
     * Converts a string into bytes
     *
     * @param string the String to convert
     * @return A byte array of the string
     */
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

    /**
     * Constructor for using the ByteString to convert a given string
     *
     * @param string the String to convert
     */
    public ByteString(String string) {
        if (string == null) {
            string = "";
        }
        this.string = string;
        complete = true;
        byte[] characters = getBytesFromString(string);
        size = (short) (characters.length / 2);
    }

    /**
     * Converts the ByteString into bytes ready to be streamed
     * @return an array of bytes
     */
    public byte[] getBytes() {
        if (!bytesMade) {
            ByteBuffer buffer = ByteBuffer.allocate(2 + (2 * size));
            buffer.putShort(size);
            buffer.put(characters);
            bytes = buffer.array();
            bytesMade = true;
            return bytes;
        } else {
            return bytes;
        }
    }

    /**
     * Checks whether the ByteString is done being formed
     *
     * @return whether it is complete
     */
    public boolean isComplete() {
        return complete;
    }

    /**
     * Gets a string version of the ByteString
     *
     * @return ByteString as a string
     */
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
