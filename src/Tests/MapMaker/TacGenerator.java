package Tests.MapMaker;

import com.sun.org.apache.xpath.internal.FoundIndex;
import com.sun.xml.internal.bind.v2.model.core.ID;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.*;
import java.nio.ByteBuffer;

public class TacGenerator {

    public static void main(String[] args) {
        //TacGenerator.generate();
        TacGenerator.test();
    }

    public static void test() {
        byte[] bytes = ByteBuffer.allocate(2).putShort((short)400).array();
        System.out.println(bytes.length);
        System.out.println(ByteBuffer.wrap(bytes).getShort());

        System.out.println((int) 'A');
    }
    
    public static byte[][] biomes = new byte[10][10];

    public static void makeData() {
        biomes[0][0] = Elements.fire.b;
    }

    public static void generate(String path, GridPane tiles, int rows, int columns) {
        TileChoice[][][][] chunks = new TileChoice[((columns - 1)/10) + 1][((rows - 1)/10) + 1][10][10];

        for (Node choice : tiles.getChildren()) {
            int col = GridPane.getColumnIndex(choice);
            int row = GridPane.getRowIndex(choice);
            chunks[(col)/10][(row)/10][(col)%10][(row)%10] = (TileChoice) choice;
        }

        IDChanger changer = new IDChanger();

        for (int i = 0; i < chunks.length; i++) {
            for (int j = 0; j < chunks[i].length; j++) {
                boolean allNull = true;
                for (int k = 0; k < 10; k++) {
                    for (int l = 0; l < 10; l++) {
                        if (!(chunks[i][j][k][l] == null)  && !chunks[i][j][k][l].getItemId().equals("test.null")) {
                            allNull = false;
                        }
                    }
                }

                if (!allNull) {
                    try (FileOutputStream writer = new FileOutputStream(new File(path + ((char) (i + 65)) + j + ".tac"))) {
                        for(int k = 0; k < 10; k++) {
                            for (int l = 0; l < 10; l++) {
                                if (chunks[i][j][k][l] != null) {
                                    writer.write(ByteBuffer.allocate(2).putShort(
                                            changer.idToShort.get(chunks[i][j][k][l].getItemId())
                                    ).array());
                                } else {
                                    writer.write(ByteBuffer.allocate(2).putShort((short) 0).array());
                                }
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        File file = new File(path + "META.tac");
        try (FileOutputStream writer = new FileOutputStream(file)) {
            System.out.println("COLS: " + columns + " ROWS: " + rows);
            writer.write(ByteBuffer.allocate(2).putShort((short)rows).array());
            writer.write(ByteBuffer.allocate(2).putShort((short)columns).array());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static GridPane load(String path, Map map) {
        GridPane output = new GridPane();

        int columns = -1;
        int rows = -1;

        try (FileInputStream reader = new FileInputStream(new File(path + "META.tac"))) {
            byte[] number = new byte[2];
            int red = reader.read(number);
            if (red != 2) {
                throw new RuntimeException("AHHHH!!");
            }

            rows = ByteBuffer.wrap(number).getShort();

            red = reader.read(number);
            if (red != 2) {
                throw new RuntimeException("AHHHHH2!");
            }

            columns = ByteBuffer.wrap(number).getShort();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(columns == -1 || rows == -1) {
            throw new RuntimeException("Oh..");
        }


        map.setColumns(((columns - 1)/10 + 1) * 10);
        map.setRows(((rows - 1)/10 + 1) * 10);

        IDChanger idChanger = new IDChanger();

        for(int i = 0; i < (columns - 1)/10 + 1; i++) {
            for (int j = 0; j < (rows - 1) / 10 + 1; j++) {
                File file = new File(path + ((char) (i + 65)) + j + ".tac");
                if (file.exists()) {
                    try (FileInputStream reader = new FileInputStream(file)){
                        for(int k = 0; k < 10; k++) {
                            for (int l = 0; l < 10; l++) {
                                byte[] tile = new byte[2];
                                int red = reader.read(tile);
                                if (red != 2) {
                                    throw new RuntimeException("Reader not red");
                                }
                                output.add(new TileChoice(
                                        idChanger.shortToId.get(ByteBuffer.wrap(tile).getShort())),
                                        (10 * i) + k, (10 * j) + l);
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    for(int k = 0; k < 10; k++) {
                        for (int l = 0; l < 10; l++) {
                            output.add(new TileChoice("test.null"), (10 * i) + k, (10 * j) + l);
                        }
                    }
                }
            }
        }

        return output;
    }
}
