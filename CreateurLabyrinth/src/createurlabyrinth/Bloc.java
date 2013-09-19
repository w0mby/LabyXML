/*
 * Bloc.java
 *
 * Created on 22 octobre 2006, 20:11
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package createurlabyrinth;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * Implémentation d'un bloc de rectangles, une surface qui est obligatoirement rectangulaire
 * @author Damien Dormal
 */
public class Bloc {
    private float x,y,z; // sa position relative au composant que le contient
    private Rectangle[] rectangles;
    
    
    /**
     * Construit un bloc à partir d'un élément d'un document xml.
     * @param bloc l'element (noeud du DOM) qui represente notre bloc
     */
    public Bloc(Element bloc) {
        x = Float.parseFloat(bloc.getAttribute("x"));
        y = Float.parseFloat(bloc.getAttribute("y"));
        z = Float.parseFloat(bloc.getAttribute("z"));
        NodeList listeRectangles = bloc.getElementsByTagName("rectangle");
        rectangles = new Rectangle[listeRectangles.getLength()];
        for (int i=0;i<listeRectangles.getLength();i++) {
            rectangles[i] = new Rectangle( (Element)listeRectangles.item(i));
        }
    }
    
    /**
     * Construit un bloc grâce à un tableau de rectangles et les coordonnées dans l'espace 3D
     * @param rectangles tableau de rectangles qui seront les differents rectangles constituant le Bloc
     * @param x position en x du bloc dans l'espace 3D
     * @param y position en y du bloc dans l'espace 3D
     * @param z position en z du bloc dans l'espace 3D
     */
    public Bloc(Rectangle[] rectangles,float x,float y,float z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.rectangles = rectangles;
    }
    
    /**
     * permet de crée l'élément ( noeud et ses fils ) correspondant au bloc en question
     * @param doc le document xml(on doit disposer de se document pour instancier un element)
     * on ne l'ajoutera pas ici biensur.
     * @return le bloc sous la forme d'un element de DOM (ainsi que ses Element Fils )
     */
    public Element toElement(Document doc) {
        Element bloc = doc.createElement("bloc");
        bloc.setAttribute("x",Float.toString(x));
        bloc.setAttribute("y",Float.toString(y));
        bloc.setAttribute("z",Float.toString(z));
        for (int i=0;i<rectangles.length;i++) {
            bloc.appendChild(rectangles[i].toElement(doc));
        }
        return bloc;
    }
    
    /**
     * retourne la texture du premier mur(Rectangle) rencontré dans le bloc
     * @return un string qui reprense la texture du premier recantgle mur rencontré,
     * peut retourné null si aucun mur n'estr present
     */
    public String getTexMur()  {
       int i=0;
       String tex = null;
       while (i<rectangles.length && tex==null) {
           if (rectangles[i].isMur()) {
               tex = rectangles[i].getTexture();
           }
           i++;
       }
       return tex;
    }
    
    /**
     * assigne une texture à tout les murs du bloc
     * @param URN un string representant l'URN de notre texture.
     */
    public void setTexMur(String URN) {
        for (int i=0;i<rectangles.length;i++) {
            if (rectangles[i].isMur()) rectangles[i].setTexture(URN);
        }      
    }
    
    /**
     * retourne la texture du premier sol(Rectangle) rencontré dans le bloc
     * @return un string qui reprense la texture du premier recantgle sol rencontré,
     * peut retourné null si aucun sol n'estr present
     */
    public String getTexSol() {
       int i=0;
       String tex = null;
       while (i<rectangles.length && tex==null) {
           if (rectangles[i].isSol()) {
               tex = rectangles[i].getTexture();
           }
           i++;
       }
       return tex;
    }
    
    /**
     * assigne une texture à tout les sols du bloc
     * @param URN un string representant l'URN de notre texture.
     */
    public void setTexSol(String URN) {
        for (int i=0;i<rectangles.length;i++) {
            if (rectangles[i].isSol()) rectangles[i].setTexture(URN);
        }  
    }
    
    /**
     * retourne la texture du premier plafond(Rectangle) rencontré dans le bloc
     * @return un string qui reprense la texture du premier recantgle plafond rencontré,
     * peut retourné null si aucun plafond n'est present
     */
    public String getTexPlafond() {
       int i=0;
       String tex = null;
       while (i<rectangles.length && tex==null) {
           if ( (!rectangles[i].isSol()) && (!rectangles[i].isMur()) ) {
               tex = rectangles[i].getTexture();
           }
           i++;
       }
       return tex;
    }
    
    /**
     * assigne une texture à tout les plafonds du bloc
     * @param URN un string representant l'URN de notre texture.
     */
    public void setTexPlafond(String URN) {
        for (int i=0;i<rectangles.length;i++) {
            if ( (!rectangles[i].isSol()) && (!rectangles[i].isMur()) ) rectangles[i].setTexture(URN);
        }  
    }
    
    /**
     * retoure la valeur en x de la coordonnée relative du bloc par rapport au composant.
     * @return le float
     */
    public float getX() {
        return x;
    }

    /**
     * retoure la valeur en y de la coordonnée relative du bloc par rapport au composant.
     * @return le float
     */
    public float getY() {
        return y;
    }

    /**
     * retoure la valeur en z de la coordonnée relative du bloc par rapport au composant.
     * @return le float
     */
    public float getZ() {
        return z;
    }

    /**
     * Retourne le tableau de rectangles qui composent notre bloc
     * @return un tableau de rectangles
     */
    public Rectangle[] getRectangles() {
        return rectangles;
    }
    
    /**
     * Methode permettant de cloner un bloc. Le clonage d'un bloc doit etre fait lorsqu'on recupere un element
     * de l'arbre de Configuration (configuration.xml) pour l'utiliser dans le labyrinthe a proprement dit.
     * Sinon le changement d'un attribut sur un bloc d'un composant se fera sur tout les composants similaires.
     * @return Le nouveau bloc cloné.
     */
    public Object clone() {
        Rectangle[] rectangles = new Rectangle[this.rectangles.length];
        for (int i=0;i<rectangles.length;i++) {
            rectangles[i] = (Rectangle)this.rectangles[i].clone();
        }
        return new Bloc(rectangles,x,y,z);
    }
    
}
