package Tests.MapMaker;

import javafx.event.Event;
import javafx.event.EventType;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.io.File;

public class Chooser extends Pane {

    File file;

    public Chooser() {
        this.minHeight(50);
        this.minWidth(100);

        Pane buttonPane = new Pane();
        this.setPrefHeight(50);
        this.setPrefWidth(100);

        Button newFile = new Button("New File");

        newFile.setOnAction(event -> {
            this.setVisible(false);
        });
    }

}
