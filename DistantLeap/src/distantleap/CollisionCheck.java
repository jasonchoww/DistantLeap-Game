/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distantleap;

import java.awt.Rectangle;

/**
 *
 * @author carolynchen
 */
public class CollisionCheck {

    Rectangle left;
    Rectangle right;
    Rectangle up;
    Rectangle down;
    Rectangle W;
    Rectangle A;

    public CollisionCheck() {

    }

    public void PandE(platforms a, enemy b) {
        A = new Rectangle(a.getX(), a.getY(), a.getWidth() - 1, a.getHeight() - 1);
        W = new Rectangle(b.getX(), b.getY(), b.getWidth() - 1, b.getHeight() - 1);
        if (A.intersects(W)) {
            b.changeD();
        }
    }

    public void BandP(bullet a, Player b) {
        if (!a.pBullet()) {
            A = new Rectangle(a.getX(), a.getY(), a.getWidth() - 1, a.getHeight() - 1);
            W = new Rectangle(b.getX(), b.getY(), b.getWidth() - 1, b.getHeight() - 1);
            if (A.intersects(W)) {
                b.attacked();
                a.collision();
                DistantLeap.bulletList.remove(a);
            }
        }
    }

    public void BandE(bullet a, enemy b, Player c) {
        if (a.pBullet()) {
            A = new Rectangle(a.getX(), a.getY(), a.getWidth() - 1, a.getHeight() - 1);
            W = new Rectangle(b.getX(), b.getY(), b.getWidth() - 1, b.getHeight() - 1);
            if (A.intersects(W)) {
                b.attacked();
                a.collision();
                DistantLeap.bulletList.remove(a);
                c.scoreUpdate();
            }
        }
    }

    public void PaAndE(Player a, enemy b) {
        A = new Rectangle(a.getX(), a.getY(), a.getWidth() - 1, a.getHeight() - 1);
        W = new Rectangle(b.getX(), b.getY(), b.getWidth() - 1, b.getHeight() - 1);
        if (A.intersects(W)) {
            if (!b.Moveable()) {
                b.attacked();
            }
        }
    }

    public void PowerupAndPlayer(Player a, PowerUp b) {
        A = new Rectangle(a.getX(), a.getY(), a.getWidth() - 1, a.getHeight() - 1);
        W = new Rectangle(b.getX(), b.getY(), b.getWidth() - 1, b.getHeight() - 1);
        if (A.intersects(W)) {
            if (b.getType() == 0) {
                b.swordItem(true);
                a.setSwordPowerUp();
            }
            if (b.getType() == 1) {
                b.healthItem(true);
                a.healthPotion();
            }
            if(b.getType() == 2){
                a.setVictory();
                b.trophyItem(true);
            }
            if(b.getType() == 3){
                b.rocketItem(true);
                a.rocketItem(); 
            }
            if(b.getType() == 4){
                b.rocketItem(true);
            }
            
        }
    }
}
