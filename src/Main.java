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
    private ArrayList<Platform> plat;
    private boolean leftt, rightt, jumpp, grounded;




    public Main(){

        x = new mainCharacter(400,300,40,40);
        leftt = false;
        rightt = false;
        jumpp = false;
        plat = new ArrayList<Platform>();
        plat.add(new Platform(0,550,600,50));
        plat.add(new Platform(100,0,50,500));
        plat.add(new Platform(500,0,50,500));


        g = 0;
        grounded = false;
        timer = new Timer(1000 / 60, e -> update());
        timer.start();
        setKeyListener();
    }

    public void update() {
        int n = 0;
        for(Platform p: plat){
            while (p.bottomC(new Rectangle(x.getX()+1, x.getY()+1, x.getWidth()-2, x.getHeight())) && ! p.topC(new Rectangle(x.getX(), x.getY(), x.getWidth(), x.getHeight()))){
                x.moveY(-1);
                jumpp = false;
            }
            if (p.topC(new Rectangle(x.getX(), x.getY(), x.getWidth(), x.getHeight()))){
                grounded = true;
                n = 1;
                g = 0;
            }
            int nn = 0;
            while (p.rightC(new Rectangle(x.getX()-1, x.getY(), x.getWidth(), x.getHeight())) && ! p.leftC(new Rectangle(x.getX()+1, x.getY(), x.getWidth(), x.getHeight()))){
                x.moveX(-1);
                nn++;
                if (nn == 24){
                    break;
                }
            }
            nn = 0;

            while (p.leftC(new Rectangle(x.getX()+1, x.getY(), x.getWidth(), x.getHeight())) && !p.rightC(new Rectangle(x.getX()-1, x.getY(), x.getWidth(), x.getHeight()))){
                x.moveX(1);
                nn++;
                if (nn == 24){
                    break;
                }
            }
            while (p.topC(new Rectangle(x.getX()+1, x.getY()-1, x.getWidth()-2, x.getHeight()))){
                x.moveY(1);
            }



        }
        if (n == 0){
            grounded = false;
        }

        if (rightt){
            x.moveX(5);
        }
        if (leftt){
            x.moveX(-5);
        }
        while (x.getX() < 0){
            x.moveX(1);
        }
        while (x.getX() + x.getWidth() > getWidth()){
            x.moveX(-1);
        }
        if (jumpp && !grounded){
            x.moveY(12-(int)(g));
        } else{
            x.moveY(-(int)(g));
        }
        if (!grounded && g < 20) {
            g+=.75;
        }

        repaint();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.fillRect(x.getX(), x.getY(), x.getWidth(), x.getHeight());
        for(Platform p: plat) {
            g2.fill(p.rec());
        }




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
                System.out.println(jumpp + " " + grounded);
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
                    g = 1;
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
