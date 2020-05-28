package Shared.coms;

@SuppressWarnings("unused")
public enum MessageType {
    CONNECTION_REQUEST((byte)0x01),
    FATAL_ERROR((byte)0x02),
    CONNECTION_APPROVED((byte)0x03),
    PLAYER_CONTROL((byte)0x04),
    UNKNOWN_TYPE((byte)0xFF);


    private byte id;

    public byte getTypeID() {
        return id;
    }

    MessageType(byte id) {
        this.id = id;

    }

    public static MessageType getMessageType(byte id) {
        switch (id) {
            case (byte)0x01:
                return MessageType.CONNECTION_REQUEST;
            case (byte)0x02:
                return MessageType.FATAL_ERROR;
            case (byte)0x03:
                return MessageType.CONNECTION_APPROVED;
            case (byte)0x04:
                return MessageType.PLAYER_CONTROL;
            default: return MessageType.UNKNOWN_TYPE;
        }
    }
}
