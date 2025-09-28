package src.Entity;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Entity {
    int x, y;
    int Speed;

    public BufferedImage right1, left1, right2, left2;
    public String Direction;

    public void SpriteImage() throws IOException {
        BufferedImage spriteSheet = ImageIO.read(new File("res/Player1/Walk.png"));
        BufferedImage[] walkFrames = new BufferedImage[8];

        // Each frame is 64x64
        for (int i = 0; i < 8; i++) {
            walkFrames[i] = spriteSheet.getSubimage(i * 64, 0, 64, 64);
        }

        // Assign the frames to your direction variables
        right1 = walkFrames[0]; 
        right2 = walkFrames[1]; 
        left1  = walkFrames[2]; 
        left2  = walkFrames[3]; 
    }
}
