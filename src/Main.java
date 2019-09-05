import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;

public class Main extends JPanel{

    public static final int WIDTH=600, HEIGHT=600;
    private Timer timer;
    private mainCharacter x;
    private double g;
    private boolean leftt, rightt, jumpp, grounded;
    Platform p = new Platform(300,400,600,50);



    public Main(){

        x = new mainCharacter(300,300,40,40);
        leftt = false;
        rightt = false;
        jumpp = false;
        g = 0;
        grounded = false;
        timer = new Timer(1000 / 60, e -> update());
        timer.start();
        setKeyListener();
    }

    public void update() {
        if (p.topC(new Rectangle(x.getX(), x.getY(), x.getWidth(), x.getHeight()))){
            grounded = true;
            g = 0;
        } else{
            grounded = false;
        }
        if (rightt){
            x.moveX(5);
        }
        if (leftt){
            x.moveX(-5);
        }
        if (jumpp && !grounded){
            x.moveY(12-(int)(g));
        } else{
            x.moveY(-(int)(g));
        }
        if (!grounded && g < 20) {
            g+=.75;
        }
        while (p.topC(new Rectangle(x.getX(), x.getY()-1, x.getWidth(), x.getHeight()))){
            x.moveY(1);
        }
        repaint();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.fillRect(x.getX(), x.getY(), x.getWidth(), x.getHeight());
        g2.fill(p.rec());




    }

    public void setKeyListener(){
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_RIGHT){
                    rightt = true;
                    leftt = false;
                }
                if (key == KeyEvent.VK_UP && grounded){
                    jumpp = true;
                    x.moveY(1);
                }
                if (key == KeyEvent.VK_LEFT){
                    leftt = true;
                    rightt = false;
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_RIGHT){
                    rightt = false;
                }
                if (key == KeyEvent.VK_LEFT){
                    leftt = false;
                }
                if (key == KeyEvent.VK_UP && jumpp == true && g < 12){
                    jumpp = false;
                    g = 2;
                }
            }
        });
    }

    public static void main(String[] args) {

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        window.setBounds(0, 0, WIDTH, HEIGHT + 22); //(x, y, w, h) 22 due to title bar.

        Main panel = new Main();

        panel.setFocusable(true);
        panel.grabFocus();

        window.add(panel);
        window.setVisible(true);
        window.setResizable(false);
    }

}
