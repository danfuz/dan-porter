import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;


public class Enemies {
    private int x, y, width, height, direction, speed;
    private BufferedImage pic;
    public Enemies(int xx, int yy, int w, int h, int d, int s){
        x = xx;
        y = yy;
        width = w;
        height = h;
        direction = d;
        speed = s;
        setPic("jiming.png");
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

    public void setPic(String fileName) {
        try {
            pic = ImageIO.read(new File("res/" + fileName));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
