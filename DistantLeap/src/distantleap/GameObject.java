/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distantleap;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

/**
 *
 * @author carolynchen
 */
public class GameObject {
      protected int x, y, width, height, speed;
    protected Image img;

    public GameObject(BufferedImage img, int x, int y) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.width = img.getWidth();
        this.height = img.getHeight();
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setX(int a) {
        this.x = a;
    }

    public void setY(int b) {
        this.y = b;
    }

    public void draw(Graphics g, ImageObserver obs) {
        
        g.drawImage(img, x, y, obs);

    }

}

