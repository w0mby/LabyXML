/*
 * CoordTex.java
 *
 * Created on 22 octobre 2006, 18:18
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package createurlabyrinth;

import org.w3c.dom.Element;
import org.w3c.dom.Document;

/**
 * Represente une coordonnée de texture
 * le bas à gauche d'un rectangle serait par exemple (0,0) 
 * et le haut a droite serait (1,1)
 * @author Damien Dormal
 */
public class CoordTex {
    
    private int x,y;
    
    /**
     * Crée la coordonnée en assignant sa valeur en x et y
     * @param x la valeur en x de la coordonnée
     * @param y la valeur en y de la coordonnée
     * @throws java.lang.IllegalArgumentException si on assigne une valeur en x ou y qui n'appartient pas a 0 et 1
     */
    public CoordTex(int x,int y) throws IllegalArgumentException {
        if ( ! ( (x  == 0 || x == 1) && (y == 1 || y == 0) )  ) throw new IllegalArgumentException("la coordonnée doit etre comprise entre 0 et 1");
        this.x = x;
        this.y = y;
    }
    
    /**
     * Construit une coordonnée de texture à partir d'un Element ( un noeud ) d'un DOM
     * @param coord le noeud réprésentant la coordonnée, ce noeud vient d'un fichier xml
     * @throws java.lang.IllegalArgumentException si on assigne une valeur en x ou y qui n'appartient pas a 0 et 1
     */
    public CoordTex(Element coord) throws IllegalArgumentException {
        int x = Integer.parseInt(coord.getAttribute("x"));
        int y = Integer.parseInt(coord.getAttribute("y")); 
        if ( ! ( (x  == 0 || x == 1) && (y == 1 || y == 0) )  ) throw new IllegalArgumentException("la coordonnée doit etre comprise entre 0 et 1");
        this.x = x;
        this.y = y;
    }
    
    /**
     * permet de crée l'élément ( noeud ) correspondant à la coordonnée de texture en question
     * @param doc le document xml (on doit disposer de ce document pour instancier un élément)
     * on ne l'ajoutera pas ici bien sur.
     * @return la coordonnée sous la forme d'un element de DOM
     */
    public Element toElement(Document doc) {
        Element coord = doc.createElement("coordTex");
        coord.setAttribute("x",Integer.toString(x));
        coord.setAttribute("y",Integer.toString(y));
        return coord;
    }
    
    /**
     * retourne la valeur en x de la coordonnée
     * @return la valeur en x
     */
    public int getX() {
        return x;
    }

    /**
     * retourne la valeur en y de la coordonnée
     * @return la valeur en y
     */
    public int getY() {
        return y;
    }
    
}
