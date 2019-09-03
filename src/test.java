import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Area;
import java.util.ArrayList;
import javax.swing.event.ChangeListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Dialog;
public class test extends JPanel{

    mainCharacter x = new mainCharacter(300,300,40,40);

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.fillRect(x.getX(), x.getY(), x.getWidth(), x.getHeight());
    }

    public static void main(String[] args) {
        
    }

}
