/**
 * Starter code for Processor - the class that processes images.
 * <p>
 * This class manipulated Java BufferedImages, which are effectively 2d arrays
 * of pixels. Each pixel is a single integer packed with 4 values inside it.
 * <p>
 * I have included two useful methods for dealing with bit-shift operators so
 * you don't have to. These methods are unpackPixel() and packagePixel() and do
 * exactly what they say - extract red, green, blue and alpha values out of an
 * int, and put the same four integers back into a special packed integer. 
 * 
 * @author Jordan Cohen and Alex Li
 * @version May 14 2020
 */
import greenfoot.*; 
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.awt.Graphics2D;

public class Processor  
{
    public static GreenfootImage Blueify(BufferedImage bi){
        // Get image size to use in for loops
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();
        // Using array size as limit     
        BufferedImage newBi = new BufferedImage (xSize, ySize, 3);
        for (int x = 0; x < xSize; x++){
            for (int y = 0; y < ySize; y++){
                // Calls method in BufferedImage that returns R G B and alpha values
                // encoded together in an integer
                int rgb = bi.getRGB(x, y);
                // Call the unpackPixel method to retrieve the four integers for
                // R, G, B and alpha and assign them each to their own integer
                int[] rgbValues = unpackPixel (rgb);                
                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];
                //make bluer
                if (blue < 250) blue += 2;
                if (red >= 50) red--;
                if (green >= 50) green--;
                int newColour = packagePixel (red, green, blue, alpha);
                newBi.setRGB (x, y, newColour);
            }
        } 
        return createGreenfootImageFromBI(newBi);
    }

    public static GreenfootImage Redify(BufferedImage bi){
        // Get image size to use in for loops
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();
        // Using array size as limit     
        BufferedImage newBi = new BufferedImage (xSize, ySize, 3);
        for (int x = 0; x < xSize; x++){
            for (int y = 0; y < ySize; y++){
                // Calls method in BufferedImage that returns R G B and alpha values
                // encoded together in an integer
                int rgb = bi.getRGB(x, y);
                // Call the unpackPixel method to retrieve the four integers for
                // R, G, B and alpha and assign them each to their own integer
                int[] rgbValues = unpackPixel (rgb);                
                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];
                //make redder
                if (blue >= 50) blue--;
                if (red < 250) red+=2;
                if (green >= 50) green--;
                int newColour = packagePixel (red, green, blue, alpha);
                newBi.setRGB (x, y, newColour);
            }
        } 
        return createGreenfootImageFromBI(newBi);
    }

    public static GreenfootImage Greenify(BufferedImage bi){
        // Get image size to use in for loops
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();
        // Using array size as limit     
        BufferedImage newBi = new BufferedImage (xSize, ySize, 3);
        for (int x = 0; x < xSize; x++){
            for (int y = 0; y < ySize; y++){
                // Calls method in BufferedImage that returns R G B and alpha values
                // encoded together in an integer
                int rgb = bi.getRGB(x, y);
                // Call the unpackPixel method to retrieve the four integers for
                // R, G, B and alpha and assign them each to their own integer
                int[] rgbValues = unpackPixel (rgb);                
                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];
                //make greener
                if (blue >= 50) blue--;
                if (red >= 50) red--;
                if (green < 250) green+=2;
                int newColour = packagePixel (red, green, blue, alpha);
                newBi.setRGB (x, y, newColour);
            }
        } 
        return createGreenfootImageFromBI(newBi);
    }

    public static GreenfootImage makeWarm(BufferedImage bi, int warmth){
        // Get image size to use in for loops
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();
        // Using array size as limit
        BufferedImage newBi = new BufferedImage (xSize, ySize, 3);
        for (int x = 0; x < xSize; x++)
        {
            for (int y = 0; y < ySize; y++)
            {
                // Calls method in BufferedImage that returns R G B and alpha values
                // encoded together in an integer
                int rgb = bi.getRGB(x, y);
                // Call the unpackPixel method to retrieve the four integers for
                // R, G, B and alpha and assign them each to their own integer
                int[] rgbValues = unpackPixel (rgb);                
                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];
                // make the pic warmer
                if (blue > 50)
                    blue -= (warmth/10);
                if (red < 200)
                    red += (warmth/10);
                if (green > 50)
                    green -= (warmth/10);
                int newColour = packagePixel (red, green, blue, alpha);
                newBi.setRGB (x, y, newColour);
            }
        }
        return createGreenfootImageFromBI(newBi);
    }

    public static GreenfootImage invertColour(BufferedImage bi){
        // Get image size to use in for loops
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();
        // Using array size as limit     
        BufferedImage newBi = new BufferedImage (xSize, ySize, 3);
        for (int x = 0; x < xSize; x++)
        {
            for (int y = 0; y < ySize; y++)
            {
                // Calls method in BufferedImage that returns R G B and alpha values
                // encoded together in an integer
                int rgb = bi.getRGB(x, y);
                // Call the unpackPixel method to retrieve the four integers for
                // R, G, B and alpha and assign them each to their own integer
                int[] rgbValues = unpackPixel (rgb);                
                int alpha = rgbValues[0];
                int red = 255- rgbValues[1];
                int green = 255- rgbValues[2];
                int blue = 255- rgbValues[3];
                int newColour = packagePixel (red, green, blue, alpha);
                newBi.setRGB (x, y, newColour);
            }
        } 
        return createGreenfootImageFromBI(newBi);
    }

    public static GreenfootImage grayScale(BufferedImage bi){
        // Get image size to use in for loops
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();
        // Using array size as limit
        BufferedImage newBi = new BufferedImage (xSize, ySize, 3);
        for (int x = 0; x < xSize; x++)
        {
            for (int y = 0; y < ySize; y++)
            {
                // Calls method in BufferedImage that returns R G B and alpha values
                // encoded together in an integer
                int rgb = bi.getRGB(x, y);
                // Call the unpackPixel method to retrieve the four integers for
                // R, G, B and alpha and assign them each to their own integer
                int[] rgbValues = unpackPixel (rgb);                
                int alpha = rgbValues[0];
                double red = 0.21 * rgbValues[1] + 0.72 * rgbValues[2] + 0.07 * rgbValues[3], green = 0.21 * rgbValues[1] + 0.72 * rgbValues[2] + 0.07 * rgbValues[3], blue = 0.21 * rgbValues[1] + 0.72 * rgbValues[2] + 0.07 * rgbValues[3];
                int red1 = (int) red, green1 = (int)green, blue1 = (int)blue;
                // make the pic black and white
                int newColour = packagePixel (red1, green1, blue1, alpha);
                newBi.setRGB (x, y, newColour);
            }
        }
        return createGreenfootImageFromBI(newBi);
    }

    public static GreenfootImage redOnly(BufferedImage bi){
        // Get image size to use in for loops
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();
        // Using array size as limit     
        BufferedImage newBi = new BufferedImage (xSize, ySize, 3);
        for (int x = 0; x < xSize; x++)
        {
            for (int y = 0; y < ySize; y++)
            {
                // Calls method in BufferedImage that returns R G B and alpha values
                // encoded together in an integer
                int rgb = bi.getRGB(x, y);
                // Call the unpackPixel method to retrieve the four integers for
                // R, G, B and alpha and assign them each to their own integer
                int[] rgbValues = unpackPixel (rgb);                
                int alpha = rgbValues[0];
                int newColour;
                double h = getHue(rgbValues[1],rgbValues[2],rgbValues[3]);
                if(h >= 353 || h <= 12) newColour = packagePixel(rgbValues[1], rgbValues[2], rgbValues[3], alpha);
                else{
                    double red = 0.21 * rgbValues[1] + 0.72 * rgbValues[2] + 0.07 * rgbValues[3], green = 0.21 * rgbValues[1] + 0.72 * rgbValues[2] + 0.07 * rgbValues[3], blue = 0.21 * rgbValues[1] + 0.72 * rgbValues[2] + 0.07 * rgbValues[3];
                    newColour = packagePixel( (int)red, (int)green, (int)blue, alpha);
                }
                newBi.setRGB (x, y, newColour);
            }
        } 
        return createGreenfootImageFromBI(newBi);
    }

    public static GreenfootImage flipHorizontal (BufferedImage bi){
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();

        // Temp image, to store pixels as we reverse everything
        BufferedImage newBi = new BufferedImage (xSize, ySize, 3);
        for(int i = 0; i < xSize; i++){
            for(int j = 0; j < ySize; j++){
                newBi.setRGB(xSize - 1 - i, j, bi.getRGB(i,j));
            }
        }
        return createGreenfootImageFromBI(newBi);
    }

    public static GreenfootImage flipVertical (BufferedImage bi){
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();
        // Temp image, to store pixels as we reverse everything
        BufferedImage newBi = new BufferedImage (xSize, ySize, 3);
        for(int i = 0; i < xSize; i++){
            for(int j = 0; j < ySize; j++){
                newBi.setRGB(i, ySize - 1 - j, bi.getRGB(i,j));
            }
        }
        return createGreenfootImageFromBI(newBi);
    }

    public static GreenfootImage rotate90(BufferedImage bi)
    {
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();

        // Temp image, to store pixels as we reverse everything
        BufferedImage newBi = new BufferedImage (ySize, xSize, 3);
        for(int i = 0; i < xSize; i++){
            for(int j = 0; j < ySize; j++){
                newBi.setRGB(j, i, bi.getRGB(i,ySize-1-j));
            }
        }
        return createGreenfootImageFromBI(newBi);
    }

    public static double getHue(double r, double g, double b){
        double R = r/255, G = g/255, B = b/255;
        double max = Math.max(R, Math.max(G,B)), min = Math.min(R, Math.min(G,B));
        //System.out.println(R + "  " + G + "  " + B);
        //System.out.println(max + "  " + min);
        if(max == min) return 0;
        if(R == max){
            return 60* (((G-B)/(max-min))%6);
        }
        else if(G == max){
            return 60* (((B - R)/(max-min))+2);
        }
        else {
            return 60* (((R - G)/(max-min))+4);
        }
    }

    /**
     * Takes in an rgb value - the kind that is returned from BufferedImage's
     * getRGB() method - and returns 4 integers for easy manipulation.
     * 
     * By Jordan Cohen
     * Version 0.2
     * 
     * @param rgbaValue The value of a single pixel as an integer, representing<br>
     *                  8 bits for red, green and blue and 8 bits for alpha:<br>
     *                  <pre>alpha   red     green   blue</pre>
     *                  <pre>00000000000000000000000000000000</pre>
     * @return int[4]   Array containing 4 shorter ints<br>
     *                  <pre>0       1       2       3</pre>
     *                  <pre>alpha   red     green   blue</pre>
     */
    public static int[] unpackPixel (int rgbaValue)
    {
        int[] unpackedValues = new int[4];
        // alpha
        unpackedValues[0] = (rgbaValue >> 24) & 0xFF;
        // red
        unpackedValues[1] = (rgbaValue >> 16) & 0xFF;
        // green
        unpackedValues[2] = (rgbaValue >>  8) & 0xFF;
        // blue
        unpackedValues[3] = (rgbaValue) & 0xFF;

        return unpackedValues;
    }

    /**
     * Takes in a red, green, blue and alpha integer and uses bit-shifting
     * to package all of the data into a single integer.
     * 
     * @param   int red value (0-255)
     * @param   int green value (0-255)
     * @param   int blue value (0-255)
     * @param   int alpha value (0-255)
     * 
     * @return int  Integer representing 32 bit integer pixel ready
     *              for BufferedImage
     */
    public static int packagePixel (int r, int g, int b, int a)
    {
        int newRGB = (a << 24) | (r << 16) | (g << 8) | b;
        return newRGB;
    }

    public static BufferedImage deepCopy(BufferedImage bi) {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }

    /**
    * Takes in a BufferedImage and returns a GreenfootImage.
    *
    * @param newBi The BufferedImage to convert.
    *
    * @return GreenfootImage A GreenfootImage built from the BufferedImage provided.
    */
   public static GreenfootImage createGreenfootImageFromBI (BufferedImage newBi)
   {   
        GreenfootImage returnImage = new GreenfootImage (newBi.getWidth(), newBi.getHeight());
        BufferedImage backingImage = returnImage.getAwtImage();
        Graphics2D backingGraphics = (Graphics2D)backingImage.getGraphics();
        backingGraphics.drawImage(newBi, null, 0, 0);
        return returnImage;
   }
}
