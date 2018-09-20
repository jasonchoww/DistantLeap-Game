/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distantleap;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author carolynchen
 */
public class Player extends GameObject implements Observer {

    GameImages gImg = new GameImages();

    private int life, gravity, graMax, xVmax, health, d;
    private int xvelocity;
    private int score;
    private int boost = 150;
    private int rocketXholder, rocketYholder;

    int imgPointer = 9;
    int yLocation = 0;

    private boolean swordPowerup, victory;

    private final BufferedImage imgL = gImg.getImage(0);
    private final BufferedImage imgR = gImg.getImage(1);

    private BufferedImage HBar;

    public boolean jump, left, right, shoot, platform, alive, moveable,expl;
    
    private Explosion haha;

    public Player(BufferedImage img, int x, int y) throws IOException {
        super(img, x, y);
        this.x = x;
        this.y = y;

        this.img = imgL;
        health = 3;
        life = 3;
        //HBar = images.ImageList.get(4);

        HBar = gImg.getImage(imgPointer);
        alive = true;

        gravity = 0;

        platform = true;
        graMax = 5;
        xvelocity = 0;
        xVmax = 20;
        d = -1;
        haha = new Explosion();
        expl = false;
    }

    @Override
    public void draw(Graphics w, ImageObserver obs) {
        if (alive&&!expl) {
            if (y > 3200) {
                reset();
            } else {
                platform = false;
                for (int i = 0; i < DistantLeap.platformList.size(); i++) {
                    PandP(DistantLeap.platformList.get(i));
                }
            }
            gravity += 1;
            if (platform) {
                gravity = 0;
                if (jump) {
                    gravity -= 20;
                }
            }
            if (xvelocity != 0) {
                xvelocity *= 0.9;
            }
            x += xvelocity;
            y += gravity;

            w.drawImage(HBar, x, y - 20, obs);
            w.drawImage(img, x, y, obs);

        }
        else if(expl){
              if(!haha.Done()){
               w.drawImage(haha.draw(), x, y, obs);
           }
            else{
             reset();
             expl = false;
           }
        }
    }

    @Override
    public void update(Observable object, Object argument) {

        //System.out.println("test");
        if (alive) {
            if (left) {
                xvelocity -= 5;
                img = imgL;
                d = -1;
            } else if (right) {
                xvelocity += 5;
                img = imgR;
                d = 1;
            }
        } else {
            img = gImg.getImage(31);
        }

        if (shoot) {
            DistantLeap.bulletList.add(new bullet(GameImages.gameImgArray[22], x, y, d, true, swordPowerup));
        }
        if (life <= 0) {
            alive = false;
            img = gImg.getImage(31);
        }

    }

    public void attacked() {
        if (health > 0) {
            health--;
            imgPointer++;
            //HBar = images.ImageList.get(images.ImageList.indexOf(HBar)+1);
            HBar = gImg.getImage(imgPointer);
        } else if (life > 0) {
            expl=true;
        } else {
            alive = false;
        }

    }

    public int getYLocation() {
        return y;
    }

    public void healthPotion() {
        health = 3;
        imgPointer = 9;
        HBar = gImg.getImage(imgPointer);
        life++;
    }

    public void setVictory() {
        victory = true;
    }

    public boolean getVictory() {
        return victory;
    }

    public void setSwordPowerUp() {
        swordPowerup = true;
    }

    public void setSwordPowerUpFalse() {
        swordPowerup = false;
    }

    public void rocketItem() {
        x = rocketXholder;
        y = rocketYholder;
    }

    public void setBoost(int Rx, int Ry) {
        rocketXholder = Rx;
        rocketYholder = Ry;

    }

    public String getLife() {
        return Integer.toString(life);
    }

    public int getLifeInt() {
        return life;
    }

    public void PandP(platforms b) {
        Rectangle leftR = new Rectangle(x, y, 1, this.getHeight() - 1);
        Rectangle rightR = new Rectangle(x + this.getWidth() - 1, y, 1, this.getHeight() - 1);
        Rectangle upR = new Rectangle(x, y, this.getWidth() - 1, 1);
        Rectangle downR = new Rectangle(x, y + this.getHeight() - 1, this.getWidth() - 1, 1);

        Rectangle W = new Rectangle(b.getX(), b.getY(), b.getWidth() - 1, b.getHeight() - 1);
        if (upR.intersects(W)) {
            this.y -= gravity;
        }
        if (downR.intersects(W)) {
            this.platform = true;
            if (b.moveable) {
                x += b.d;
            }

        }
        if (leftR.intersects(W)) {
            //this.x += xvelocity;
        }
        if (rightR.intersects(W)) {
            //this.x -= xvelocity;
        }
    }

    public void reset() {
        life--;
        setSwordPowerUpFalse();
        health = 3;
        //HBar = images.ImageList.get(4);
        imgPointer = 9;
        HBar = gImg.getImage(imgPointer);
        x = 200;
        y = 2850;
        haha.reset();
    }

    public void scoreUpdate() {
        score += 100;
    }

    public String getScore() {
        return Integer.toString(score);
    }
}
