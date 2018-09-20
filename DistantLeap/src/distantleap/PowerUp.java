/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distantleap;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

/**
 *
 * @author jasonchow
 */
public class PowerUp extends GameObject {
    int powerType = 0;

    public PowerUp(BufferedImage img, int x, int y, int type) {
        super(img, x, y);
        this.img = img;
        powerType = type;
    }
    
    public int getType(){
        return powerType;
        
    }

    public void healthItem(boolean active) {
        DistantLeap.powerupList.remove(this);

    }

    public void swordItem(boolean active) {
        DistantLeap.powerupList.remove(this);
    }
    
    public void trophyItem(boolean active){
        DistantLeap.powerupList.remove(this);
    }
    
    public void rocketItem(boolean active){
        DistantLeap.powerupList.remove(this);
    }

    @Override
    public void draw(Graphics g, ImageObserver obs) {
        g.drawImage(img, x, y, obs);
    }
}
