package Tests.MapMaker;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.regex.Pattern;

public class TileChoice extends StackPane {
    private final String id;
    private String location = "File:src/Shared/textures";
    private static final ImageView selector = new ImageView(new Image("File:src/Shared/textures/test/select.png",
            64,
            64,
            true,
            true));
    private final ImageView tile = new ImageView();
    private boolean selected = false;

    public TileChoice(String id) {
        super();
        this.id = id;
        while(id.contains(".")) {
            String[] parts = id.split("\\.");
            location += "/" + parts[0];
            id = parts[1];
        }

        location += "/" + id + ".png";

        tile.setImage(new Image(location,
                64,
                64,
                true,
                true));
        tile.setFitWidth(64);
        tile.setFitHeight(64);
        this.getChildren().add(tile);
    }

    public String getItemId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public void select() {
        if (!this.selected) {
            selector.setFitHeight(64);
            selector.setFitWidth(64);
            this.getChildren().add(selector);
            this.selected = true;
        }
    }

    public void deselect() {
        this.getChildren().remove(selector);
        this.selected = false;
    }
}
