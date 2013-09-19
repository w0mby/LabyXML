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
 * Represente une coordonn�e de texture
 * le bas � gauche d'un rectangle serait par exemple (0,0) 
 * et le haut a droite serait (1,1)
 * @author Damien Dormal
 */
public class CoordTex {
    
    private int x,y;
    
    /**
     * Cr�e la coordonn�e en assignant sa valeur en x et y
     * @param x la valeur en x de la coordonn�e
     * @param y la valeur en y de la coordonn�e
     * @throws java.lang.IllegalArgumentException si on assigne une valeur en x ou y qui n'appartient pas a 0 et 1
     */
    public CoordTex(int x,int y) throws IllegalArgumentException {
        if ( ! ( (x  == 0 || x == 1) && (y == 1 || y == 0) )  ) throw new IllegalArgumentException("la coordonn�e doit etre comprise entre 0 et 1");
        this.x = x;
        this.y = y;
    }
    
    /**
     * Construit une coordonn�e de texture � partir d'un Element ( un noeud ) d'un DOM
     * @param coord le noeud r�pr�sentant la coordonn�e, ce noeud vient d'un fichier xml
     * @throws java.lang.IllegalArgumentException si on assigne une valeur en x ou y qui n'appartient pas a 0 et 1
     */
    public CoordTex(Element coord) throws IllegalArgumentException {
        int x = Integer.parseInt(coord.getAttribute("x"));
        int y = Integer.parseInt(coord.getAttribute("y")); 
        if ( ! ( (x  == 0 || x == 1) && (y == 1 || y == 0) )  ) throw new IllegalArgumentException("la coordonn�e doit etre comprise entre 0 et 1");
        this.x = x;
        this.y = y;
    }
    
    /**
     * permet de cr�e l'�l�ment ( noeud ) correspondant � la coordonn�e de texture en question
     * @param doc le document xml (on doit disposer de ce document pour instancier un �l�ment)
     * on ne l'ajoutera pas ici bien sur.
     * @return la coordonn�e sous la forme d'un element de DOM
     */
    public Element toElement(Document doc) {
        Element coord = doc.createElement("coordTex");
        coord.setAttribute("x",Integer.toString(x));
        coord.setAttribute("y",Integer.toString(y));
        return coord;
    }
    
    /**
     * retourne la valeur en x de la coordonn�e
     * @return la valeur en x
     */
    public int getX() {
        return x;
    }

    /**
     * retourne la valeur en y de la coordonn�e
     * @return la valeur en y
     */
    public int getY() {
        return y;
    }
    
}
