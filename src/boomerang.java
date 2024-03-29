import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.Rectangle;


public class boomerang {

    private int x, y, time, speed;
    private double yy;
    private Boolean dir, down, live;
    private BufferedImage pic;
    private int currentRotation;
    private Point loc;
    private boolean call;
    private double xCall, yCall;
    private double fakeX, fakeY;




    public boomerang(int x, int y, boolean d, boolean live){
        this.x = x;
        this.y = y;
        this.live = live;
        fakeX = 0;
        fakeY = 0;
        xCall = 0;
        yCall = 0;
        call = false;
        yy = 0;
        loc = new Point(x,y);
        dir = d;
        currentRotation = 0;
        setPic("boomerang.png");
        down = true;
        time = 0;
    }


    public void setTime(int time) {
        this.time = time;
    }
    public void callB(){
        call = true;
    }
    public boolean isFrogger(int frog){
        return call;
    }

    public int getTime() {
        return time;
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    //    public void move(int tx, int ty){
//
//        if (!call) {
//            int dist = 0;
//            int d = Math.abs(Main.WIDTH / 2 - (x + 20));
//            int speed = Math.abs(-d / 90 + 6);
//            if (x + 40 >= Main.WIDTH) {
//                dir = false;
//            }
//            if (x <= 0) {
//                dir = true;
//            }
//            if (dir) {
//                x += speed;
//            }
    public Boolean isLive() {
        return live;
    }

    public void setLive(Boolean live) {
        this.live = live;
    }

    public void move(int tx, int ty){
        if (!call) {
            int dist = 0;
            int d = Math.abs(Main.WIDTH / 2 - (x + 20));
            int speed = Math.abs(-d / 100 + 6);
            if (x + 40 >= Main.WIDTH) {
                dir = false;
            }
            if (x <= 0) {
                dir = true;
            }
            if (dir) {
                x += speed;

            } else {
                x -= speed;


            }
            yy += .3;
//        if(y >= 515){
//            down = false;
//        }
            if (down) {
                if (yy >= 1) {
                    y += 1;
                    yy -= 1;
                }
            }
            yy += .3;
            if (y >= 515) {
                down = false;
            }
            if (down) {
                if (yy >= 1) {
                    y += 1;
                    yy -= 1;
                }

            }
            fakeY = y;
            fakeX = x;
        }
        else{
            double ht = -ty+y;
            double bt = -tx+x;
            double hyp = Math.sqrt(ht*ht+bt*bt);
            if (hyp >= 5) {
                double ratio = ht / bt;
                double rat = 5 / (Math.abs(ht) + Math.abs(bt));
                double yM = rat * ht;
                double xM = rat * bt;
                fakeX -= xM;
                fakeY -= yM;
                y = (int)fakeY;
                x = (int)fakeX;

            }

        }

        loc.setLocation(x, y);
    }



    public void setPic(String fileName) {
        try {
            pic = ImageIO.read(new File("res/" + fileName));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//out of 360


    public void setCurrentRotation(int currentRotation) {
        this.currentRotation = currentRotation;
    }

    public int getCurrentRotation() {
        return currentRotation;
    }

    /**
     * This draws the image pic at the Point loc, rotated to face dir.
     */
    public void draw(Graphics2D g2) {


        double halfWidth = pic.getWidth() / 2;
        double halfHeight = pic.getHeight() / 2;
        g2.rotate(currentRotation, loc.x + halfWidth, loc.y + halfHeight);
        g2.drawImage(pic, loc.x, loc.y, null);
        g2.rotate(-currentRotation, loc.x + halfWidth, loc.y + halfHeight);
    }
    public boolean off(){
        if (y  > Main.HEIGHT){
            return true;
        }
        return false;
    }
    public boolean intersects(Rectangle o){
        //System.out.println(pic.getWidth() + " " + pic.getHeight());
        if (new Rectangle(x,y,20,17).intersects(o)){
            return true;
        }
        return false;
    }





}