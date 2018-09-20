/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distantleap;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

/**
 *
 * @author carolynchen
 */
public class bullet extends GameObject {

    private int D;
    private BufferedImage imge;

    private boolean Pbullet, c;
    boolean powerupActive = false;
    private AffineTransform tx;
    private int num = 22;

    public bullet(BufferedImage img, int x, int y, int D, boolean Pbullet, boolean Ppowerup) {
        super(img, x, y);
        this.D = D;
        imge = img;
        this.Pbullet = Pbullet;
        c = false;
        if (D == -1) {
            imge = GameImages.gameImgArray[17];
        }
        
        powerupActive = Ppowerup;
    }

    public void updata() {
        if (!c && x > 0 && x < 500) {
            if (Pbullet) {
                if (powerupActive) {
                    imge = GameImages.gameImgArray[num];
                    if (num < 30) {
                        num++;
                    } else {
                        num = 27;
                    }

                } else {
                    imge = GameImages.gameImgArray[num];
                    if (num < 25) {
                        num++;
                    } else {
                        num = 22;
                    }
                }
            }
            x += D * 5;

        }
    }

    @Override
    public void draw(Graphics g, ImageObserver obs) {
        if (!c && x > 0 && x < 500) {
            updata();
            g.drawImage(imge, x, y, obs);
        }
    }

    public void collision() {
        c = true;

    }

    public boolean pBullet() {
        return Pbullet;
    }
    
    public void powerupSwitch(){
        powerupActive = true;
    }
}
