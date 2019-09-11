import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;


public class Enemies {
    private int x, y, width, height, direction, speed;
    private Point c;
    private BufferedImage pic;
    public Enemies(int xx, int yy, int w, int h, int d, int s){
        x = xx;
        y = yy;
        c = new Point(xx+w/2, yy+h/2);
        width = w;
        height = h;
        direction = d;
        speed = s;
        setPic("jiming.png");
    }
    public void draw(Graphics2D g2, int tx, int ty) {

        double ht = Math.abs(ty-c.y);
        double bt = Math.abs(tx-c.x);
        double hyp = Math.sqrt(ht*ht+bt*bt);


        g2.translate(x+width/2, y+height/2);
        //g2.rotate(30);
        g2.translate(-(x+width/2), -(y+height/2));


        g2.drawImage(pic, x, y, null);

        //g2.rotate(-currentRotation, loc.x + halfWidth, loc.y + halfHeight);


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
