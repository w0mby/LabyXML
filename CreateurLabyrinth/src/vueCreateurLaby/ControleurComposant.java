/*
 * ControleurComposant.java
 *
 * Created on 16 novembre 2006, 17:26
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package vueCreateurLaby;

import createurlabyrinth.Monde;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * "Panel" qui va contenir les objets permettant de modifiés et de voir les attributs d'un composant
 * @author Bavay Jonathan & Damien Dormal
 * @see SelectPropertyComposant
 * @see VueComposant
 */
public class ControleurComposant extends JPanel {
    private SelectPropertyComposant propComposant;
    private VueComposant texComposant;
    
    /**
     * Creer une nouvelle instance d'un Composant
     * @param monde Monde complet utile pour les deux objets qui vont être contenu.
     */
    public ControleurComposant(Monde monde) {
        setPreferredSize(new Dimension(450,300));
        propComposant = new SelectPropertyComposant(monde);
        texComposant = new VueComposant(monde);
        add(propComposant);
        add(texComposant);
    }
    /**
     * Retourne un objet de type SelectPropertyComposant
     * @return l'objet SelectPropertyComposant contenu.
     */
    public SelectPropertyComposant getPropComposant(){ return propComposant;}
    /**
     * Retourne un objet de type VueComposant
     * @return l'objet VueComposant contenu.
     */
    public VueComposant getTexComposant(){ return texComposant;}
}
