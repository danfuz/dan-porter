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
    private double g, rotate;
    private ArrayList<Platform> plat;
    private ArrayList<Enemies> enemies;
    private ArrayList<boomerang> boomerangs;
    //private ArrayList<Platform> plat;
    private boolean leftt, rightt, jumpp, grounded;
    private boolean dead;
    private int interval;




    public Main(){

        x = new mainCharacter(400,400,40,40);
        dead = false;
        leftt = false;
        rotate = 0;
        rightt = false;
        jumpp = false;
        plat = new ArrayList<Platform>();
        boomerangs = new ArrayList<boomerang>();
        enemies = new ArrayList<Enemies>();
        interval = 0;


        plat.add(new Platform(0,550,600,50));
        plat.add(new Platform(100,300,50,500));
        plat.add(new Platform(500,0,50,500));

//        for (int i = 0; i < 9; i++) {
//            enemies.add(new Enemies(50*i, 400, 40, 40, 0, 5));
//        }


        g = 0;
        grounded = false;
        timer = new Timer(1000 / 60, e -> update());
        timer.start();
        setKeyListener();
    }

    public void update() {
        if (dead == false) {
            int n = 0;
            for (Platform p : plat) {
                while (p.bottomC(new Rectangle(x.getX() + 1, x.getY() + 1, x.getWidth() - 2, x.getHeight())) && !p.topC(new Rectangle(x.getX(), x.getY(), x.getWidth(), x.getHeight()))) {
                    x.moveY(-1);
                    jumpp = false;
                }
                for (int i = 0; i < g; i++) {
                    if (p.topC(new Rectangle(x.getX() + 1, x.getY() - 1, x.getWidth() - 2, x.getHeight()))) {
                        x.moveY(1);
                        grounded = true;
                        jumpp = false;

                    }
                }
                if (p.topC(new Rectangle(x.getX(), x.getY(), x.getWidth(), x.getHeight()))) {
                    grounded = true;
                    n = 1;
                    g = 0;
                }
                int nn = 0;
                while (p.rightC(new Rectangle(x.getX(), x.getY(), x.getWidth() - 1, x.getHeight() - 1)) && !p.leftC(new Rectangle(x.getX() + 1, x.getY(), x.getWidth(), x.getHeight()))) {
                    x.moveX(-1);
                    nn++;
                    if (nn == 24) {
                        break;
                    }
                }
                nn = 0;

                while (p.leftC(new Rectangle(x.getX() + 1, x.getY(), x.getWidth(), x.getHeight() - 1)) && !p.rightC(new Rectangle(x.getX(), x.getY(), x.getWidth() - 1, x.getHeight()))) {
                    x.moveX(1);
                    nn++;
                    if (nn == 24) {
                        break;
                    }
                }
                while (p.topC(new Rectangle(x.getX() + 1, x.getY() - 1, x.getWidth() - 2, x.getHeight()))) {
                    x.moveY(1);
                }


            }
            if (n == 0) {
                grounded = false;
            }

        rotate = 1;
        interval++;
        if(interval%2==0){
            rotate = 0;
        }
        for (int i = 0; i < boomerangs.size(); i++) {
            boomerangs.get(i).move();
//            boomerangs.get(i).setCurrentRotation(3);
            boomerangs.get(i).setCurrentRotation(boomerangs.get(i).getCurrentRotation()+(int)(rotate));
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
            if (rightt) {
                x.moveX(5);
            }
            if (leftt) {
                x.moveX(-5);
            }
            while (x.getX() < 0) {
                x.moveX(1);
            }
            while (x.getX() + x.getWidth() > getWidth()) {
                x.moveX(-1);
            }
            if (jumpp && !grounded) {
                x.moveY(12 - (int) (g));
            } else {
                x.moveY(-(int) (g));
            }
            if (!grounded && g < 24) {
                g += .75;
            }
            for (int i = 0; i < enemies.size(); i++) {
                enemies.get(i).move();
            }


            repaint();
        }
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.fillRect(x.getX(), x.getY(), x.getWidth(), x.getHeight());
        for(Platform p: plat) {
            g2.fill(p.rec());
        }
        for(Enemies p: enemies) {
            g2.fill(p.rec());
            if (p.intersects(new Rectangle(x.getX(), x.getY(), x.getWidth(), x.getHeight()))){
                System.out.println("DEAD");
                dead = true;
            }
        }
        for(boomerang b: boomerangs){
            b.draw(g2);
        }




    }

    public void setKeyListener(){
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }
            @Override
            public void keyPressed(KeyEvent e) {
                if (dead == false) {
                    int key = e.getKeyCode();
                    if (key == KeyEvent.VK_RIGHT) {
                        rightt = true;
                        leftt = false;
                    }
                    if (key == KeyEvent.VK_UP && grounded) {
                        jumpp = true;
                        x.moveY(3);
                    }
                    if (key == KeyEvent.VK_LEFT) {
                        leftt = true;
                        rightt = false;
                    }
                    if (key == KeyEvent.VK_Z){
                        boomerangs.add(new boomerang(x.getX(),x.getY(),false));
                    }
                    if (key == KeyEvent.VK_X){
                        boomerangs.add(new boomerang(x.getX(),x.getY(),true));
                    }
                }
                //System.out.println(jumpp + " " + grounded);
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
