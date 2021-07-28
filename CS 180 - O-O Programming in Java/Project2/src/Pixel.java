/**
 * This class represents a Pixel in an image
 * @author Joseph Lewis joseph@josephlewis.net
 * @version 2/25/16
 */
public class Pixel {
    private int red;
    private int green;
    private int blue;

    /**
     * Pixel lets you get and set individual channels of a given
     * image pixel
     * @param rgb color
     */
    public Pixel(int rgb) {
        red = (rgb >> 16) & 0xFF;
        green = (rgb >> 8) & 0xFF;
        blue = (rgb >> 0) & 0xFF;
    }

    /**
     * Pixel receives a value for each channel
     * @param red red channel
     * @param green green channel
     * @param blue blue channel
     */
    public Pixel(int red, int green, int blue) {
        this.red = bound(red);
        this.green = bound(green);
        this.blue = bound(blue);
    }

    /**
     * Gets the red channel value of the pixel
     * @return red value
     */
    public int getRed() {
        return red;
    }

    /**
     * Set the value for the red channel
     * @param red value
     */
    public void setRed(double red) {
        this.red = bound(red);
    }

    /**
     * Gets the blue channel value of the pixel
     * @return blue value
     */
    public int getBlue() {
        return blue;
    }
    /**
     * Set the value for the blue channel
     * @param blue value
     */
    public void setBlue(double blue) {
        this.blue = bound(blue);
    }

    /**
     * Gets the green channel value of the pixel
     * @return green value
     */
    public int getGreen() {
        return green;
    }

    /**
     * Set the value for the green channel
     * @param green value
     */
    public void setGreen(double green) {
        this.green = bound(green);
    }

    /**
     * Bounds the given value between 0 and 255
     * @param color int value
     */
    private int bound(double color) {
        if(color > 255)
            return 255;
        if(color < 0)
            return 0;
        return (int) color;
    }

    /**
     * Converts this pixel back into an RGB integer.
     *
     * @return an rgb encoded integer.
     */
    public int toRGB() {
        int output = 0;
        output += (red & 0xFF) << 16;
        output += (green & 0xFF) << 8;
        output += (blue & 0xFF) << 0;

        return output;
    }
}