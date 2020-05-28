package Shared.coms;

@SuppressWarnings("unused")
public enum MessageType {
    CONNECTIONREQUEST((byte)0x01),
    FATALERROR((byte)0x02),
    CONNECTIONAPPROVED((byte)0x03);


    private byte id;

    public byte getTypeID() {
        return id;
    }

    MessageType(byte id) {
        this.id = id;
    }
}
