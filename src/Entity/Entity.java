package src.Entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {
    public int x, y;
    public int Speed;

    public BufferedImage[] rightFrames; // walking right
    public BufferedImage[] leftFrames;  // walking left
    BufferedImage[] attackRightFrames;
    BufferedImage[] attackLeftFrames;

    public String Direction;
    public Rectangle SolidArea;
    public boolean collision = false;
    boolean attacking = false;

    public int frameIndex = 0;       // which animation frame
    public int frameDelay = 8;       // controls speed of animation
    public int frameCounter = 0;
}

