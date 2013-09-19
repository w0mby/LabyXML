/*
 * VueBloc.java
 *
 * Created on 14 novembre 2006, 17:29
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package vueCreateurLaby;

import createurlabyrinth.Composant;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * permet de représenter un bloc sur la VueLaby
 * @author Bavay Jonathan & Damien Dormal
 */
public class VueBloc extends JButton implements ActionListener{
    private int indexComposant = -1;
    private int indexComposantCourant = -1;
    /**
     * Crée une nouvelle instance de VueBloc
     */
    public VueBloc() {
        setPreferredSize(new Dimension(15,15));
    }
    /**
     * Permet de définir l'index du composant auquel appartient le bloc. Cet index est celui du composant dans 
     * la liste de composant contenue dans le Monde.
     * @param i index du Composant auquel appartient le bloc.
     */
    public void setIndexComposant(int i){indexComposant = i;}
    /**
     * Permet de recuperer l'index du composant auquel appartient le bloc
     * @return return l'index du composant auquel appartient le bloc
     */
    public int getIndexComposant(){return indexComposant;}
    /**
     * Permet de définir l'index du composant selectionné dans le composant ControleurBouton.
     * @return l'index du composant actuellement selectionné par l'utilisateur.
     */
    public int getIndexComposantCourant(){return indexComposantCourant;}
    
    /**
     * Va modifié l'<code>indexComposantCourant</code> du bloc lorsque l'utilisateur va selectionner 
     * un Composant
     * @param e evement ayant déclencher l'appelle à la méthode.
     * @see ControleurBouton
     */
    public void actionPerformed(ActionEvent e) {
            indexComposantCourant = ((ComposantConfig)e.getSource()).getIndexComposantCourant();
        /*
            Toolkit tk = Toolkit.getDefaultToolkit();
            Cursor monCurseur = tk.createCustomCursor(((ComposantConfig)e.getSource()).getImage(), new Point(), "f");
            setCursor(monCurseur);
         */

    }
}
