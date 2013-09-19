/*
 * Listener.java
 *
 * Created on 9 novembre 2006, 15:36
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package vueCreateurLaby;
import createurlabyrinth.*;

/**
 * interface devant suivre un ecouteur, doit juste fournir une methode update
 * @author Dormal
 */
public interface Listener {
    /**
     * rafraichit l'écouteur, et ceci par rapport à un sujet modèle
     * @param modele le sujet modele grace auquel l'ecouteur pourra être rafraichit.
     */ 
    public void update(Subject modele);
}
