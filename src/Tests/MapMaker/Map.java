package Tests.MapMaker;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;

public class Map {
    public GridPane grid = new GridPane();
    public ScrollPane scroll = new ScrollPane();

    private int rows = 1;
    private int columns = 1;

    public Map() {
        scroll.setContent(grid);
        grid.setOnMouseClicked(event -> {
            System.out.println("X: " + event.getX() + " Y: " + event.getY());
            if (TileOptions.selected != null) {
                addTile(new TileChoice(TileOptions.selected.getItemId()), (int) event.getX() / 64, (int) event.getY() / 64);
            }
        });

        grid.add(new TileChoice("test.null"), 0, 0);
        grid.add(new TileChoice("test.rock"), 0, 0);
    }

    public Node getNode() {
        return scroll;
    }

    public void addTile(TileChoice tile, int column, int row) {
        grid.add(tile, column, row);
        if ((row + 1 ) == rows) {
            for (int i = 0; i < columns; i++) {
                grid.add(new TileChoice("test.null"), i, rows);
                System.out.println("added tile");
            }
            rows ++;
        }

        if ((column + 1) == columns) {
            for (int i = 0; i < rows; i++) {
                grid.add(new TileChoice("test.null"), columns, i);
                System.out.println("added tile");
            }
            columns++;
        }
    }

}
