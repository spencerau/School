import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Scanner;

import static java.awt.image.BufferedImage.TYPE_INT_ARGB;

/**
 * Purdue CS180 Summer 2017 Project 2 - Photoshop Class Skeleton
 * @author Spencer CW Au
 * @version 7/7/2017
 */

public class Photoshop {
    private FileManager fm;
    private BufferedImage selection;

    private JFrame frame;
    private ImageIcon imgIcon;
    private JLabel lbl;

    /**
     * Photoshop Constructor
     * @param fm FileManager Object used for loading and saving a BufferedImage to a file and vise versa
     */
    public Photoshop (FileManager fm) {
        this.fm = fm;
        selection = fm.getImg();
    }

    /**
     * Photoshop Constructor - Used for testing purposes
     */
    public Photoshop (){}

    /**
     * getter for BufferedImage 'selection'
     * @return selection
     */
    public BufferedImage getSelection() {
        return selection;
    }

    /**
     * setter for BufferedImage 'selection'
     * @param selection
     */
    public void setSelection(BufferedImage selection) {
        this.selection = selection;
    }

    /**
     * Prints the help menu
     */
    public static void printHelpMenu () {
        System.out.println("rotate");
        System.out.println("flip < h | v >");
        System.out.println("filter < brighten | darken | inverse | grayscale >");
        System.out.println("help");
        System.out.println("exit");
    }

    /**
     * Utility Function for printing errors in code
     * @param error String to be printed
     */
    public static void printError(String error) {
        System.out.println("ERROR: " + error);
    }

    /**
     * Utility Function for checking if a method has been called
     */
    public void printCompressedStackTrace() {
        StackTraceElement[] traceElements = Thread.currentThread().getStackTrace();
        for (StackTraceElement e: traceElements) {
            System.out.print(e.getMethodName() + ":");
        }
        System.out.println();
    }

    /**------------------------------
     * DO NOT MODIFY ABOVE THIS POINT
     *-----------------------------*/

    /**
     * Runs the bulk of the program. Consists of a if/else parser and method calls to edit
     * the BufferedImage 'selection'
     */
    public void run() {

        //TODO: Write code here

        Filter f = new Filter(selection);
        Scanner s = new Scanner(System.in);
        boolean cont = true;

        do {
            String command = s.nextLine();
            String[] coms = command.split(" ");
            int size = coms.length;
            if (size < 1 || size > 3) printError("Invalid Argument Count");
            else {
                switch (coms[0]) {
                    case "rotate":
                        if (size == 1) rotate();
                        else printError("Invalid Argument Count");
                        break;
                    case "flip":
                        if (size != 2) printError("Invalid Argument Count");
                        else {
                            if (coms[1].equals("v")) flipVertical();
                            else if (coms[1].equals("h")) flipHorizontal();
                            else printError("Invalid Flip");
                        }
                        break;
                    case "filter":
                        if (size != 2) printError("Invalid Argument Count");
                        else {
                            String com = coms[1];
                            if (com.equals("brighten")) f.brighten();
                            else if (com.equals("darken")) f.darken();
                            else if (com.equals("inverse")) f.inverse();
                            else if (com.equals("grayscale")) f.grayscale();
                            else printError("Invalid Filter");
                        }
                        break;
                    case "help":
                        if (size != 1) printError("Invalid Argument Count");
                        else printHelpMenu();
                        break;
                    case "exit":
                        cont = false;
                        break;
                    default:
                        printError("Unknown Command");
                        break;
                }
            }
        }while (cont);

    }

    /**
     * Flips the BufferedImage 'selection' horizontally
     */
    public void flipHorizontal() {
        printCompressedStackTrace();

        //TODO: Write code here

        int width = selection.getWidth();
        int height = selection.getHeight();
        BufferedImage flipImage = new BufferedImage(width, height, selection.getType());
        flipImage = selection;

        for (int y = 0; y < height; y++)
            for (int x = 0; x < width/2; x++)
            {
                int rgb = selection.getRGB(x, y);
                flipImage.setRGB(x, y, selection.getRGB(width-x-1, y));
                flipImage.setRGB(width-x-1, y, rgb);
            }
        selection = flipImage;

        update();
        //preview();
    }

