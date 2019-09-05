import java.awt.*;

public class Platform {
    private int x,y,width,height;
    public Platform(int xx, int yy, int w, int h){
        x = xx;
        y = yy;
        width = w;
        height = h;
    }
    public Rectangle rec(){
        return new Rectangle(x,y,width,height);
    }
    public boolean topC(Rectangle r){
        int x1 = r.x+1;
        int x2 = r.x+r.width-1;
        int x3 = r.x+r.width/2;
        int yy = r.y+r.height;
        if (rec().contains(new Point(x1,yy)) || rec().contains(new Point(x2,yy)) || rec().contains(new Point(x3,yy))){
            return true;
        }
        return false;

    }

}
