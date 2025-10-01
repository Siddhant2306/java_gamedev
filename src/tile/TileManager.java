package src.tile;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;

import src.Main.GamePanel;

public class TileManager{
    
    GamePanel gp;
    Tile[] tile;
    double scale = 1280.0 / 3834.0;


    public TileManager(GamePanel gp)
    {
        this.gp = gp;

        tile = new Tile[5];

        getTileImage();
    }

    public void getTileImage()
    {
        try {

            tile[0] = new Tile();
            tile[0].TileImage = ImageIO.read(new File("res/Background/2.png"));
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Draw(Graphics2D g2) 
    {
        

        int newWidth  = (int)(3834 * scale);   // 1280
        int newHeight = (int)(1054 * scale); 

        g2.drawImage(tile[0].TileImage, 350, 180, newWidth,newHeight,null);
    }
}
