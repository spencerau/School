import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Purdue CS180 Summer 2017 Project 2 - FileManager Class Skeleton
 * @author Joshua Chambers
 * @version 6/22/2017
 */

public class FileManager {
    //This variable 'filename' is currently useless, try thinking of a reason
    //this would be useful if implementing a more robust save feature...
    private String filename;
    private BufferedImage img;

    /* Constructor */

    public FileManager(String filename) throws IOException, IllegalArgumentException {
        if (filename == null) {
            throw new IllegalArgumentException("FATAL STARTUP: PASSED NULL WHEN CREATING FileManager()");
        }
        URL path = FileManager.class.getResource(filename);
        if (path == null) {
            throw new IOException("FATAL STARTUP: COULD NOT GET PATH FROM INITIAL IMAGE");
        }
        img = ImageIO.read(new File(path.getFile()));
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

    public boolean writeImage(String filename) {
        if (img == null || filename == null) {
            return false;
        } else {
            try {
                ImageIO.write(img, "jpg", new File(filename));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }
    }
}