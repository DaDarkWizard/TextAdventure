package Tests.MapMaker;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MapMaker extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Chooser fileChooser = new Chooser();
        BorderPane main = new BorderPane();
        main.setCenter(fileChooser);
        main.setRight(new TileOptions());
        Scene scene = new Scene(main, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Map Maker");
        primaryStage.show();
    }
}
