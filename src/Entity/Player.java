package src.Entity;

import src.Main.KeyHandler;
import src.tile.Tile;
import src.tile.TileManager;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import src.Main.GamePanel;


public class Player extends Entity {

    public static final int worldX = 0;
    GamePanel gp;
    KeyHandler keyHandler;
    int playerScale = 2;
    int playerNumber;
    boolean attackTriggered = false;

    PlayerState state = PlayerState.IDLE;

    public Player(int x, int y, int Speed, GamePanel gp, KeyHandler keyHandler, String Direction, int playerNumber) {
        this.x = x;
        this.y = y;
        this.Speed = Speed;
        this.gp = gp;
        this.keyHandler = keyHandler;
        this.Direction = Direction;
        this.playerNumber = playerNumber;

        SolidArea = new Rectangle();
        SolidArea.x = 0;
        SolidArea.y = 0;
        SolidArea.width = gp.TileSize;
        SolidArea.height = gp.TileSize;

        getPlayerImage();

        if (playerNumber == 1) { 
            getAttackImages();
        }
    }

    public Rectangle getHitBox() {
        return new Rectangle(x + SolidArea.x, y + SolidArea.y, SolidArea.width, SolidArea.height);
    }

    // Load movement frames
    private void getPlayerImage() {
        try {
            rightFrames = new BufferedImage[8];
            leftFrames = new BufferedImage[8];

            String folderPath = (playerNumber == 1) ? "res/Player1/walk_" : "res/Player2/player2_walk";

            for (int i = 0; i < 8; i++) {
                rightFrames[i] = ImageIO.read(new File(folderPath + (i + 1) + ".png"));
                leftFrames[i] = flipImage(rightFrames[i]);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load attack frames (only player1)
    private void getAttackImages() {
        try {
            attackRightFrames = new BufferedImage[6];
            attackLeftFrames = new BufferedImage[6];

            String folderPath = "res/Player1/attack_";

            for (int i = 0; i < 6; i++) {
                attackRightFrames[i] = ImageIO.read(new File(folderPath + (i + 1) + ".png"));
                attackLeftFrames[i] = flipImage(attackRightFrames[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Flip horizontally
    private BufferedImage flipImage(BufferedImage img) {
        int w = img.getWidth();
        int h = img.getHeight();
        BufferedImage flipped = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = flipped.createGraphics();
        g.drawImage(img, 0, 0, w, h, w, 0, 0, h, null);
        g.dispose();
        return flipped;
    }

    // Update player
public void Update(boolean up, boolean down, boolean left, boolean right, TileManager tileManager, Player otherPlayer) {

    int newX = x;
    int newY = y;

    if (up) newY -= Speed;
    if (down) newY += Speed;
    if (left) newX -= Speed;
    if (right) newX += Speed;

    // Future hitbox for collision check
    Rectangle futureBox = new Rectangle(newX + SolidArea.x, newY + SolidArea.y, SolidArea.width, SolidArea.height);

    // Tile collision
    boolean collisionWithTile = false;
    for (Tile t : tileManager.tile) {
        if (t != null && t.collision && futureBox.intersects(t.bounds)) {
            collisionWithTile = true;
            break;
        }
    }

    // Player collision
    boolean collisionWithPlayer = futureBox.intersects(otherPlayer.getHitBox());


    if (!collisionWithTile && !collisionWithPlayer) {
        x = newX;
        y = newY;
    }

    if (playerNumber == 1) { 
        if (keyHandler.basicAttack && !attackTriggered && state != PlayerState.ATTACKING) {
            state = PlayerState.ATTACKING;
            frameIndex = 0;
            frameCounter = 0;
            attackTriggered = true; // only trigger once per key press
        }
        if (!keyHandler.basicAttack) {
            attackTriggered = false; // reset when key released
        }
    }

  
    frameCounter++;

    int currentLength = 1; // default for IDLE
    switch (state) {
        case WALKING: currentLength = rightFrames.length; break;
        case ATTACKING: currentLength = attackRightFrames.length; break;
        case IDLE: currentLength = 1; break;
    }

    if (frameCounter >= frameDelay) {
        frameIndex++;
        frameCounter = 0;

        if (frameIndex >= currentLength) {
            if (state == PlayerState.ATTACKING) state = PlayerState.IDLE; // reset after attack
            frameIndex = 0;
        }
    }

   
    if (state != PlayerState.ATTACKING) { // only update walking direction if not attacking
        boolean moving = up || down || left || right;
        if (moving) state = PlayerState.WALKING;
        else state = PlayerState.IDLE;

        if (left) Direction = "left";
        if (right) Direction = "right";
    }
}


    // Draw player
    public void Draw(Graphics2D g2) {
        int drawX = x;
        int drawY = y;

        BufferedImage image;

        if (state == PlayerState.ATTACKING) {
            image = "right".equals(Direction) ? attackRightFrames[frameIndex] :
                "left".equals(Direction) ? attackLeftFrames[frameIndex] :
                 attackRightFrames[0];

                    // Adjust Y so feet stay aligned
                    int walkHeight = rightFrames[0].getHeight() * playerScale;
                    int attackHeight = image.getHeight() * playerScale;
                    drawY += walkHeight - attackHeight;

        } else {
            image = "right".equals(Direction) ? rightFrames[frameIndex] :
                "left".equals(Direction) ? leftFrames[frameIndex] :
                rightFrames[0];
        }

        int newWidth = image.getWidth() * playerScale;
        int newHeight = image.getHeight() * playerScale;

        g2.drawImage(image, drawX, drawY, newWidth, newHeight, null);
    }

}
