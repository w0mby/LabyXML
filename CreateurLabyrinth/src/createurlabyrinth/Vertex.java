/*
 * Vertex.java
 *
 * Created on 22 octobre 2006, 18:15
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package createurlabyrinth;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Représentation du point dans un espace à 3 dimensions
 * @author Damien Dormal
 */
public class Vertex {
    
    private float x,y,z;

    /**
     * crée un vertex à partir de ses valeurs passées en paramètre.
     * @param x sa valeur en x
     * @param y sa valeur en y
     * @param z sa valeur en z
     */
    public Vertex(float x,float y,float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    /**
     * Construit un vertex à partir d'un Element ( un noeud ) d'un DOM
     * @param vertex le noeud représentant le vertex, ce noeud vient d'un fichier xml.
     */
    public Vertex(Element vertex) {
        x = Float.parseFloat(vertex.getAttribute("x"));
        y = Float.parseFloat(vertex.getAttribute("y"));
        z = Float.parseFloat(vertex.getAttribute("z"));
    }
    
    /**
     * permet de crée l'élément ( noeud ) correspondant au vertex en question.
     * @param doc le document xml(on doit disposer de ce document pour instancier un élément)
     * on ne l'ajoutera pas ici bien sur.
     * @return le vertex sous la forme d'un element de DOM
     */
    public Element toElement(Document doc) {
        Element vertex = doc.createElement("vertex");
        vertex.setAttribute("x",Float.toString(x));
        vertex.setAttribute("y",Float.toString(y));
        vertex.setAttribute("z",Float.toString(z));
        return vertex;
    }

    /**
     * Retourne la valeur en x de notre point
     * @return la valeur en x
    */
    public float getX() {
        return x;
    }

    /**
     * Retourne la valeur en y de notre point
     * @return la valeur en y
     */
    public float getY() {
        return y;
    }

    /**
     * Retourne la valeur en x de notre point
     * @return la valeur en z
     */
    public float getZ() {
        return z;
    }
    
    
}
