package GIU;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Okno extends JFrame {

    Color refColor= Color.GRAY;
    int limit=100;
    int Xc,Yc;
    int Rc=10;
    public Graphics g;
    ArrayList<Point> punkty=new ArrayList<Point>(limit);

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(refColor);
        for(Point p:punkty) {
            Xc=(int)p.getX();
            Yc=(int)p.getY();
            g.drawOval(Xc, Yc, Rc, Rc);
        }
    }
    Okno(String nazwa)
    {
        super(nazwa);
        setResizable(false);
        setSize(500,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        this.addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent ke) {
                int key=ke.getKeyCode();
                switch(key) {
                    case KeyEvent.VK_ENTER:
                        g.setColor(Color.RED);
                        System.out.println("Kolor to: red");
                        break;
                    case KeyEvent.VK_G:
                        g.setColor(Color.GREEN);
                        System.out.println("Kolor to: green");
                        break;
                    case KeyEvent.VK_B:
                        g.setColor(Color.BLUE);
                        System.out.println("Kolor to: blue");
                        break;
                    default:
                        g.setColor(Color.GRAY);
                        System.out.println("Kolor to: gray");
                }
            }
        });
        JPanel p = new JPanel(new FlowLayout());
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                punkty.add(new Point(getMousePosition()));
                System.out.println("punkt: "+punkty.size());
                if(punkty.size()==100) {
                    punkty.remove(0);
                }
                repaint();
            }
        });
        JCheckBox c=new JCheckBox(); //setFocusable(false)
        c.setFocusable(false);
        JLabel l = new JLabel("Skróc sprężyne");
        l.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                if(punkty.size()!=0) {
                    punkty.remove(0);
                }
                System.out.println("a tera:" +punkty.size());
            }
        });
        p.add(c);
        p.add(l);
        add(p,BorderLayout.SOUTH);
        setVisible(true);
    }


}
