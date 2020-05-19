package Tests.MapMaker;


import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;

public class Map {
    public GridPane grid = new GridPane();
    public ScrollPane scroll = new ScrollPane();
    private EventHandler<MouseEvent> dragBegin;
    private EventHandler<MouseEvent> dragEnd;
    private double dragBeginX;
    private double dragBeginY;

    private int rows = 1;
    private int columns = 1;

    public Map() {
        scroll.setContent(grid);

        dragEnd = event -> {
            if (TileOptions.selected != null) {
                for (int i = (int) dragBeginX / 64; ;) {
                    for (int j = (int) dragBeginY / 64; ;) {
                        addTile(new TileChoice(TileOptions.selected.getItemId()), i, j);

                        if (dragBeginY <= event.getY()) {
                            j++;
                        } else {
                            j--;
                        }

                        if (dragBeginY <= event.getY()) {
                            if (j > (int) event.getY() / 64) {
                                break;
                            }
                        } else {
                            if (j < (int) event.getY() / 64) {
                                break;
                            }
                        }
                    }

                    if ((dragBeginX <= event.getX())) {
                        i++;
                    } else {
                        i--;
                    }

                    if (dragBeginX <= event.getX()) {
                        if (i > (int) event.getX() / 64) {
                            break;
                        }
                    } else {
                        if (i < (int) event.getX() / 64) {
                            break;
                        }
                    }
                }
            }
        };

        dragBegin = event -> {
            this.dragBeginX = event.getX();
            this.dragBeginY = event.getY();
        };

        //grid.setOnMouseClicked(gridClick);
        grid.setOnMousePressed(dragBegin);
        //grid.setOnMouseDragReleased(dragEnd);
        //grid.setOnMouseDragged(event -> System.out.println("Yote"));
        grid.setOnMouseReleased(dragEnd);

        grid.add(new TileChoice("test.null"), 0, 0);
    }

    public Node getNode() {
        return scroll;
    }

    public void addTile(TileChoice tile, int column, int row) {
        grid.add(tile, column, row);
        if ((row + 1 ) == rows) {
            for (int i = 0; i < columns; i++) {
                grid.add(new TileChoice("test.null"), i, rows);
            }
            rows ++;
        }

        if ((column + 1) == columns) {
            for (int i = 0; i < rows; i++) {
                grid.add(new TileChoice("test.null"), columns, i);
            }
            columns++;
        }
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public void setGrid(GridPane grid) {
        this.grid = grid;
        //this.grid.setOnMouseClicked(gridClick);
        this.grid.setOnMousePressed(dragBegin);
        this.grid.setOnMouseReleased(dragEnd);
        scroll.setContent(grid);
    }
}
