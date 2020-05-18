package Tests.MapMaker;

import java.io.*;

public class TacGenerator {

    public static void main(String[] args) {
        TacGenerator.generate();
    }

    public static byte[][] biomes = new byte[10][10];

    public static void makeData() {
        biomes[0][0] = Elements.fire.b;
    }

    public static void generate() {
        File file = new File("A1.tac");

        try (FileOutputStream writer = new FileOutputStream(file)) {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
