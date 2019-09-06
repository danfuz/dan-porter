import java.awt.*;
import java.awt.Rectangle;

public class Enemies {
    private int x, y, width, height, direction, speed;
    public Enemies(int xx, int yy, int w, int h, int d, int s){
        x = xx;
        y = yy;
        width = w;
        height = h;
        direction = d;
        speed = s;
    }
    public void move(){
        for (int i = 0; i < speed; i++) {
            x += 1 * Math.cos(Math.toRadians(direction));
            if (x + width >= Main.WIDTH || x <= 0){
                direction +=180;
                x += 1 * Math.cos(Math.toRadians(direction));
            }
        }
    }
    public Rectangle rec(){
        return new Rectangle(x,y,width,height);
    }
    public boolean intersects(Rectangle o){
        if (new Rectangle(x,y,width,height).intersects(o)){
            return true;
        }
        return false;
    }

}
