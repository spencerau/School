import java.util.LinkedList;

public class MoveToFront {

	// apply move-to-front encoding, reading from standard input and writing to standard output
	public static void encode() {
		ASCII a = new ASCII();
		LinkedList list = a.createAscii();
		while (!BinaryStdIn.isEmpty()) {
		    Character c = BinaryStdIn.readChar();
			int decAscii = list.indexOf(c);
			BinaryStdOut.write(decAscii);
			list.remove(c);
			list.addFirst(c);
		}
		BinaryStdOut.flush();
	}

	// apply move-to-front decoding, reading from standard input and writing to standard output
	public static void decode() {
		ASCII a = new ASCII();
		LinkedList<String> ascii = a.createAscii();
		while (!BinaryStdIn.isEmpty()) {
			int index = BinaryStdIn.readChar();
			String s = ascii.get(index);
			BinaryStdOut.write(s);
			ascii.remove(s);
			ascii.addFirst(s);
		}
		BinaryStdOut.flush();
	}
	// if args[0] is '-', apply move-to-front encoding
	// if args[0] is '+', apply move-to-front decoding
	public static void main(String[] args) {
		if (args[0].equals("-")) encode();
		if (args[0].equals("+")) decode();
	}
}