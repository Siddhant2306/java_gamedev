package src.Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;


import javax.swing.JPanel;

import src.Entity.Player;

public class GamePanel extends JPanel implements Runnable{  // This is inside the JFrame component which will allow us to draw on the screen
    //Screeen Settings
    final int OriginalTileSize = 32;
    final int scale = 4;    

    public final int TileSize = OriginalTileSize * scale;
    final int maxscreencol = 16;
    final int maxscreenrow = 12;
    final int ScreeenWidth = TileSize * maxscreencol; // 768 pixels 
    final int ScreenHeight = TileSize * maxscreenrow; // 576 pixels

    //FPS
    int Fps = 60;

    Thread gametThread;
    KeyHandler keyHandler = new KeyHandler();

    Player player1 = new Player(100,100,5,this,keyHandler,"Down");
    //Player player2 = new Player(200,200,5,this, keyHandler);

    public GamePanel(){
        this.setPreferredSize(new Dimension(ScreeenWidth,ScreenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);

    }

    public void StartGameThread(){
        gametThread = new Thread(this);
        gametThread.start();
    }


    @Override
    public void run() {

        double drawInterval = 1000000000/Fps;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCounter = 0;
        
        while(gametThread != null){    

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += currentTime - lastTime;
            lastTime = currentTime;

            if(delta >= 1){
            //Update and Draw the screen as per the Fps
                Update();


                repaint();

                delta--;
                drawCounter++;
            }

            if(timer >= 1000000000)
            {
                System.out.println("FPS:" + drawCounter);
                drawCounter = 0;
                timer = 0;
            }
             
        }
    }

    public void Update()
    {
        player1.Update(keyHandler.upPressed_1,keyHandler.downPressed_1,keyHandler.leftPressed_1,keyHandler.rightPressed_1);
        //player2.Update(keyHandler.upPressed_2,keyHandler.downPressed_2,keyHandler.leftPressed_2,keyHandler.rightPressed_2);
    }

    public void paintComponent(Graphics g) // A JPanel Methon To draw Objects on screen(JFrame)
    {
        super.paintComponent(g); //It clears the background so old frames don’t overlap
        Graphics2D g2 = (Graphics2D)g;

        player1.Draw(g2);
        //player2.Draw(g2);

        g2.dispose();
    }
}




