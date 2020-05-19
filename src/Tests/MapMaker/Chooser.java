package Tests.MapMaker;

import javafx.event.Event;
import javafx.event.EventType;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.File;

public class Chooser extends Pane {

    File file;
    Node replacement;

    public Chooser(Node replacement) {
        this.replacement = replacement;
        this.minHeight(50);
        this.minWidth(100);

        Pane buttonPane = new Pane();
        this.setPrefHeight(50);
        this.setPrefWidth(100);

        Button newFile = new Button("New File");

        buttonPane.getChildren().add(newFile);
        this.getChildren().add(buttonPane);
        buttonPane.translateXProperty().bind(this.widthProperty().divide(2).subtract(50));
        buttonPane.translateYProperty().bind(this.heightProperty().divide(2).subtract(25));

        newFile.setOnAction(event -> {
            this.setVisible(false);
            ((BorderPane)this.getParent()).setCenter(replacement);
        });
    }

}
