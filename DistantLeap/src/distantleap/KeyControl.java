/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distantleap;

/**
 *
 * @author carolynchen
 */
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
/**
 *
 * @author jasonchow & carolynchen
 */
public class KeyControl extends Observable implements KeyListener  {
    private final Player p;
    private final int jump;
    private final int right;
    private final int left;
    private final int shoot;
    private Event e;
    
    boolean[] keys;
    
    public KeyControl(Event e,Player p, int jump, int left, int right,int shoot){
        
        keys = new boolean[99];
        
        
        this.p = p;
        this.jump = jump;
        this.right = right;
        this.left = left;
        this.shoot = shoot;
        this.e = e;
    }
    
    
    @Override
    public void keyPressed(KeyEvent k){

       // System.out.println("Pressed test");
        int keyPressed = k.getKeyCode();
        
        
     
        if (keyPressed == jump) {
            this.p.jump= true;
        }
        if (keyPressed == left) {
            this.p.left=true;
        }
        if (keyPressed == right) {
            this.p.right=true;
        }
         if (keyPressed == shoot) {
            this.p.shoot=true;
        }
 
        
        e.setValue(k);

    }
    @Override
    public void keyReleased(KeyEvent k){
      // System.out.println("Pressed test");
        int keyPressed = k.getKeyCode();
        if (keyPressed == jump) {
            this.p.jump = false;
        }

        if (keyPressed == left) {
            this.p.left=false;
        }
        if (keyPressed == right) {
            this.p.right=false;
        }
         if (keyPressed == shoot) {
            this.p.shoot=false;
        }
         e.setValue(k);
    
    }
    
    @Override
    public void keyTyped(KeyEvent e){

    }

    
    
}
