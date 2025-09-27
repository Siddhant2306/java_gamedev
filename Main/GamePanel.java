package Main;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
        //Screeen Settings
        final int OriginalTileSize = 16;
        final int scale = 3;    

        final int TileSize = OriginalTileSize * scale;
        final int maxscreencol = 16;
        final int maxscreenrow = 12;
        final int ScreeenWidth = TileSize * maxscreencol; // 768 pixels 
        final int ScreenHeight = TileSize * maxscreenrow; // 576 pixels

        Thread gametThread;


        public GamePanel(){
            this.setPreferredSize(new Dimension(ScreeenWidth,ScreenHeight));
            this.setBackground(Color.BLACK);
            this.setDoubleBuffered(true);

        }

        public void StartGameThread(){
            gametThread = new Thread(this);
            gametThread.start();
        }


        @Override
        public void run() {
        
            throw new UnsupportedOperationException("Unimplemented method 'run'");
        }


}
