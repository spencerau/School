import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Purdue CS180 Summer 2017 Project 3 Skeleton Code
 * @author Joshua Chambers
 * @version 7/3/2017
 */

public class FileManager {
    //This variable 'filename' is currently useless, try thinking of a reason
    //this would be useful if implementing a more robust save feature...
    private String filename;
    private BufferedImage img;

    /**
     * Attempts to read an image file into a BufferedImage.
     * @param filename - filename to read from
     * @throws IOException - Thrown by ImageIO.read() if the image cannot be read
     */
    public FileManager(String filename) throws IOException {
        img = ImageIO.read(getClass().getResource(filename));
        if (img == null) {
            throw new IOException("FATAL STARTUP: COULD NOT READ IMAGE FROM FILE");
        }
        this.filename = filename;
    }

    /* Getter and Setter */

    public BufferedImage getImg() {
        return img;
    }

    public void setImg(BufferedImage img) {
        this.img = img;
    }

    /* Method */

    public void readImage(String filename) throws IOException {
        img = ImageIO.read(getClass().getResource(filename));
        if (img == null) {
            throw new IOException("ERROR: COULD NOT READ IMAGE FROM FILE");
        }
        this.filename = filename;
    }

    /**
     * Writes the BufferedImage 'img' to a file. This is simpler than part 1
     * There is no need to check other parameters as in skeleton code for part 1
     * Simply check args
     *
     * @param args contains either zero parameters (null), one parameter, or two parameters
     *             Zero parameters means save using the current filename, 'jpeg' as filetype
     *             One parameter means save using that parameter, 'jpeg' as filetype
     *             Two parameters means save using the first parameter as the filename
     *                  and the second as the filetype
     * @throws IOException
     */
    public void writeImage(String[] args) throws IOException {
        //TODO: Write code here
        String fileType = "jpeg";
        String fileName;
        switch (args.length) {
            case 0:
                try {
                    File file = new File(filename);
                    ImageIO.write(img, fileType, file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 1:
                fileName = args[0];
                try {
                    File file = new File(fileName);
                    ImageIO.write(img, fileType, file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                fileName = args[0];
                fileType = args[1];
                try {
                    File file = new File(fileName);
                    ImageIO.write(img, fileType, file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }
}