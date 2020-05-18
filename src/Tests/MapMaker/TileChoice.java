package Tests.MapMaker;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.regex.Pattern;

public class TileChoice extends StackPane {
    private String id;
    private String location = "File:src/Shared/textures";
    private static ImageView selector = new ImageView(new Image("File:src/Shared/textures/test/select.png"));
    private ImageView tile = new ImageView();

    public TileChoice(String id) {
        super();
        this.id = id;
        while(id.contains(".")) {
            System.out.println(id);
            String[] parts = id.split("\\.");
            System.out.println(Arrays.toString(parts));
            location += "/" + parts[0];
            id = parts[1];
        }

        location += "/" + id + ".png";

        tile.setImage(new Image(location));
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
        selector.setFitHeight(64);
        selector.setFitWidth(64);
        this.getChildren().add(selector);
    }

    public void deselect() {
        this.getChildren().remove(selector);
    }
}
