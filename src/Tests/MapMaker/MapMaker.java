package Tests.MapMaker;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MapMaker extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Map map = new Map();
        Chooser fileChooser = new Chooser(map.getNode());
        BorderPane main = new BorderPane();
        main.setCenter(fileChooser);
        main.setRight(new TileOptions());
        Button save = new Button("Save");
        save.setOnAction(event -> {
            String saveLocation = "Save\\";
            TacGenerator.generate(saveLocation, map.grid, map.getRows(), map.getColumns());
        });

        Button load = new Button("Load");
        load.setOnAction(event -> {
            String loadLocation = "Save\\";
            map.setGrid(TacGenerator.load(loadLocation, map));
        });

        HBox bottomBox = new HBox();
        bottomBox.getChildren().add(save);
        bottomBox.getChildren().add(load);
        main.setBottom(bottomBox);

        Scene scene = new Scene(main, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Map Maker");
        primaryStage.show();
    }
}
