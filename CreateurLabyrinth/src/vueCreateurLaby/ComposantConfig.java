/*
 * ComposantConfig.java
 */

package vueCreateurLaby;

import createurlabyrinth.Composant;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 * Permet de repr�senter un Composant disponible pour cr�er le labyrinthe sous forme d'un bouton
 *  que l'utilisateur va cliquer pour placer le composant repr�senter dans le Monde.
 * @author Bavay Jonathan & Damien Dormal
 */
public class ComposantConfig extends JButton{
    private int indexComposantCourant;
    private ImageIcon image;
    /**
     * Cr�e un bouton representant le Composant pass� en parametre.
     * @param texte Texte represantant le Composant repr�sent�.
     */
    public ComposantConfig(String texte) {
        setPreferredSize(new Dimension(50,30));
        setBackground(Color.WHITE);
        image = new ImageIcon(texte + ".GIF");
        setIcon(image);
    }
    /**
     * Permet d'assigner l'index du composant, present dans la liste de <B>Config</B>uration, repr�sent� par l'objet.
     * @param i index du Composant repr�sent� dans la liste des composants de <B>Config</B>uration
     */
    public void setIndexComposantCourant(int i){indexComposantCourant = i;} 
    /**
     * Recupere l'index du Composant repr�sent� dans la liste de <B>Config</B>uration
     * @return l'index du Composant repr�sent� dans la liste de <B>Config</B>uration
     */
    public int getIndexComposantCourant(){return indexComposantCourant;}
    /**
     * Retourne l'attribut image de l'objet
     * @return l'attribut image de l'objet
     */
    public Image getImage(){return image.getImage();}
}