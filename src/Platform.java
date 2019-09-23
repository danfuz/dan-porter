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
        int  x1 = r.x+1;
        int x2 = r.x+r.width-1;
        int x3 = r.x+r.width/2;
        int yy = r.y+r.height;
        if (rec().contains(new Point(x1,yy)) || rec().contains(new Point(x2,yy)) || rec().contains(new Point(x3,yy))){
            return true;
        }
        return false;

    }
    public boolean rightC(Rectangle r){
        int y1 = r.y + r.height;
        int y2 = r.y+r.height-1;
        int y3 = r.y+r.height/2;
        int xx = r.x+r.width;
        if (rec().contains(new Point(xx,y1)) || rec().contains(new Point(xx,y2)) || rec().contains(new Point(xx,y3))){
            return true;
        }
        return false;

    }
    public boolean leftC(Rectangle r){
        int y1 = r.y + r.height;
        int y2 = r.y+r.height-1;
        int y3 = r.y+r.height/2;
        int xx = r.x;
        if (rec().contains(new Point(xx,y1)) || rec().contains(new Point(xx,y2)) || rec().contains(new Point(xx,y3))){
            return true;
        }
        return false;

    }
    public boolean bottomC(Rectangle r){
        int x1 = r.x+1;
        int x2 = r.x+r.width-1;
        int x3 = r.x+r.width/2;
        int yy = r.y;
        if (rec().contains(new Point(x1,yy)) || rec().contains(new Point(x2,yy)) || rec().contains(new Point(x3,yy))){
            return true;
        }
        return false;

    }

}
