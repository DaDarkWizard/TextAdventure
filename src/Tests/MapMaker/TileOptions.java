package Tests.MapMaker;

import Shared.world.Tile;
import javafx.scene.Node;
import javafx.scene.input.PickResult;
import javafx.scene.layout.GridPane;
import sun.rmi.runtime.NewThreadAction;

public class TileOptions extends GridPane {
    boolean left = false;
    int used = 0;
    TileChoice selected;


    public TileOptions() {
        super();
        addTile(new TileChoice("test.boulder"));
        addTile(new TileChoice("test.rock"));
        addTile(new TileChoice("test.grass"));
        addTile(new TileChoice("test.null"));
        addTile(new TileChoice("test.brick"));
        this.setOnMouseClicked(event -> {
            int row = (int) event.getY() / 64;
            int column = (int) event.getX() / 64;
            boolean found = false;
            for (Node tileChoice : this.getChildren()) {
                if(inRowAndColumn(tileChoice, row, column)) {
                    this.selected = (TileChoice) tileChoice;
                    ((TileChoice) tileChoice).select();
                    found = true;
                } else {
                    ((TileChoice) tileChoice).deselect();
                }
            }
            if (!found) {
                this.selected = null;
            }
            System.out.println(this.selected == null ? "null" : this.selected.getItemId());
        });
    }

    public boolean inRowAndColumn(Node child, int row, int column) {
        return getRowIndex(child) == row && getColumnIndex(child) == column;
    }

    public void addTile(TileChoice tile) {
        this.add(tile, (left ? 1 : 0), used);
        if (left) {
            used++;
        }

        left = !left;
    }


}
