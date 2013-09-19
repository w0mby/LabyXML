/*
 * Subject.java
 *
 * Created on 9 novembre 2006, 15:37
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package createurlabyrinth;

import java.util.*;
import vueCreateurLaby.*;

/**
 * un Subject est un sujet d'observation, il permet de prévenir du changement tout ses ecouteurs( listener )
 * @see Listener
 * @author Damien Dormal
 */
public class Subject {
    private List<Listener> listeners;
    
    /**
     * crée le sujet
     */
    public Subject() {
        listeners = new ArrayList<Listener>();
    } 
    
    /**
     * ajoute un écouteur au sujet
     * @param listener l'écouteur à ajouter
     */
    public void addListener(Listener listener) {
        listener.update(this);
        listeners.add(listener);
    }
    
    /**
     * retire un écouteur au sujet
     * @param listener l'écouteur à retirer
     */
    public void removeListener(Listener listener) {
        listeners.remove(listener);
    }
   
    /**
     * rafraichit tout les écouteur abonné au sujet.
     */
    public void fireUpdate() {
        for (Listener listener:listeners) {
            listener.update(this);
        }
    }
    
}
