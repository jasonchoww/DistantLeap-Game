
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distantleap;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.Random;

/**
 *
 * @author carolynchen
 */
public class platforms extends GameObject {
    BufferedImage img;
    boolean moveable = false;

    int d;
    
    
    public platforms(BufferedImage img, int x, int y, int move) {
        super(img, x, y);
        this.img = img;
        
        this.x = x;
        this.y = y;
        
        d=1;
        if(move == 0){
            this.moveable = true;
        }
        else{
            this.moveable = false;
        }
    }
    
    @Override
     public void draw(Graphics w, ImageObserver obs){
         if(moveable){
             if(d == 1){
                 x+=d;
                 if(x>400){
                     d=-1;
                 }
             }
             else{
                 x+=d;
                 if(x<19){
                     d=1;
                 }
             }
         }
         w.drawImage(img, x, y, obs);
     }
     
     

     
     public int getD(){
         return d;
     }
     
     
     
     
     
    
}
 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
