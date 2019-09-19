import java.awt.Rectangle;

public class WIN extends Platform{
    private int xx,yy, ww, hh;

    public WIN(int x, int y, int w, int h){
        super(x,y,w,h);
        xx = x;
        yy = y;
        hh = h;
        ww = w;
    }
    public boolean intersects(Rectangle o){
        if (new Rectangle(xx,yy,ww,hh).intersects(o)){
            return true;
        }
        return false;
    }


}
