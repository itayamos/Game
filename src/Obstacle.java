import java.awt.*;

public class Obstacle extends Rectangle{
    public static final int OBSTACLE_WIDTH=60;
    private int x;
    private int y;
    private int height;
    private Color color;
    public Obstacle(int y, int height) {
        this.x=Main.WINDOW_WIDTH-OBSTACLE_WIDTH;
        this.y=y;
        this.height=height;
        this.color=Color.green;
    }

    public static int getObstacleWidth() {
        return OBSTACLE_WIDTH;
    }


    public Color getColor() {
        return this.color;
    }

    public void paint(Graphics graphics){
        graphics.fillRect(this.x,this.y,OBSTACLE_WIDTH,this.height);
        graphics.setColor(this.color);
    }

    public void moveLeft(){
        this.x--;
    }
}
