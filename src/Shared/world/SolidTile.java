package Shared.world;

import Shared.util.Vector;

import javax.swing.*;

public class SolidTile extends Tile {
    public SolidTile(Vector location, String id) {
        super(location, id);
    }

    public double getLeft() {
        return this.location.getX();
    }

    public double getRight() {
        return this.location.getX() + 1;
    }

    public double getTop() {
        return this.location.getY();
    }

    public double getBottom() {
        return this.location.getY() + 1;
    }
}
