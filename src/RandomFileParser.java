import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RandomFileParser {

    public RandomFileParser(String s) {
    }

    public static String RandomString(String fileName) throws FileNotFoundException {

        File file = new File(fileName);
        Scanner scan = new Scanner(file);
        int fileLength = scan.nextInt();
        String[] array = new String[fileLength];
        double position = Math.random() * fileLength;

        for(int i = 0; i < fileLength; i++){
            array[i] = scan.nextLine();
        }

        return array[(int)position+1];
    }
}

