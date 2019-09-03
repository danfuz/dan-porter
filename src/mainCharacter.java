import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class mainCharacter {
    private int xcenter, ycenter, width, height, x,y;
    public mainCharacter(int xx, int yy, int w, int h){
        x = xx;
        y = yy;
        width = w;
        height = h;
        xcenter = x+width/2;
        ycenter = y+height/2;
    }
    public void moveY(int mY){
        y += mY;
    }
    public void moveX(int mX){
        x += mX;
    }
    public Rectangle getBody(){
        return new Rectangle(x, y, width, height);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
}
