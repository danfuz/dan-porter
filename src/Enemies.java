import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;


public class Enemies {
    private int x, y, width, height, direction, speed;
    private Point c;
    private BufferedImage pic;

    public Point getC() {
        return c;
    }

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
        c.setLocation(x,y);
        //System.out.println(tx + " " + ty);
        double ht = -ty+c.y;
        double bt = -tx+c.x;
        double hyp = Math.sqrt(ht*ht+bt*bt);
        double angle = Math.toDegrees(Math.atan(ht/bt));
        //System.out.println(angle);
        if (ht < 0 && bt <=0){
            angle +=180;
        }
        if (ht > 0 && bt <=0){
            angle -=180;
            angle +=360;
        }

        //System.out.println(angle);
        //double a = Math.toDegrees(0);
        //System.out.println(angle);




        g2.translate(x+15, y+15);
        g2.rotate(Math.toRadians(angle));
        g2.translate(-(x+15), -(y+15));


        g2.drawImage(pic, x, y, null);

        g2.translate(x+15, y+15);
        g2.rotate(Math.toRadians(-angle));
        g2.translate(-(x+15), -(y+15));
        //System.out.println(angle);


        //g2.rotate(-currentRotation, loc.x + halfWidth, loc.y + halfHeight);


    }
    public double dist(int tx, int ty){
        double ht = -ty+c.y;
        double bt = -tx+c.x;
        double hyp = Math.sqrt(ht*ht+bt*bt);
        return hyp;
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
        if (new Rectangle(x,y,width,height-15).intersects(o)){
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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
