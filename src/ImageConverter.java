
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *I
 * @author Dmitry
 */
public class ImageConverter {
    
    private BufferedImage originalImage;
    private BufferedImage drawnImage;
    private int resolution;
    
    public void loadImage(String imagePath) throws IOException
    {
        originalImage = ImageIO.read(new File(imagePath));
    }
    public void setResolution(int resolution)
    {
        if (resolution > 0 && resolution <= originalImage.getHeight() && resolution <= originalImage.getWidth())
        {
            this.resolution = resolution;
        }
        
        int newWidth = this.resolution*(Math.round(originalImage.getWidth()/this.resolution));
        int newHeight = this.resolution*(Math.round(originalImage.getHeight()/this.resolution));
        
        int w = originalImage.getWidth();   
        int h = originalImage.getHeight();   
        BufferedImage dimg = new BufferedImage(newWidth, newHeight, originalImage.getColorModel().getTransparency());   
        Graphics2D g = dimg.createGraphics();   
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);   
        g.drawImage(originalImage, 0, 0, newWidth, newHeight, 0, 0, w, h, null);   
        g.dispose();  
        drawnImage = dimg;
    }
    
    public void draw(Graphics graphics){
        int r = 0;
        int g = 0;
        int b = 0;
        
        int numPixelsHor = drawnImage.getWidth()/resolution;
        int numPixelsVert = drawnImage.getHeight()/resolution;
        
        for (int i = 0; i < drawnImage.getWidth(); i += numPixelsHor)
        {
            for (int j = 0; j < drawnImage.getHeight(); j += numPixelsVert)
            {
                for (int x = 0; x < numPixelsHor; x ++)
                {
                    for (int y = 0; y < numPixelsVert; y ++)
                    {
                        int[] pixel = drawnImage.getRaster().getPixel(i+x, j+y, new int[3]);
                        r += pixel[0];
                        g += pixel[1];
                        b += pixel[2];
                    }
                }
                int avgR = r/(numPixelsHor*numPixelsVert);
                int avgG = g/(numPixelsHor*numPixelsVert);
                int avgB = b/(numPixelsHor*numPixelsVert);
                graphics.setColor(new Color(avgR, avgG, avgB));
                graphics.fillRect(i, j, numPixelsHor, numPixelsVert);
                
                r = 0;
                g = 0;
                b = 0;
            }
        }
    }
}
