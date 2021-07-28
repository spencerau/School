import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Purdue CS180 Summer 2017 Project 3 Skeleton Code
 * @author Joshua Chambers
 * @version 7/3/2017
 */

public class Filter {
    private BufferedImage img;
    private JFrame frame;
    private JLabel lbl;
    private ImageIcon imgIcon;

    private Object LOCK = new Object();

    /*
    private double[][] mul = new double[3][3];
    private double[][] add = new double[3][3];
    */

    /**
     * Filter Constructor
     * @param img BufferedImage to be used for editing with filters
     */
    public Filter (BufferedImage img) {
        this.img = img;
    }

    public void testFilter() throws ArrayIndexOutOfBoundsException {
        /*
        synchronized (LOCK) {
            try {
                LOCK.wait(1, 1);
            } catch (InterruptedException e) {
                System.out.println("DOUGH");
            }
        }
         */
    }

    /**
     * Brightens the BufferedImage by increasing values of all pixel channels by 25.5
     */
    public void brighten() {
        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {
                Pixel p = new Pixel(img.getRGB(i, j));
                p.setBlue(p.getBlue()+25.5);
                p.setGreen(p.getGreen()+25.5);
                p.setRed(p.getRed()+25.5);
                img.setRGB(i, j, p.toRGB());
            }
        }
    }

    /**
     * Darkens the BufferedImage by decreasing values of all pixel channels by 25.5
     */
    public void darken() {

        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {
                Pixel p = new Pixel(img.getRGB(i, j));
                p.setBlue(p.getBlue()-25.5);
                p.setGreen(p.getGreen()-25.5);
                p.setRed(p.getRed()-25.5);
                img.setRGB(i, j, p.toRGB());
            }
        }

    }

    /**
     * Inverts the BufferedImage by taking 255 minus the current value
     */
    public void inverse() {

        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {
                Pixel p = new Pixel(img.getRGB(i, j));
                p.setBlue(255-p.getBlue());
                p.setGreen(255-p.getGreen());
                p.setRed(255-p.getRed());
                img.setRGB(i, j, p.toRGB());
            }
        }

    }

    /**
     * Grayscales the BufferedImage by taking all three channels, multiplying by
     * certain ratios specified in the handout and summing them.
     */
    public void grayscale() {

        for (int i = 0; i < img.getNumXTiles()*img.getWidth(); i++) {
            for (int j = 0; j < img.getNumYTiles()*img.getHeight(); j++) {
                Pixel p = new Pixel(img.getRGB(i, j));
                double gray = p.getRed()*0.2989 + p.getGreen()*0.5870 + p.getBlue()*0.1140;
                p.setBlue(gray);
                p.setGreen(gray);
                p.setRed(gray);
                img.setRGB(i, j, p.toRGB());
            }
        }

    }

    public void preview() throws IllegalArgumentException {
        if (img == null) {
            throw new IllegalArgumentException("Selection is null! Set and/or update the image properly!");
        }
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                if (frame == null) {
                    frame = new JFrame("Preview");
                    frame.setSize(img.getWidth(), img.getHeight());
                    frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                }

                if (imgIcon == null) {
                    imgIcon = new ImageIcon(img);
                }

                if (lbl == null) {
                    lbl = new JLabel();
                    lbl.setIcon(imgIcon);
                } else {
                    lbl.setIcon(new ImageIcon(img));
                }

                frame.getContentPane().add(lbl, BorderLayout.CENTER);
                frame.setLocationRelativeTo(frame);
                frame.setVisible(true);
            }
        });
    }
}