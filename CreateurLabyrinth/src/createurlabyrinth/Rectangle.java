/*
 * Rectangle.java
 *
 * Created on 22 octobre 2006, 18:07
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package createurlabyrinth;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * Implémentation d'un rectangle dans un espace trois dimensions,
 * une texture lui est aussi appliquée.
 * @author Damien Dormal
 */
public class Rectangle {
    private String texture;
    private Vertex[] vertexs;
    private CoordTex[] coordsTex;
    private boolean mur;
    private boolean sol;
    
    /**
     * Créer un rectangle constitué d'une liste de vertex et d'une texture.
     * @param mur Permet de définir si le rectangle est un mur ou non.
     * @param sol Permet de définir si le rectangle est un sol ou non.
     * @param vertexs Les vertexs constituant le rectangle
     * @param coordsTex Coordonnée de la texture. Ces coordonnées représentent la maniere dont va être appliquée
     * la texture sur la surface Rectangle.
     * @param texture La texture qui va être appliquée au rectangle.
     * @throws java.lang.IllegalArgumentException Si le nombre de vertex est différent de 4 <B>OU</B> si le nombre de coordsTex est inférieur à 4
     */
    public Rectangle(Vertex[] vertexs,CoordTex[] coordsTex,String texture,boolean mur,boolean sol) throws IllegalArgumentException {
        if ( ! ( vertexs.length == 4 && coordsTex.length == 4 ) ) throw new IllegalArgumentException("les points du rectangle ainsi que ses coordonnés doivent etre au nombre de 4");
        this.vertexs = vertexs;
        this.coordsTex = coordsTex;
        this.texture = texture;
        this.mur = mur;
        this.sol = sol;
    }
    
    /**
     * Crée un rectangle à partir d'un Element d'un document xml.
     * @param rectangle L'élément(Noeud) d'un DOM.
     * @throws java.lang.IllegalArgumentException Si les vertexs et coordonées ne sont pas exactement on nombre de 4
     * ou alors le rectangle est défini comme un mur et un sol.
     */
    public Rectangle(Element rectangle) throws IllegalArgumentException {
        NodeList listeVertexs = rectangle.getElementsByTagName("vertex");
        NodeList listeCoords =  rectangle.getElementsByTagName("coordTex");
        if ( ! ( listeVertexs.getLength() == 4 && listeCoords.getLength() == 4 ) ) throw new IllegalArgumentException("les points du rectangle ainsi que ses coordonnés doivent etre au nombre de 4");
        vertexs = new Vertex[listeVertexs.getLength()];
        coordsTex = new CoordTex[listeCoords.getLength()];
        for (int i=0;i<listeVertexs.getLength();i++) {
            vertexs[i] = new Vertex( (Element) listeVertexs.item(i) );
            coordsTex[i] = new CoordTex( (Element) listeCoords.item(i) );
        }
        texture = rectangle.getAttribute("tex");
        String estMur = rectangle.getAttribute("mur");
        if (estMur.equals("true")) {
            mur = true;
        } else {
            mur = false;
        }           
        String estSol = rectangle.getAttribute("sol");
        if (estSol.equals("true")) {
            sol = true;
        } else {
            sol = false;
        }   
        if (sol && mur) throw new IllegalArgumentException("le rectangle est a la fois un mur et un sol, c'est une faute");
    }
    
    /**
     * permet de crée l'élément ( noeud et ses fils ) correspondant au rectangle en question.
     * @param doc le document xml(on doit disposer de ce document pour instancier un élément)
     * on ne l'ajoutera pas ici bien sur.
     * @return le rectangle sous la forme d'un élément de DOM (ainsi que ses Element Fils )
     */
    public Element toElement(Document doc) {
        Element rectangle = doc.createElement("rectangle");
        rectangle.setAttribute("tex",texture);
        if (mur) {
            rectangle.setAttribute("mur","true");
        } else {
            rectangle.setAttribute("mur","false");
        }
        if (sol) {
            rectangle.setAttribute("sol","true");
        } else {
            rectangle.setAttribute("sol","false");
        }
        for (int i=0;i<vertexs.length;i++) {
            rectangle.appendChild(vertexs[i].toElement(doc));
        }
        for (int i=0;i<coordsTex.length;i++) {
            rectangle.appendChild(coordsTex[i].toElement(doc));
        }
        return rectangle;
    }

    /**
     * Retourne l'URN texture qui est appliqué au rectangle
     * @return l'URN de la texture
     */
    public String getTexture() {
        return texture;
    }

    /**
     * aplique une nouvelle texture à un Element
     * @param texture la nouvelle URN de la texture à appliquer au rectangle
     */
    public void setTexture(String texture) {
        this.texture = texture;
    }

    /**
     * retourne le tableau de vertexs définissant le rectangle
     * @return le tableau de vertexs
     */
    public Vertex[] getVertexs() {
        return vertexs;
    }

    /**
     * retourne le tableau de coordonnées qui appliquent la texture au rectangle
     * @return le  tableau de coordonnées de texture
     */
    public CoordTex[] getCoordsTex() {
        return coordsTex;
    }

    /**
     * décrit si le rectangle est un mur
     * @return true si c'est un mur, false si c'est un plafond ou un sol
     */
    public boolean isMur() {
        return mur;
    }

    /**
     * décrit si le rectangle est un sol
     * @return true si c'est un sol, false si c'est un plafond ou si un mur
     */
    public boolean isSol() {
        return sol;
    }
    
    /**
     * Clone le rectangle.
     * @return le nouveau rectangle cloné.
     */
    public Object clone() {
        return new Rectangle(vertexs,coordsTex,texture,mur,sol);
    }
    
}










