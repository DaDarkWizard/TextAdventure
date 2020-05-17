package Shared.world;

import Shared.util.Vector;

@SuppressWarnings("unused")
//
public class Tile {
    protected Vector location;
    protected String id;

    public Tile(Vector location, String id) {
        this.location = location;
        this.id = id;
    }

    public Vector getLocation() {
        return location;
    }

    public void setLocation(Vector location) {
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
