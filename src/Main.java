import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

public class Main extends JPanel{

    public static final int WIDTH=600, HEIGHT=600;
    private Timer timer;
    private int minesweeper;
    private mainCharacter x;
    private double g, rotate;
    private ArrayList<Platform> plat;
    private ArrayList<Enemies> enemies;
//    private ArrayList<boomerang> boomerangs;
    private boomerang tetris;
    //private ArrayList<Platform> plat;
    private boolean leftt, rightt, jumpp, grounded;
    private boolean dead;
    private int interval;




    public Main(){

        minesweeper = 0;

        x = new mainCharacter(400,500,40,40);

        dead = false;
        leftt = false;
        rotate = 0;
        rightt = false;
        jumpp = false;
        plat = new ArrayList<Platform>();
//        boomerangs = new ArrayList<boomerang>();
        tetris = new boomerang(-100, 0, false, false);
        enemies = new ArrayList<Enemies>();
        interval = 0;



        plat.add(new Platform(0,550,600,50));
        plat.add(new Platform(100,300,50,500));
        plat.add(new Platform(500,0,50,500));
        plat.add(new Platform(250, 0, 50, 400));
        plat.add(new Platform(375,300,50,500));
        //plat.add(new WIN(550,0,50,50));

//        for (int i = 0; i < 9; i++) {
//            enemies.add(new Enemies(50*i, 400, 40, 40, 0, 5));
//        }
        enemies.add(new stalkerMing(400, 20, 40, 40, 0, 0));

//        for (int i = 0; i < 9; i++) {
//            enemies.add(new Enemies(50*i, 400, 40, 40, 0, 5));
//        }

        enemies.add(new Enemies(300,300, 40, 40, 0, 5, 150, 510));

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
            if (tetris.intersects(new Rectangle(x.getX(), x.getY(), x.getWidth(), x.getHeight()))){
                tetris.setLive(false);
            }
            if(tetris.isLive()) {
                rotate = 1;
                interval++;
                if (interval % 2 == 0) {
                    rotate = 0;
                }

                tetris.move(x.getX() + x.getWidth()/2, x.getY() + x.getHeight()/2);
                tetris.setTime(tetris.getTime() + 1);
        tetris.move(x.getX()+x.getWidth()/2, x.getY() + x.getHeight()/2);
        tetris.setTime(tetris.getTime()+1);

        //            tetris.setCurrentRotation(3);
                tetris.setCurrentRotation(tetris.getCurrentRotation() + (int) (rotate));
            }
            else {tetris.setX(-100); tetris.setY(-100);}
//        if (tetris.off()){
//
//        }







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
                g += .5;
            }
            for (int i = 0; i < enemies.size(); i++) {
                enemies.get(i).move(x.getX() + x.getWidth()/2, x.getY() + x.getHeight()/2);
            }





                for (int j = enemies.size()-1; j >= 0; j--) {
                    if (tetris.intersects(
                            new Rectangle(enemies.get(j).getX(), enemies.get(j).getY(),
                                    enemies.get(j).getWidth(), enemies.get(j).getHeight()))) {
                        enemies.remove(j);
                        minesweeper+= 100;
                        System.out.println(minesweeper);
                        break;
                    }
                }
            }



            if(enemies.size()<=1) {
                if (interval % 50 == 0) {
                    Enemies a = null;
                    if (Math.random() > .5) {
                         a = new Enemies(0, (int) (Math.random() * 450), 50, 50, 0, 5);


                    } else {
//                        System.out.println("h");
                         a = new Enemies(500, (int) (Math.random() * 400), 50, 50, 180, 5);


                    }
                    if (!(a.dist(x.getX()+x.getWidth()/2, x.getY() + x.getHeight()/2) < 150)){
                        enemies.add(a);

                    }
                }
            }
            for (Platform p : plat){
            if (p instanceof  WIN) {
                WIN www = (WIN)(p);
                if (www.intersects(new Rectangle(x.getX()-1, x.getY()-1, x.getWidth()+2, x.getHeight()+2))){
                    minesweeper++;
                    System.out.println(minesweeper);
                }
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
            if (p instanceof WIN){
                g2.setColor(Color.GREEN);
            }
            else {
                g2.setColor(Color.black);
            }
            g2.fill(p.rec());
        }
        for(Enemies p: enemies) {
            p.draw(g2, x.getX()+x.getWidth()/2, x.getY() + x.getHeight()/2);
            if (p.intersects(new Rectangle(x.getX(), x.getY(), x.getWidth(), x.getHeight())) && !dead){
                System.out.println("DEAD");
                dead = true;
                repaint();
            }
        }
        if(tetris.isLive()) {
            tetris.draw(g2);
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
                    if (key == KeyEvent.VK_SPACE) {
                        tetris.callB();
                    }
                    if (key == KeyEvent.VK_RIGHT) {
                        rightt = true;
                        leftt = false;
                    }
                    if (key == KeyEvent.VK_DOWN && g <=24) {
                        g+=.2;
                    }
                    if (key == KeyEvent.VK_UP && grounded) {
                        jumpp = true;
                        x.moveY(3);
                    }
                    if (key == KeyEvent.VK_LEFT) {
                        leftt = true;
                        rightt = false;
                    }
                    if(!tetris.isLive()) {
                        if (key == KeyEvent.VK_Z) {
                            tetris = new boomerang(x.getX() - 20-10, x.getY(), false, true);
                            System.out.println("Z");
                        }
                        if (key == KeyEvent.VK_X) {
                            tetris = new boomerang(x.getX() + 40+10, x.getY(), true, true);
                            System.out.println("X");

                        }
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
