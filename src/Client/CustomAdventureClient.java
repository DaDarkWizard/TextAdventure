package Client;

import PlayerHandler.UI.StandardFrame;
import com.sun.xml.internal.bind.v2.runtime.output.StAXExStreamWriterOutput;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sun.awt.FontDescriptor;

import java.awt.font.TextAttribute;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CustomAdventureClient extends Application {
    public static BufferedReader fromServer;
    public static BufferedReader keyboardInput;
    public static TextArea textArea;
    public static PrintWriter toServer;
    StandardFrame frame = new StandardFrame();
    public static ArrayList<String> input = new ArrayList<>();
    public static ArrayList<String> output = new ArrayList<>();
    public InputThread inputThread;

    public static void main(String[] args) {
        CustomAdventureClient.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        inputThread = new InputThread();
        inputThread.start(this.getParameters().getRaw());
        textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setPrefRowCount(32);
        textArea.setPrefColumnCount(100);
        textArea.setStyle("-fx-font-family: 'monospace'");
        TextField textField = new TextField();
        textField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                if (!textField.getText().isEmpty()) {
                    input.add(textField.getText());
                    textField.setText("");
                }
            }
        });

        VBox pane = new VBox();
        pane.getChildren().add(textArea);
        pane.getChildren().add(textField);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (textArea.getText().length() > 10000) {
                    textArea.deleteText(0, 5000);
                }
                while (output.size() > 0) {
                    textArea.appendText(output.remove(0));
                }
                textArea.appendText("");
            }
        };

        Button button = new Button("Reconnect");
        button.setOnAction(event -> {
            inputThread.interrupt();
            inputThread = new InputThread();
            inputThread.start(this.getParameters().getRaw());
        });
        timer.start();

        pane.getChildren().add(button);

        Scene root = new Scene(pane, 800, 600);
        primaryStage.setScene(root);
        primaryStage.setTitle("Generic Dungeon Crawler 2019");
        primaryStage.show();
    }


    private class InputThread extends Thread {
        List<String> args;

        public void start(List args) {
            this.args = args;
            super.start();
        }

        public void run() {
            if (args == null || args.size() != 2) {
                System.out.println("Command line arguments: server_address port");
            } else {
                try (Socket server = new Socket(args.get(0),
                        Integer.valueOf(args.get(1)))) {
                    System.out.println("Connected to AdventureServer host " + server.getInetAddress());
                    fromServer = new BufferedReader(new InputStreamReader(server.getInputStream()));
                    toServer = new PrintWriter(server.getOutputStream(), true);
                    keyboardInput = new BufferedReader(new InputStreamReader(System.in));
                    String s = "";
                    while (true) {
                        if (keyboardInput.ready()) {
                            if ((s = keyboardInput.readLine()) == null) {
                                break;
                            }
                            toServer.println(s);
                        }
                        if (input.size() > 0) {
                            s = input.get(0);
                            input.remove(0);
                            toServer.println(s);
                        }
                        if (fromServer.ready()) {
                            while (fromServer.ready()) {
                                s = fromServer.readLine();
                                if (s == null) {
                                    break;
                                }

                                System.out.println(s);
                                s += "\n";
                                output.add(s);
                            }
                        }


                        //if(textArea != null) {
                        //    textArea.setText("HI");
                        //}
                    }
                    fromServer.close();
                    toServer.close();
                    keyboardInput.close();

                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}