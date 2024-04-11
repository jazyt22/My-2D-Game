package main;
import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    //Screen Setting
    final int OriginalTileSize = 16;//16x16 Tile// Must scale due to res being so high tile would be hard to see
    final int Scale = 3;
    final int TileSize = OriginalTileSize * Scale;// makes 48x48 Tile
    final int MaxScreenCol = 16;
    final int MaxScreenRow = 12;
    final int ScreenWidth = TileSize * MaxScreenCol;//768 Pixels
    final int ScreenHeight = TileSize * MaxScreenRow;//576 pixels

    Thread gameThread;
    public GamePanel(){
        this.setPreferredSize(new Dimension(ScreenWidth, ScreenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);

    }

    public void StartGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        while(gameThread != null){

//            System.out.println("The game log is running");
            //1 UPDATE:  Update info as such as character positions
            update();
            //2 DRAW: Draw the screen with update info
            repaint();
        }

    }

    public void update(){

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);


    }

}
