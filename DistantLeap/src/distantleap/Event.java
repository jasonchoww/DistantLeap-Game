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
import java.util.Observable;
/**
 *
 * @author carolynchen
 */

public class Event extends Observable {
    Object event;

    public Event(){

    }
    public void setValue(KeyEvent e){
        event = e;
        setChanged();
        notifyObservers(event);
    }
    
    public Object getEvent(){
        return event;
    }
    
    public void setValue(String msg) {
          setChanged();
         // trigger notification
         notifyObservers(event);  
        }


 }



