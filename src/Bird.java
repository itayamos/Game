import java.awt.*;

public class Bird extends Rectangle {
    public static final int X_VALUE=200;
    private int x;
    private int y;
    private int width;
    private int height;
    private Color color;

    public Bird(int y, int width, int height, Color color) {
        this.x = X_VALUE;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public static int getXValue() {
        return X_VALUE;
    }


    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public double getHeight() {
        return this.height;
    }


    public Color getColor() {
        return color;
    }

    public void paint(Graphics graphics){
        graphics.setColor(this.color);
        graphics.fillRect(this.x,this.y,this.width,this.height);

    }

    public void fly(){
        this.y=this.y-80;
    }

    public void gravity(){
        this.y++;
    }
    public boolean inBounds(Bird bird){
        boolean checker=true;
        if (bird.y<=0 || bird.getY()>=Main.WINDOW_HEIGHT){
            checker=false;
        }
        return checker;
    }
    public boolean checkCollision(Obstacle obstacle){
        boolean collide=false;
        Rectangle obstacleRect=new Rectangle(
                (int)obstacle.getX(),
                (int)obstacle.getY(),
                (int)obstacle.getWidth(),
                (int)obstacle.getHeight()
        );
        Rectangle playRect=new Rectangle(
                this.x,
                this.y,
                this.width,
                this.height
        );
        if (obstacleRect.intersects(playRect)){
            collide=true;
        }
        return collide;
    }
}

