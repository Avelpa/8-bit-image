
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import javax.swing.JComponent;
import javax.swing.JFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dmitry
 */
public class View extends JComponent implements KeyListener{
    
    private JFrame window;
    private ImageConverter img;
    private int resolution = 1;
    
    public View()
    {
        window = new JFrame("8-bit");
        window.add(this);
        window.setPreferredSize(new Dimension(1500, 800));
        window.pack();
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        window.addKeyListener(this);
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        if (img != null)
            img.draw(g);
    }
    
    public void draw(ImageConverter img)
    {
        this.img = img;
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            resolution --;
            img.setResolution(resolution);
            draw(img);
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            resolution ++;
            img.setResolution(resolution);
            draw(img);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
