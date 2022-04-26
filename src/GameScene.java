import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class GameScene extends JPanel implements KeyListener {
    public static final int FAST_GAME=10;
    public static final int SLOW_GAME=100;
    public static final int MARGIN=200;
    private boolean flag;
    private Bird player;
    private Obstacle obstacle;
    private ArrayList<Obstacle> UpperObstacle;
    private Obstacle upper;
    private Obstacle bottom;
    private ArrayList<Obstacle> BottomObstacle;
    public GameScene(int x, int y, int width, int height){
        this.setBounds(x,y,width,height);
        this.setBackground(Color.cyan);
        this.obstacle=new Obstacle(200,200);
        this.player=new Bird(Main.WINDOW_HEIGHT/5,60,60,Color.RED);
        this.BottomObstacle=new ArrayList<>();
        this.UpperObstacle=new ArrayList<>();
        this.mainGameLoop();
        this.obstacleCreator();
        this.setDoubleBuffered(true);
        //this.RemoveObstacle();
        this.flag=false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.player.paint(g);
        this.obstacle.paint(g);
        for (int i=0; i<UpperObstacle.size();i++){
            UpperObstacle.get(i).paint(g);
            BottomObstacle.get(i).paint(g);
        }

    }

    private void mainGameLoop() {
        new Thread(() -> {
            while (player.inBounds(player)) {
                for (Obstacle value : UpperObstacle) {
                    value.moveLeft();
                }
                for (Obstacle value : BottomObstacle) {
                    value.moveLeft();
                    ;
                }
                player.gravity();
                //obstacle.moveLeft();
                repaint();
                if (this.player.checkCollision(this.obstacle)){
                    System.out.println("stop");
                }
                try {
                    Thread.sleep(FAST_GAME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void obstacleCreator(){
        new Thread(()->{
            while (true) {
                Random random = new Random();
                int t = random.nextInt(300) + 200;
                int BottomY =t+MARGIN;
                int UpperHeight = t;
                upper = new Obstacle(0, UpperHeight);
                bottom = new Obstacle(BottomY, BottomY);
                if ((this.UpperObstacle.isEmpty()&& this.BottomObstacle.isEmpty())||
                        (this.UpperObstacle.get(0).getX()==250 && this.BottomObstacle.get(0).getX()==250)) {
                    this.UpperObstacle.add(upper);
                    this.BottomObstacle.add(bottom);
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    public void CreateObstacle(Obstacle upper,Obstacle bottom){
        Random random=new Random();
        int BottomY=random.nextInt((int) (Main.WINDOW_HEIGHT/2-this.player.getHeight()));
        int UpperHeight=(int) (Main.WINDOW_HEIGHT-BottomY-this.player.getHeight());
        upper=new Obstacle(0,UpperHeight);
        bottom=new Obstacle(BottomY,BottomY);

        if ((this.UpperObstacle.isEmpty()&&this.BottomObstacle.isEmpty())||
                (this.UpperObstacle.get(0).getX()==500 && this.BottomObstacle.get(0).getX()==500)){
            this.UpperObstacle.add(upper);
            this.BottomObstacle.add(bottom);
        }
    }
    public void RemoveObstacle(){
        for(int i=0; i<UpperObstacle.size() && i<BottomObstacle.size(); i++){
        if (UpperObstacle.get(0).getX()==0 && BottomObstacle.get(0).getX()==0) {
            UpperObstacle.remove(i);
            BottomObstacle.remove(i);
        }
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_SPACE){
            player.fly();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
