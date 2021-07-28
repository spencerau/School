import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Purdue CS180 Summer 2017 Project 3 Skeleton Code
 * @author Joshua Chambers
 * @version 7/3/2017
 */

public class Photoshop {
    public FileManager fm;
    private BufferedImage selection;
    private BufferedImage cp_selection;
    private int[] imageBounds;

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
     * More getters and setters for testing use only
     * @return
     */
    public BufferedImage getCp_selection() {
        return cp_selection;
    }

    public void setCp_selection(BufferedImage cp_selection) {
        this.cp_selection = cp_selection;
    }

    public int[] getImageBounds() {
        return imageBounds;
    }

    public void setImageBounds(int[] imageBounds) {
        this.imageBounds = imageBounds;
    }

    /**
     * Prints the help menu
     */
    public static void printHelpMenu () {
        //System.out.println("new < filename >");
        System.out.println("load < filename >");
        System.out.println("save [ filename ] | [ filename extension ]");
        System.out.println("rotate");
        System.out.println("flip < h | v >");
        System.out.println("select < x1 y1 x2 y2 >");
        System.out.println("apply");
        //System.out.println("filter < brighten | darken | inverse | grayscale | sepia | custom ");
        System.out.println("filter < brighten | darken | inverse | grayscale >");
        //System.out.println("undo [ number ]");
        //System.out.println("redo [ number ]");
        System.out.println("help");
        System.out.println("exit");
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
     * Attempts to parse a string into valid method calls
     * @param input raw input from user
     * @return true - exit was not input
     *         false - exit was input
     * @throws PhotoshopException - When a valid command is not given
     * @throws IOException - When an image cannot be read from/written to
     */
    public boolean parse(String input) throws PhotoshopException, IOException {
        //TODO: Write code here
        if (input != null || !input.isEmpty()) {
            String[] coms = input.split(" ");
            int size = coms.length;
            if (size < 1 || size > 5) throw new PhotoshopException(12);
            else {
                switch (coms[0]) {
                    case "apply":
                        applyRegion();
                    case "select":
                        if (size != 5) throw new PhotoshopException(12);
                        else {
                            for (int i = 0; i < 4; i++) {
                                imageBounds[i] = Integer.parseInt(coms[i+1]);
                            }
                        }
                        break;
                    case "load":
                        if (size != 2) throw new PhotoshopException(12);
                        fm.readImage(coms[1]);
                        break;
                    case "save":
                        if (size != 3) throw new PhotoshopException(12);
                        String[] info = new String[2];
                        for (int i = 0; i < 2; i++) {
                            info[i] = coms[i+1];
                        }
                        fm.writeImage(info);
                        break;
                    case "rotate":
                        if (size == 1) rotate();
                        else throw new PhotoshopException(31);
                        break;
                    case "flip":
                        if (size != 2) throw new PhotoshopException(12);
                        else {
                            if (coms[1].equals("v")) flipVertical();
                            else if (coms[1].equals("h")) flipHorizontal();
                            else throw new PhotoshopException(20);
                        }
                        break;
                    case "filter":
                        if (size != 2) throw new PhotoshopException(12);
                        else {
                            String com = coms[1];
                            Filter f = new Filter(selection);
                            if (com.equals("brighten")) f.brighten();
                            else if (com.equals("darken")) f.darken();
                            else if (com.equals("inverse")) f.inverse();
                            else if (com.equals("grayscale")) f.grayscale();
                            else throw new PhotoshopException(21);
                        }
                        break;
                    case "help":
                        if (size != 1) throw new PhotoshopException(12);
                        else printHelpMenu();
                        break;
                    case "exit":
                        return false;
                    default:
                        throw new PhotoshopException(11);
                }
            }
        }
        else throw new PhotoshopException(10);
        return false;
    }

    /**
     * Calls parse() until parse() returns false. You may handle Exception and IOException in any way
     * When catching PhotoshopException you must print out its message to standard err using
     *      System.err.println()
     *
     */
    public void run() {
        //TODO: Try to parse, catch PhotoshopException, Exception, IOException
        Scanner s = new Scanner(System.in);
        String input = s.nextLine();
        try {
            while (!parse(input)) {
                input = s.nextLine();
        }
        } catch (PhotoshopException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Flips the BufferedImage 'selection' horizontally
     */
    public void flipHorizontal() {
        //update();
        //preview();
    }

    /**
     * Flips the BufferedImage 'selection' vertically
     */
    public void flipVertical() {
        //update();
        //preview();
    }

    /**
     * Rotates the BufferedImage 'selection' 90 degrees clockwise
     *
     * @throws PhotoshopException - Should be thrown when an image cannot be rotated without compromising
     *                          its dimensions. For simplicity, if the sub-region is not square this
     *                          should be thrown.
     */
    public void rotate() throws PhotoshopException {
        //TODO: Error Check
        if (cp_selection.getHeight() != cp_selection.getWidth()) throw new PhotoshopException(31);
        //update();
        //preview();
    }

    /**
     * Creates a rectangular sub-region of 'selection' with 2 coordinate pairs, cp_selection is
     * set to copy selection and selection is updated to contain the sub-region. It will be advantageous
     * to set imageBounds either in this method or after parsing integers to use in 'apply'
     *
     * @param x1 x coordinate in first coordinate pair (upper left hand corner of rectangular region)
     * @param y1 y coordinate in first coordinate pair (upper left hand corner of rectangular region)
     * @param x2 x coordinate in second coordinate pair (lower right hand corner of rectangular region)
     * @param y2 y coordinate in second coordinate pair (lower right hand corner of rectangular region)
     * @throws PhotoshopException - Thrown when the bounds of x1, y1, x2, y2 do not match a valid
     *                          region inside of 'selection' or when trying to selection a
     *                          sub-region of a sub-region.
     */
    public void selectRegion(int x1, int y1, int x2, int y2) throws PhotoshopException {
        //TODO: Write code here
        if (x1 < 0 || y1 < 0 || x2 > selection.getWidth() || y2 > selection.getHeight()) {
            throw new PhotoshopException(30);
        }
        else {
            if (cp_selection.equals(selection)) throw new PhotoshopException(33);
            else {
                imageBounds[0] = x1;
                imageBounds[1] = y1;
                imageBounds[2] = x2;
                imageBounds[3] = y2;
            }
        }
        //update();
        //preview();
    }

    /**
     * Overlays the sub-region onto the original copy of selection. Once the overlay is complete,
     * updates selection then sets imageBounds and cp_selection to null
     *
     * @throws PhotoshopException - Thrown if a sub-region has not been selected
     *                              hint: use imageBounds or cp_selection
     */
    public void applyRegion() throws PhotoshopException {
        if (cp_selection == null) throw new PhotoshopException(32);
        else {
            for (int i = imageBounds[0]; i < imageBounds[2]; i++) {
                for (int j = imageBounds[1]; j < imageBounds[3]; j++) {
                    selection.setRGB(i,j,cp_selection.getRGB(i,j));
                }

            }
        }
        //TODO: Write code here
        //update();
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
            //photoshop.fm.writeImage("output.jpg");

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
        if (fm == null) return;
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
         