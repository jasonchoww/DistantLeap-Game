/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distantleap;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author User
 */
public class GameImages {

    static BufferedImage[] gameImgArray;

    public GameImages() {
        gameImgArray = new BufferedImage[40];
        try {

            gameImgArray[0] = (ImageIO.read(DistantLeap.class.getResource("images/GplayerL.png")));//0:player left
            gameImgArray[0] = resizeCharacter(gameImgArray[0]);

            gameImgArray[1] = (ImageIO.read(DistantLeap.class.getResource("images/GplayerR.png")));//1:player right
            gameImgArray[1] = resizeCharacter(gameImgArray[1]);

            gameImgArray[2] = (ImageIO.read(DistantLeap.class.getResource("images/platforms1.png")));// island1
            gameImgArray[2] = resizePlatform1(gameImgArray[2]);
            
            gameImgArray[3] = (ImageIO.read(DistantLeap.class.getResource("images/platforms2.png")));// island2
            gameImgArray[3] = resizePlatform2(gameImgArray[3]);

            gameImgArray[4] = (ImageIO.read(DistantLeap.class.getResource("images/background1.png")));// 
            gameImgArray[5] = (ImageIO.read(DistantLeap.class.getResource("images/background3.png")));// 
            gameImgArray[6] = (ImageIO.read(DistantLeap.class.getResource("images/background4.png")));// 
            gameImgArray[7] = (ImageIO.read(DistantLeap.class.getResource("images/background5.png")));// 
            gameImgArray[8] = (ImageIO.read(DistantLeap.class.getResource("images/background7.jpg")));// 

            gameImgArray[9] = (ImageIO.read(Player.class.getResource("images/health.png")));//9:healthBar full
            gameImgArray[10] = (ImageIO.read(Player.class.getResource("images/health1.png")));//10:healthBar 3
            gameImgArray[11] = (ImageIO.read(Player.class.getResource("images/health2.png")));//11:healthBar 2
            gameImgArray[12] = (ImageIO.read(Player.class.getResource("images/health3.png")));//12:healthBar 1
            
            gameImgArray[13] = (ImageIO.read(Player.class.getResource("images/enemy2R.png")));//13:moveable enemy right
            gameImgArray[13] = resizeEnemy(gameImgArray[13],30,60);
            gameImgArray[14] = (ImageIO.read(Player.class.getResource("images/enemy2L.png")));//13:moveable enemy Left
            gameImgArray[14] = resizeEnemy(gameImgArray[14],30,60);
            gameImgArray[15] = (ImageIO.read(Player.class.getResource("images/enemy1.png")));//14: enemy
            gameImgArray[15] = resizeCharacter(gameImgArray[15]);
            gameImgArray[16] = (ImageIO.read(Player.class.getResource("images/fireball.png")));//14: fireball
            gameImgArray[16] = resizeEnemy(gameImgArray[16],20,20);
            gameImgArray[17] = (ImageIO.read(Player.class.getResource("images/fireballL.png")));//14: fireball left
            gameImgArray[17] = resizeEnemy(gameImgArray[17],20,20);
            gameImgArray[19] = (ImageIO.read(Player.class.getResource("images/firebackground.jpg"))); 
            gameImgArray[20] = (ImageIO.read(Player.class.getResource("images/healthPotion.png"))); //Health potion
            gameImgArray[20] = resizePowerUp(gameImgArray[20]);
            gameImgArray[21] = (ImageIO.read(DistantLeap.class.getResource("images/background6.png")));// 
            gameImgArray[22] = (ImageIO.read(Player.class.getResource("images/sword.png")));
            gameImgArray[22] = resizeEnemy(gameImgArray[22],20,20);
            gameImgArray[23] = (ImageIO.read(Player.class.getResource("images/sworda.png")));
            gameImgArray[23] = resizeEnemy(gameImgArray[23],20,20);
            gameImgArray[24] = (ImageIO.read(Player.class.getResource("images/swordb.png")));
            gameImgArray[24] = resizeEnemy(gameImgArray[24],20,20);
            gameImgArray[25] = (ImageIO.read(Player.class.getResource("images/swordc.png")));
            gameImgArray[25] = resizeEnemy(gameImgArray[25],20,20);
            gameImgArray[26] = (ImageIO.read(Player.class.getResource("images/sword2.png")));
            gameImgArray[26] = resizeEnemy(gameImgArray[26],40,40);
            gameImgArray[27] = (ImageIO.read(Player.class.getResource("images/sword2a.png"))); 
            gameImgArray[27] = resizeEnemy(gameImgArray[27],45,45);
            gameImgArray[28] = (ImageIO.read(Player.class.getResource("images/sword2b.png")));
            gameImgArray[28] = resizeEnemy(gameImgArray[28],45,45);
            gameImgArray[29] = (ImageIO.read(Player.class.getResource("images/sword2c.png"))); 
            gameImgArray[29] = resizeEnemy(gameImgArray[29],45,45);
            gameImgArray[30] = (ImageIO.read(Player.class.getResource("images/sword2.png"))); 
            gameImgArray[30] = resizeEnemy(gameImgArray[30],45,45);
            gameImgArray[31] = (ImageIO.read(Player.class.getResource("images/dead.png"))); 
            gameImgArray[31] = resizeEnemy(gameImgArray[31],100,100);
            gameImgArray[32] = (ImageIO.read(Player.class.getResource("images/trophy.png"))); 
            gameImgArray[32] = resizeEnemy(gameImgArray[32],50,50);
            gameImgArray[33] = (ImageIO.read(Player.class.getResource("images/rocket.png"))); 
            gameImgArray[33] = resizeEnemy(gameImgArray[33],50,50);
            
        } catch (IOException ex) {
            Logger.getLogger(GameImages.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static BufferedImage resizeCharacter(BufferedImage originalImage) {
        BufferedImage resizedImage = new BufferedImage(50, 50, 2);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, 50, 50, null);
        g.dispose();

        return resizedImage;
    }
     private static BufferedImage resizeEnemy(BufferedImage originalImage,int a, int b) {
        BufferedImage resizedImage = new BufferedImage(a, b, 2);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, a, b, null);
        g.dispose();

        return resizedImage;
    }
    private static BufferedImage resizePlatform1(BufferedImage originalImage) {
        BufferedImage resizedImage = new BufferedImage(180, 110, 2);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, 180, 110, null);
        g.dispose();

        return resizedImage;
    }
    
    private static BufferedImage resizePlatform2(BufferedImage originalImage) {
        BufferedImage resizedImage = new BufferedImage(150, 90, 2);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, 150, 90, null);
        g.dispose();

        return resizedImage;
    }
    
    private static BufferedImage resizePowerUp(BufferedImage originalImage) {
        BufferedImage resizedImage = new BufferedImage(50, 50, 2);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, 50, 50, null);
        g.dispose();

        return resizedImage;
    }
    
    

    public BufferedImage getImage(int image) {
        return gameImgArray[image];
    }
    

    

}
