/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distantleap;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import javax.imageio.ImageIO;

/**
 *
 * @author carolynchen
 */
public class Explosion 
{
    private ArrayList<BufferedImage> list;
    private int image;
    private boolean done;
    private int speed;
    public Explosion() throws IOException {
        list = new ArrayList<BufferedImage>();
        list.add(ImageIO.read(Explosion.class.getResource("images/explosion1_1.png")));
        list.add(ImageIO.read(Explosion.class.getResource("images/explosion1_2.png")));
        list.add(ImageIO.read(Explosion.class.getResource("images/explosion1_3.png")));
        list.add(ImageIO.read(Explosion.class.getResource("images/explosion1_4.png")));
        list.add(ImageIO.read(Explosion.class.getResource("images/explosion1_5.png")));
        list.add(ImageIO.read(Explosion.class.getResource("images/explosion1_6.png")));
        image = 0;
        done = false;
        speed = 3;
        
    }
    public BufferedImage draw(){
            speed--;
            if(speed==0){
               image++;
               speed = 3;
            }
            if(image == 5){
               done = true; 
            }
            return list.get(image);
            
    }
    
    public boolean Done(){
        return done;
    }
    
    public void reset(){
        image = 0;
        speed = 3;
        done = false;
    }
    
    
}
