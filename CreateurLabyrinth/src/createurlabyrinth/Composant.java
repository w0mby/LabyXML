/*
 * Composant.java
 *
 * Created on 25 octobre 2006, 23:04
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package createurlabyrinth;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * Un composant est un emsemble de bloc qui forme une structure complexe
 * @author Damien Dormal
 */
public class Composant implements Cloneable{
    private Bloc[] blocs;
    private String type;
    private float x,y,z;
    
    /**
     * Construit un composant à partir d'un noeud Element du document XML
     * @param composant le noeud servant de base a la construiction du composant
     */
    public Composant(Element composant) {
        type = composant.getAttribute("type");
        setX(Float.parseFloat(composant.getAttribute("x")));
        setY(Float.parseFloat(composant.getAttribute("y")));
        setZ(Float.parseFloat(composant.getAttribute("z")));
        NodeList listeBlocs = composant.getElementsByTagName("bloc");
        blocs = new Bloc[listeBlocs.getLength()];
        for (int i=0;i<getBlocs().length;i++) {
            getBlocs()[i] = new Bloc( (Element) listeBlocs.item(i));
        }
    }
    
    /**
     * Construit un composant a partir d'un tableau de bloc et d'une position 3D dans l'espace.
     * @param blocs Tableau des blocs constituant le composant
     * @param x position en x dans l'espace
     * @param y position en y dans l'espace
     * @param z position en z dans l'espace
     * @param type Détermine le type de composant.
     */
    public Composant(Bloc[] blocs,float x,float y,float z,String type) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.type = type;
        this.blocs = blocs;
    }
    
    /**
     * permet de crée l'élément ( noeud et ses fils ) correspondant au composant en question
     * @param doc le document xml(on doit disposé de ce document pour instancier un élément)
     * on ne l'ajoutera pas ici bien sur.
     * @return le composant sous la forme d'un element de DOM (ainsi que ses Element Fils )
     */
    public Element toElement(Document doc) {
        Element composant = doc.createElement("composant");
        composant.setAttribute("type", getType());
        composant.setAttribute("x",Float.toString(getX()));
        composant.setAttribute("y",Float.toString(getY()));
        composant.setAttribute("z",Float.toString(getZ()));
        for (int i=0;i<getBlocs().length;i++) {
            composant.appendChild( getBlocs()[i].toElement(doc) );
        }
        return composant;
    }
    
    /**
     * retourne la texture du premier mur de bloc rencontré dans le composant
     * @return un string qui reprend la texture
     * peut retourné null si aucun mur n'est présent
     */
    public String getTexMur() {
       int i=0;
       String tex = null;
       while (i<blocs.length && tex==null) {
           tex = blocs[i].getTexMur();
           i++;
       }
       return tex;
    }
    
    /**
     * assigne une texture à tout les murs des blocs du composant
     * @param URN un string representant l'URN de notre texture.
     */
    public void setTexMur(String URN) {
        for (int i=0;i<blocs.length;i++) {
            blocs[i].setTexMur(URN);
        }      
    }
    
    /**
     * retourne la texture du premier sol de bloc rancontré dans le composant
     * @return un string qui reprend la texture
     * peut retourné null si aucun sol n'estr present
     */
    public String getTexSol() {
       int i=0;
       String tex = null;
       while (i<blocs.length && tex==null) {
           tex = blocs[i].getTexSol();
           i++;
       }
       return tex;
    }
    
    /**
     * assigne une texture à tout les sols des blocs du composant
     * @param URN un string representant l'URN de notre texture.
     */
    public void setTexSol(String URN) {
        for (int i=0;i<blocs.length;i++) {
            blocs[i].setTexSol(URN);
        }     
    }
    
    /**
     * retourne la texture du premier plafond de bloc rencontré dans le composant
     * @return un string qui reprend la texture
     * peut retourné null si aucun plafond n'est present
     */
    public String getTexPlafond() {
      int i=0;
       String tex = null;
       while (i<blocs.length && tex==null) {
           tex = blocs[i].getTexPlafond();
           i++;
       }
       return tex;
    }
    
    /**
     * assigne une texture à tout les plafonds des blocs du composant
     * @param URN un string représentant l'URN de notre texture.
     */
    public void setTexPlafond(String URN) {
        for (int i=0;i<blocs.length;i++) {
            blocs[i].setTexPlafond(URN);
        }   
    }
    
    /**
     * retourne le tableau des blocs qui constituent le composant
     * @return un tableau de blocs
     */
    public Bloc[] getBlocs() {
        return blocs;
    }

    /**
     * retourne le type donnée à notre composant.
     * @return le string répresentant le type du composant
     */
    public String getType() {
        return type;
    }

    /**
     * retourne la valeur en x de la coordonnée du composant par rapport au monde
     * @return le float
     */
    public float getX() {
        return x;
    }

    /**
     * assigne la valeur en x de la coordonnée du composant par rapport au monde
     * @param x le float à assigner
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * retourne la valeur en y de la coordonnée du composant par rapport au monde
     * @return le float
     */
    public float getY() {
        return y;
    }

    /**
     * assigne la valeur en y de la coordonnée du composant par rapport au monde
     * @param y le float à assigner
     */
    public void setY(float y) {
        this.y = y;
    }

   /**
     * retourne la valeur en z de la coordonnée du composant par rapport au monde
     * @return le float
     */
    public float getZ() {
        return z;
    }

    /**
     * assigne la valeur en z de la coordonnée du composant par rapport au monde
     * @param z le float à assigner
     */
    public void setZ(float z) {
        this.z = z;
    }
    /**
     * Permet de cloner un composant. Il est obligatoire de cloner un composant. 
     * Dans le cas contraire lorsque l'on va placer un composant sur la grille depuis l'arbre de Configuration
     * (configuration.xml), on ne pourra placer qu'un seul composant de chaque type.
     * @throws java.lang.CloneNotSupportedException Exception définissant que l'objet que l'on veut cloner n'est pas clonable.
     * @return le composant cloné.
     */
    public Object clone() throws CloneNotSupportedException{     
        Bloc[] blocs = new Bloc[this.blocs.length];
        for (int i=0;i<blocs.length;i++) {
            blocs[i] = (Bloc)this.blocs[i].clone();
        }
        return new Composant(blocs,x,y,z,type);
    }
}
