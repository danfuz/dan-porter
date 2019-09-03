import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;

public class Main extends JPanel implements ActionListener{

    public static final int WIDTH=600, HEIGHT=600;
    private Timer timer;
    private mainCharacter x;
    private boolean leftt, rightt;



    public Main(){

        x = new mainCharacter(300,300,40,40);
        leftt = false;
        rightt = false;
        timer = new Timer(1000 / 60, e -> update());
        timer.start();
        setKeyListener();
    }
    public void ac

    public void update() {


        repaint();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.fillRect(x.getX(), x.getY(), x.getWidth(), x.getHeight());




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
                if (key == KeyEvent.VK_LEFT){
                    leftt = true;
                    rightt = false;
                }
                update();

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
