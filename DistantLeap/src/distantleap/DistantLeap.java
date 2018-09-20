/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distantleap;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JApplet;

/**
 *
 * @author User
 */
public class DistantLeap extends JApplet implements Runnable, ActionListener {

    Thread thread;
    Graphics2D gfx;

    BufferedImage bimg;
    BufferedImage playerImg;

    private final int width = 500;
    private final int height = 3375;

    private final int widthWindow = 500;
    private final int heightWindow = 630;

    Button startButton = new Button("START GAME");

    private Event event;
    private Sound bmusic;
    private KeyControl key;
    private int enemy;

    static ArrayList<platforms> platformList;
    static ArrayList<enemy> enemiesList;
    static ArrayList<bullet> bulletList;
    static ArrayList<PowerUp> powerupList;

    private Random randomPlatform = new Random();
    private Random platformType = new Random();
    private Random enemyType = new Random();
    
    int randomPlat, platType;
    private int rocketX, rocketY;
    
    private int playerHeight;

    int startGame = 0;

    GameImages gImg = new GameImages();
    platforms platform;
    Player player;
    private CollisionCheck CC;

    @Override
    public void init() {
        setSize(widthWindow, heightWindow);
        setBackground(Color.white);
        setFocusable(true);

        setLayout(null);
        startButton.setBounds(150, 300, 200, 100);
        add(startButton);
        startButton.addActionListener(this);

        platformList = new ArrayList<>();
        enemiesList = new ArrayList<>();
        bulletList = new ArrayList<>();
        powerupList = new ArrayList<>();

        try {
            player = new Player(gImg.getImage(0), 200, 2890);
            platformList.add(new platforms(gImg.getImage(2), 150, 2935, 1));

            for (int i = 400; i < 2935; i += 170) {
                randomPlat = randomPlatform.nextInt(400);
                platType = platformType.nextInt(2) + 2;
                enemy = enemyType.nextInt(3);
                int move = randomPlatform.nextInt(3);
                platformList.add(new platforms(gImg.getImage(platType), randomPlat, i, move));
                if (enemy == 0) {
                    enemiesList.add(new enemy(gImg.getImage(14), randomPlat, i - 50, true));
                    //System.out.println("enemy");
                } else if (enemy == 1 && move != 0) {
                    enemiesList.add(new enemy(gImg.getImage(15), randomPlat + (gImg.getImage(platType).getWidth() / 2), i - 30, false));
                }
            }

            powerupList.add(new PowerUp(gImg.getImage(26), platformList.get(platformList.size() - 2).getX() + 50, platformList.get(platformList.size() - 2).getY() - 40, 0));
            powerupList.add(new PowerUp(gImg.getImage(26), platformList.get(platformList.size() - 7).getX() + 50, platformList.get(platformList.size() - 7).getY() - 40, 0));
            powerupList.add(new PowerUp(gImg.getImage(20), platformList.get(platformList.size() - 3).getX() + 50, platformList.get(platformList.size() - 3).getY() - 40, 1));
            powerupList.add(new PowerUp(gImg.getImage(20), platformList.get(platformList.size() - 8).getX() + 50, platformList.get(platformList.size() - 8).getY() - 40, 1));
            powerupList.add(new PowerUp(gImg.getImage(33), platformList.get(platformList.size() - 9).getX() + 50, platformList.get(platformList.size() - 9).getY() - 40, 3));
            powerupList.add(new PowerUp(gImg.getImage(33), platformList.get(platformList.size() - 11).getX() + 50, platformList.get(platformList.size() - 11).getY() - 40, 4));
            powerupList.add(new PowerUp(gImg.getImage(32), platformList.get(platformList.size() - 15).getX() + 50, platformList.get(platformList.size() - 15).getY() - 40, 2));

            rocketX = platformList.get(platformList.size() - 11).getX() + 50;
            rocketY = platformList.get(platformList.size() - 11).getY() - 40;
            
            player.setBoost(rocketX, rocketY);
                    
            System.out.println(platformList.size());

        } catch (IOException ex) {
            Logger.getLogger(DistantLeap.class.getName()).log(Level.SEVERE, null, ex);
        }

        event = new Event();
        if (event == null) {
            System.out.println("Event null");
        }
        key = new KeyControl(event, player, KeyEvent.VK_UP, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_SPACE);
        this.addKeyListener(key);
        this.event.addObserver(player);

        bmusic = new Sound("images/bmusic.wav");
        CC = new CollisionCheck();

    }

