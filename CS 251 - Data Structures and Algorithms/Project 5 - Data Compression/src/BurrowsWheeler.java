import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class BurrowsWheeler {

    static int length;
    static String string;
    static int row;

    //fills suffixes list with all the possible suffixes of the cyclic string and sorts
    private static ArrayList<String> createTable() {
        ArrayList<String> strings = new ArrayList<>(length);
        String cyclic = string;
        strings.add(cyclic);
        for (int i = 1; i < length; i++) {
            String temp = cyclic.substring(1, length);
            temp += cyclic.charAt(0);
            cyclic = temp;
            strings.add(cyclic);
        }
        return strings;
    }

    private static ArrayList<Character> createT(ArrayList<String> strings) {
        ArrayList<Character> t = new ArrayList<>();
        for (String string : strings) {
            t.add(string.charAt(length-1));
        }
        return t;
    }

    private static void readEncode() {
        string = "";
        while (!BinaryStdIn.isEmpty()) {
            string += BinaryStdIn.readChar();
        }
        length = string.length();
    }

    // apply Burrows-Wheeler encoding, reading from standard input and writing to standard output
    public static void encode() {
        readEncode();
        ArrayList<String> strings = createTable();
        strings.sort(String::compareTo);
        int origRow = strings.indexOf(string);
        ArrayList<Character> t = createT(strings);
        BinaryStdOut.write(origRow);
        for (char c : t) {
            BinaryStdOut.write(c);
        }
        BinaryStdOut.flush();
    }

    private static void readDecode() {
        row = BinaryStdIn.readInt();
        while (!BinaryStdIn.isEmpty()) {
            string += BinaryStdIn.readChar();
        }
        length = string.length();
    }

    // apply Burrows-Wheeler decoding, reading from standard input and writing to standard output
    public static void decode() {
        readDecode();
        ArrayList<Character> suffix = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            suffix.add(string.charAt(i));
        }
        ArrayList<Character> prefix = suffix;
        Collections.sort(prefix);

        char decoded[] = new char[length];

        decoded[0] = prefix.get(row);
        decoded[length-1] = suffix.get(row);

        int index = length-1;
        for (int i = index-1; i > 1; i--) {
            int occur = 0;
            Character ch = suffix.get(index);
            for (int j = 0; i < index; i++) {
                if (suffix.get(j) == ch) occur++;
            }
            index = prefix.indexOf(ch) + occur;
            decoded[i] = suffix.get(index);
        }
        for (char c : decoded) {
            BinaryStdOut.write(c);
        }
        BinaryStdOut.flush();
    }

    // if args[0] is '-', apply Burrows-Wheeler encoding
    // if args[0] is '+', apply Burrows-Wheeler decoding
    public static void main(String[] args) {

        if (args[0].equals('-')) {
            encode();
        }
        else if (args[0].equals('+')) {
            decode();
        }
    }
}