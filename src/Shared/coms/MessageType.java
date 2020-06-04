package Shared.coms;

@SuppressWarnings("unused")
public enum MessageType {
    CONNECTION_REQUEST((byte)0x01),
    FATAL_ERROR((byte)0x02),
    CONNECTION_APPROVED((byte)0x03),
    PLAYER_MOVEMENT((byte)0x04),
    NPC_MOVEMENT((byte)0x05),
    NPC_USE_SKILL((byte)0x06),
    PLAYER_USE_SKILL((byte)0x07),
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
                return MessageType.PLAYER_MOVEMENT;
            default: return MessageType.UNKNOWN_TYPE;
        }
    }
}
