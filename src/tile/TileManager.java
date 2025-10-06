package src.tile;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.Rectangle;


import src.Main.GamePanel;

public class TileManager {
    
    GamePanel gp;
    public Tile[] tile;
    float scale = 2.0f;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[5];
        getTileImage();
    }

    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].TileImage = ImageIO.read(new File("res/Background/2.png"));
            tile[0].collision = true;

            // Set collider to only cover the bottom fence area
            int fenceHeight = 20; // adjust this to match your fence height
            int fenceY = gp.ScreenHeight - 700; // 200 pixels from the bottom
                tile[0].bounds = new Rectangle(
                0,          // x
                fenceY,     // y: move collider up
                gp.ScreenWidth, // width
                fenceHeight    // height of fence
            );


            tile[0].visible = false; // make it visible for debugging

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Draw(Graphics2D g2) {
        int newWidth  = (int)(tile[0].TileImage.getWidth() / scale);   
        int newHeight = (int)(tile[0].TileImage.getHeight() / scale); 

        if (tile[0].visible) {
            g2.drawImage(tile[0].TileImage, 0, 0, newWidth, newHeight, null);
    
        }
    }
}
