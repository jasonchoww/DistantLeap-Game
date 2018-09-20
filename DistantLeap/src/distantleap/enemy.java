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
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author carolynchen
 */
public class enemy extends GameObject{
    private BufferedImage img;
    private boolean moveable;
    private boolean alive;
    private int d, timer;
    private int score;
    private Explosion haha;
    
    public enemy(BufferedImage img, int x, int y,boolean moveable) throws IOException {
        super(img, x, y);
        this.moveable = moveable;
        this.x=x;
        this.y = y;
        this.img = img;
        d=-1;
        timer = 20;
        alive = true;
        haha = new Explosion();
        
    }

    public void update() {
      
        if(moveable){
           if(d == -1){
               x-=2;
               if(x<20){
                   d=1;
                   img = GameImages.gameImgArray[13];
               }
           }
           else{
               x+=2;
               if(x>480){
                   d=-1;
                   img = GameImages.gameImgArray[14];
               }
           }
           if(timer!=0){
               timer--;
           }
           else{
               timer = 20;
               DistantLeap.bulletList.add(new bullet(GameImages.gameImgArray[16],x,y+10,d,false, false));
           }
        }
        else{
           if(timer!=0){
               timer--;
           }
           else{
               timer = 50;
               DistantLeap.bulletList.add(new bullet(GameImages.gameImgArray[16],x+10,y+15,d,false, false));
               DistantLeap.bulletList.add(new bullet(GameImages.gameImgArray[16],x+10,y+15,d*-1,false, false));
           }
        }
 }
    
    
    
    @Override
    public void draw(Graphics w, ImageObserver obs) {
        if(alive){
        update();
        w.drawImage(img, x, y, obs);
        }
        else{
            if(!haha.Done()){
               w.drawImage(haha.draw(), x, y, obs);
           }
           else{
            DistantLeap.enemiesList.remove(this);
           }
        }
    }
    
    public void changeD(){
        if(d==0){
            d=1;
        }
        else{
            d=0;
        }
    }
    public void attacked(){
        alive = false;
    }
    public boolean Moveable(){
        return moveable;
    }
    
}
    

