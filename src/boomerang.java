import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

public class boomerang {

    private int x, y, speed;
    private Boolean dir;
    private Image pic;
    private int currentRotation;
    private Point loc;




    public boomerang(int x, int y, boolean d){
        this.x = x;
        this.y = y;
        dir = d;
        currentRotation = 0;
        setPic();
        loc.setLocation(x,y);
    }

    public void move(){
        int dist = 0;
        if(dir){
            dist = Main.WIDTH - x;
            x += dist/30;
        }
        else{
            dist = x;
            x -= dist/30;
        }
    }

    public void setPic(String fileName) {
        try {
            pic = ImageIO.read(new File("res/" + fileName));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//out of 360


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


}
