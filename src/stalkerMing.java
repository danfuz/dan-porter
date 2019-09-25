import java.awt.*;
import java.awt.image.BufferedImage;

public class stalkerMing extends Enemies{
    private int x, y, width, height, direction, speed;
    private Point c;
    private double fakeX, fakeY;
    public stalkerMing(int xx, int yy, int w, int h, int d, int s){
        super(xx,yy,w,h,d,s);

        x = xx;
        y = yy;
        fakeY = y;
        fakeX = x;

        c = new Point(xx+w/2, yy+h/2);
        width = w;
        height = h;
        direction = d;
        speed = s;
        setPic("jiming.png");
    }
    public void move(int tx, int ty) {
        double ht = -ty+c.y;
        double bt = -tx+c.x;
        double hyp = Math.sqrt(ht*ht+bt*bt);
        double rat = 3 / (Math.abs(ht) + Math.abs(bt));
        double yM = rat * ht;
        double xM = rat * bt;
        fakeX -= xM;
        fakeY -= yM;
        y = (int)fakeY;
        x = (int)fakeX;
        c.setLocation(x,y);
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


        g2.drawImage(super.getPic(), x, y, null);

        g2.translate(x+15, y+15);
        g2.rotate(Math.toRadians(-angle));
        g2.translate(-(x+15), -(y+15));
        //System.out.println(angle);


        //g2.rotate(-currentRotation, loc.x + halfWidth, loc.y + halfHeight);


    }
    public boolean intersects(Rectangle o){
        if (new Rectangle(x,y,width,height-15).intersects(o)){
            return true;
        }
        return false;
    }
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

