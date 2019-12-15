package Generator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RandomFileParser {

    private final String RandomString;

    public RandomFileParser(String RandomString) {
        this.RandomString = RandomString;
    }

    public static String RandomString(String fileName) {

        try {
            File file = new File(fileName);
            Scanner scan = new Scanner(file);
            int fileLength = scan.nextInt();
            String[] array = new String[fileLength];
            double position = Math.random() * fileLength;
            scan.nextLine();
            for (int i = 0; i < fileLength; i++) {
                array[i] = scan.nextLine();
            }
            return array[(int) position];
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return "Failed";
    }
}

