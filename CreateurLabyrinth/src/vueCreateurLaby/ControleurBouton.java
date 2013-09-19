/*
 * ControleurBouton.java
 *
 * Created on 14 novembre 2006, 18:22
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package vueCreateurLaby;

import createurlabyrinth.Config;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Contient tout les boutons représentant les differents composant disponible pour créer le Monde
 * @author Bavay Jonathan & Damien Dormal
 */
public class ControleurBouton extends JPanel implements ActionListener{
    private Vector<ComposantConfig> btnComposants;
    private Config config;
    
    /**
     * Crée une nouvelle instance de ControleurBouton à partir du fichier configuration.xml.
     * @param config URN du fichier de configuration contenant tout les composants disponible pour creer un Monde.
     */
    public ControleurBouton(Config config){
        btnComposants = new Vector<ComposantConfig>();
        this.config = config;
        setLayout(new GridLayout(10,2));
        for(int i=0;i<config.getComposants().size();i++){
            btnComposants.add(new ComposantConfig(config.getComposants().get(i).getType()));
            btnComposants.get(i).setIndexComposantCourant(i);
            btnComposants.get(i).addActionListener(this);
            add(btnComposants.get(i));
        }
        
    }
    /**
     * Retourne le tableau contenant tout les boutons représentants les composant disponible pour creer
     * le Monde.
     * @return les boutons contenus.
     */
    public Vector<ComposantConfig> getBtnComposants(){return btnComposants;}

    /**
     * Action effectuée lors d'un clique sur un bouton. Ici on applique une couleur de background differente
     * des autres sur le bouton actif.
     * @param e Evenement ayant provoqué l'appel a la méthode.
     */
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() instanceof ComposantConfig){
            for(int i = 0;i<btnComposants.size();i++){
                if(btnComposants.get(i).getBackground() == Color.RED){
                   btnComposants.get(i).setBackground(Color.WHITE);
                   i = btnComposants.size();
                }
            }
            ((ComposantConfig)e.getSource()).setBackground(Color.RED);
        }
    }
}
