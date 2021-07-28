import java.util.LinkedList;

/**
 * Created by Spencer on 4/25/2017.
 */

public class ASCII {

    public LinkedList<String> createAscii() {
        LinkedList<String> ascii = new LinkedList<>();
        for (int i = 0; i < 256; i++) {
            String ch = Character.toString((char) i);
            ascii.add(ch);
        }
        return ascii;
    }

    public String convertToHex(int dec) {
        String hex = "";
        String bitString = Integer.toBinaryString(dec);
        while (bitString.length() % 4 != 0) {
            bitString = '0' + bitString;
        }
        for (int i = 0; i < bitString.length(); i += 4) {
            String hexVal = "";
            int val = 0;
            if (bitString.charAt(i) == '1') val += 8;
            if (bitString.charAt(i+1) == '1') val += 4;
            if (bitString.charAt(i+2) == '1') val += 2;
            if (bitString.charAt(i+3) == '1') val += 1;
            if (val >= 10) {
                switch (val) {
                    case 10: hexVal = "A";
                        break;
                    case 11: hexVal = "B";
                        break;
                    case 12: hexVal = "C";
                        break;
                    case 13: hexVal = "D";
                        break;
                    case 14: hexVal = "E";
                        break;
                    case 15: hexVal = "F";
                        break;
                }
            }
            else hexVal = String.valueOf(val);
            hex += hexVal;
        }
        if (Integer.parseInt(hex) < 10) return "0" + hex;
        else return hex;
    }


}
