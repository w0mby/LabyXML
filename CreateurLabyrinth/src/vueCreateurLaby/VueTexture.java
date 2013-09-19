/*
 * VueTexture.java
 *
 * Created on 16 novembre 2006, 22:58
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package vueCreateurLaby;

import java.awt.*;
import javax.swing.*;


/**
 * Permet d'afficher une texture d'un composant.
 * @author Bavay Jonathan & Damien Dormal
 */
public class VueTexture extends JPanel {
    private Image img;
    
    /**
     * Creer une nouvelle instanciation d'une VueTexture sans appliqué d'image.
     */
    public VueTexture(){
        setPreferredSize(new Dimension(100,100));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        repaint();
    }
    /**
     * Creer une instanciation d'une VueTexture par rapport a l'URN passé en paramètre
     * @param file Fichier contenant l'image de la texture à afficher.
     */
    public VueTexture(String file) {
        setPreferredSize(new Dimension(100,100));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        img = new ImageIcon(file).getImage();
        repaint();
    }
    /**
     * Permet de modifier l'attribut img de l'objet.
     * @param file Nouvelle image.
     */
    public void setImage(String file){
        img = new ImageIcon(file).getImage();
        repaint();
    }
    
    /**
     * Methode permettant de redessiner le composant graphique.
     * @param g 
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (img == null) return;
        int w = img.getWidth(this);
        int h = img.getHeight(this);
        boolean zoom = (w > getWidth() || h > getHeight());
        if (zoom) g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
        else g.drawImage(img, (getWidth()-w)/2, (getHeight()-h)/2, this);
    }
}
