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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author carolynchen
 */
class Sound {
   private AudioInputStream soundStream; 
   private String soundFile;
   private Clip c;
   
   
   public Sound(String File){
       this.soundFile = File;
       try{
           soundStream = AudioSystem.getAudioInputStream(Sound.class.getResource(soundFile));
           c = AudioSystem.getClip();
           c.open(soundStream);
       }
       catch(Exception e){
           System.out.println(e.getMessage() + " No sound");
       
       }
   }
   
   public void start(){
       c.start();
   }
   public void stop(){
       c.stop();
   }
}


