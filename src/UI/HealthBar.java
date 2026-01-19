package src.UI;


import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.File;

public class HealthBar {

    int x,y;
    BufferedImage HealthBar;
;
    public HealthBar(int x , int y)
    {
        this.x  = x;
        this.y = y;
        try
        {
            HealthBar = ImageIO.read(new File("res/UI/HealthBar.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    BufferedImage flipImage(BufferedImage img) {
        int w = img.getWidth();
        int h = img.getHeight();
        BufferedImage flipped = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = flipped.createGraphics();
        g.drawImage(img, 0, 0, w, h, w, 0, 0, h, null);
        g.dispose();
        return flipped;
    }

    public void draw(Graphics2D g2, boolean flipped)
    {
        int size = 5;

        int newWidth = HealthBar.getWidth() * size;
        int newHeight = HealthBar.getHeight() * size;

        BufferedImage imgToDraw = flipped ? flipImage(HealthBar) : HealthBar;
        g2.drawImage(imgToDraw, x, y,newWidth, newHeight, null);

    }
}
