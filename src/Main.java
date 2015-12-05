
import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dmitry
 */
public class Main {
    
    private void run() throws IOException
    {
        View view = new View();
        ImageConverter img1 = new ImageConverter();
        img1.loadImage("images\\smarties.jpg");
        img1.setResolution(1);
        view.draw(img1);
    }
    
    public static void main(String[] args) throws IOException
    {
        Main main = new Main();
        main.run();
    }
    
}
