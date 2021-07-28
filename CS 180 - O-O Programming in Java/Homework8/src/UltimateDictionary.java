import java.io.*;
import java.util.*;

/**
 * Created by Spencer on 7/10/2017.
 */
public class UltimateDictionary {

    public static ArrayList<String> readDictionary(String filename, ArrayList<String> buffer) {
        ArrayList<String> list = buffer;
        File file = new File(filename);
        try {
            Scanner s = new Scanner(file);
            while (s.hasNextLine()) {
                String word = s.nextLine();
                if (!list.contains(word)) list.add(word);
            }
            s.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void writeDictionary(String filename, ArrayList<String> buffer) {
        Collections.sort(buffer);
        File file = new File(filename);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (String word : buffer) {
                writer.write(word);
                writer.write('\n');
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