    public void ScreenCapture() {
        playerHeight = player.getYLocation() - 490;
        
        if(playerHeight > 0 && playerHeight < 3000){
            playerImg = bimg.getSubimage(0, playerHeight, 500, 600);
        }
        else{
            playerImg = bimg.getSubimage(0, 0, 500, 600);
        }
    }

    @Override
    public void paint(Graphics g) {
        if (bimg == null) {
            bimg = (BufferedImage) createImage(width, height);
            gfx = bimg.createGraphics();
        }

        //Player Screen
        ScreenCapture();

        if (startGame == 1) {
            g.drawImage(playerImg, 0, 30, this);
        } else {
            g.drawImage(gImg.getImage(21), 0, 0, 500, 600, this);
            g.setFont(new Font("Consolas", Font.PLAIN, 36));
            // g.setColor(Color.black);
            g.drawString("Distant Leap", 145, 200);
            g.drawImage(gImg.getImage(1), 90, 320, this);
            g.drawImage(gImg.getImage(14), 360, 320, this);

            g.setFont(new Font("Consolas", Font.PLAIN, 16));
            g.drawString("Created By: Carolyn Chen & Jason Chow", 100, 550);
        }

        if (startGame == 1) {
            backgroundDraw();
            platformDraw();
            enemiesDraw();
            playerDraw();
            bulletDraw();
            powerupDraw();
            
            if(player.getVictory()){
                victoryDraw();
            }

            if (player.getLifeInt() <= 0) {
                gameoverDraw();
            }
            g.setColor(Color.darkGray);

            g.fillRect(0, 0, 600, 30);
            g.setFont(new Font("Consolas", Font.PLAIN, 20));
            g.setColor(Color.white);
            g.drawString("Score: ", 10, 20);
            g.drawString(player.getScore(), 70, 20);
            g.drawString("Distant Leap", 190, 20);
            g.drawString("Lives: ", 400, 20);
            g.drawString(player.getLife(), 460, 20);
        }

    }

    public void backgroundDraw() {
        gfx.drawImage(gImg.getImage(6), 0, 0, 500, 600, this);
        gfx.drawImage(gImg.getImage(8), 0, 600, 500, 600, this);
        gfx.drawImage(gImg.getImage(7), 0, 1200, 500, 600, this);
        gfx.drawImage(gImg.getImage(5), 0, 1800, 500, 600, this);
        gfx.drawImage(gImg.getImage(4), 0, 2400, 500, 600, this);
        gfx.drawImage(gImg.getImage(19), 0, 3000, 500, 375, this);

    }

    public void playerDraw() {
        player.draw(gfx, this);
        //System.out.println("player");

    }

    public void platformDraw() {
        for (int i = 0; i < platformList.size(); i++) {
            platformList.get(i).draw(gfx, this);
        }
    }

    public void enemiesDraw() {
        for (int i = 0; i < enemiesList.size(); i++) {
            enemiesList.get(i).draw(gfx, this);
            for (int w = 0; w < bulletList.size(); w++) {
                if (i < enemiesList.size()) {
                    CC.BandE(bulletList.get(w), enemiesList.get(i), player);
                }
                if (i < enemiesList.size()) {
                    CC.PaAndE(player, enemiesList.get(i));
                }
            }

        }
    }

    public void bulletDraw() {
        for (int i = 0; i < bulletList.size(); i++) {
            if (bulletList.get(i).x > 500 || bulletList.get(i).x < 0) {
                bulletList.remove(i);
            } else {
                bulletList.get(i).draw(gfx, this);
                CC.BandP(bulletList.get(i), player);
            }

        }
    }

    public void powerupDraw() {
        for (int i = 0; i < powerupList.size(); i++) {
            powerupList.get(i).draw(gfx, this);
            if (i < powerupList.size()) {
                CC.PowerupAndPlayer(player, powerupList.get(i));
            }
        }
    }

    public void gameoverDraw() {
        gfx.setColor(Color.white);
        gfx.setFont(new Font("Consolas", Font.PLAIN, 36));
        gfx.drawString("GAME OVER", 145, player.getYLocation() - 290);
    }
    
    public void victoryDraw(){
        gfx.setColor(Color.white);
        gfx.setFont(new Font("Consolas", Font.PLAIN, 36));
        gfx.drawString("YOU WIN!", 175, 290);
    }

    @Override
    public void run() {
        Thread game = Thread.currentThread();

        while (thread == game) {
            repaint();

            try {
                thread.sleep(25);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    @Override
    public void start() {
        thread = new Thread(this);
        thread.setPriority(Thread.MIN_PRIORITY);
        thread.start();
        bmusic.start();

    }

    @Override
    public void stop() {
        bmusic.stop();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        startGame = 1;
        startButton.setVisible(false);
    }
}
