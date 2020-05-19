package Tests.MapMaker;

public enum Elements {
    fire ((byte)1),
    water ((byte)2),
    earth ((byte)3),
    air ((byte)4);

    public final byte b;

    private Elements(byte b) {
        this.b = b;
    }


}