    /**
     * Flips the BufferedImage 'selection' vertically
     */
    public void flipVertical() {
        printCompressedStackTrace();

        //TODO: Write code here

        int width = selection.getWidth();
        int height = selection.getHeight();
        BufferedImage flipImage = new BufferedImage(width, height, selection.getType());
        flipImage = selection;

        for (int x = 0; x < width; x++)
            for (int y = 0; y < height/2; y++)
            {
                int rgb = selection.getRGB(x, y);
                flipImage.setRGB(x, y, selection.getRGB(x, height-y-1));
                flipImage.setRGB(x, height-y-1, rgb);
            }
        selection = flipImage;

        update();
        //preview();
    }

    /**
     * Rotates the BufferedImage 'selection' 90 degrees clockwise
     * You will most likely need to use the API for BufferedImage to properly complete this method
     */
    public void rotate() {
        printCompressedStackTrace();

        //TODO: Write code here

        int width = selection.getWidth();
        int height = selection.getHeight();
        BufferedImage flipImage = new BufferedImage(width, height, selection.getType());

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                //flipImage.setRGB(y, x, selection.getRGB(x, y));
            }
        }
        selection = flipImage;

        update();
        //preview();
    }


    /**------------------------------
     * DO NOT MODIFY BELOW THIS POINT
     *-----------------------------*/

    /**
     * Main method.
     * @param args String array for initial arguments, this is not used.
     */
    public static void main(String[] args) {
        //Declares the Photoshop Object named 'photoshop'
        Photoshop photoshop;

        System.out.println("Initializing...");

        try {
            //Attempts to create a new Photoshop Object and FileManager with a single image 'PurdueLogo.jpg'
            //If you would like to try your own image, replace will full image name here
            photoshop = new Photoshop(new FileManager("PurdueLogo.jpg"));

            //Previews the image before edits begin
            photoshop.preview();

            //Prints dimensions
            System.out.printf("Success! Image has dimensions: %d, %d\n",
                    photoshop.fm.getImg().getWidth(), photoshop.fm.getImg().getHeight());

            //Calls the 'run' method
            photoshop.run();

            //Writes the image to a file called 'output.jpg'
            photoshop.fm.writeImage("output.jpg");

            //Destroys the JFrame
            if (photoshop.frame != null) {
                photoshop.frame.dispose();
            }

            System.exit(0);
        } catch (IOException e) {

            //If the file could not be found or read it is caught here
            e.printStackTrace();
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException i) {

            //If null is passed as an argument to one of the object creations, it is caught here
            i.printStackTrace();
            System.out.println(i.getMessage());
        }
    }

    /**
     * Updates the FileManger image to reflect the most current change.
     * It is important to keep this updated as the most recent change to
     * the FileManger will be what is written to the file upon exit.
     */
    public void update() {
        if (fm == null)
            return;
        else
            fm.setImg(selection);
    }

    /**
     * Initializes or refreshes a JFrame and/or ImageIcon to display 'selection'
     */
    public void preview() throws IllegalArgumentException {
        if (selection == null) {
            throw new IllegalArgumentException("Selection is null! Set and/or update the image properly!");
        }
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                if (frame == null) {
                    frame = new JFrame("Preview");
                    frame.setSize(selection.getWidth(), selection.getHeight());
                    frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                }

                if (imgIcon == null) {
                    imgIcon = new ImageIcon(selection);
                }

                if (lbl == null) {
                    lbl = new JLabel();
                    lbl.setIcon(imgIcon);
                } else {
                    lbl.setIcon(new ImageIcon(selection));
                }

                frame.getContentPane().add(lbl, BorderLayout.CENTER);
                frame.setLocationRelativeTo(frame);
                frame.setVisible(true);
            }
        });
    }
}