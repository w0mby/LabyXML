/*
 * Monde.java
 *
 * Created on 30 octobre 2006, 17:26
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package createurlabyrinth;
import java.util.*;
import java.io.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;
import org.xml.sax.*;

/**
 * la définition d'un monde ( un labyrinthe ), c'est le modèle de l'application.
 * @author Damien Dormal
 */
public class Monde extends Subject{
    
    private List<Composant> composants;
    private int longueur;
    private int largeur;
    
    /**
     * Crée notre monde à partir d'un document XML.
     * @param URN l'URN de notre document XML
     */
    public Monde(String URN) {
        setMonde(URN);
    }
    
    /**
     * Crée un monde vide.
     * @param longueur longueur de notre monde
     * @param largeur largeur de notre monde
     */
    public Monde(int longueur,int largeur) {
        setMonde(longueur,largeur);
    }
    
    /**
     * Export le monde vers un document XML
     *
     * @param URN le nom du futur fichier xml
     */
    public void export(String URN) {
        try {
            DocumentBuilderFactory fabrique = DocumentBuilderFactory.newInstance();
            fabrique.setValidating(true);
            DocumentBuilder constructeur = fabrique.newDocumentBuilder();
            Document document = constructeur.newDocument();
            
            // Propriétés du DOM
            document.setXmlVersion("1.0");
            document.setXmlStandalone(true);
            
            // Création de l'arborescence du DOM
            Element racine = document.createElement("monde");
            racine.setAttribute("longueur",Integer.toString(getLongueur()));
            racine.setAttribute("largeur",Integer.toString(getLargeur()));
            document.appendChild(racine);
            for (Composant composant:composants) {
                racine.appendChild(composant.toElement(document)) ;
            }
            
            // Création de la source DOM
            Source source = new DOMSource(document);
            
            // Création du fichier de sortie
            File file = new File(URN);
            Result resultat = new StreamResult(URN);
            
            // Configuration du transformer
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM,"http://womby.zapto.org/monde.dtd");
            
            // Transformation
            transformer.transform(source, resultat);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Importer un Monde depuis un fichier xml déjà existant.
     * @param URN URN du fichier XMl contenant déjà un Monde
     */
    public void importe(String URN) {
        setMonde(URN);
    }
    
    /**
     * Permet de creer un nouveau Monde en abandonnant les modifications faites à celui en cours.
     * @param longueur Longueur du nouveau monde
     * @param largeur Largeur du nouveau Monde.
     */
    public void resetMonde(int longueur,int largeur) {
        setMonde(longueur,largeur);
    }
    
    private void setMonde(String URN) {
        try {
            DocumentBuilderFactory fabrique = DocumentBuilderFactory.newInstance();
            fabrique.setValidating(true);
            DocumentBuilder constructeur = fabrique.newDocumentBuilder();
            Document document = constructeur.parse(URN);
            Element monde = document.getDocumentElement();
            largeur = Integer.parseInt(monde.getAttribute("largeur"));
            longueur = Integer.parseInt(monde.getAttribute("longueur"));
            
            NodeList listeCompos = monde.getElementsByTagName("composant");
            composants = new ArrayList<Composant>();
            for (int i=0;i<listeCompos.getLength();i++) {
                composants.add(new Composant( (Element) listeCompos.item(i)) );
            }
            fireUpdate();
        } catch ( Exception e) {
            e.printStackTrace();
        }
        
    }
    private void setMonde(int longueur,int largeur) {
        this.largeur = largeur;
        this.longueur = longueur;
        composants = new ArrayList<Composant>();
        fireUpdate();
    }
    
    /**
     * ajoute un composant au monde, vérifie d'abord que ce composant n'entre pas en conflit avec d'autre.
     * @param composantAdd Le composant à ajouter au Monde
     * @throws java.lang.IllegalArgumentException si un ou plusieurs composants bloquent l'ajout de votre composant.
     */
    public void add(Composant composantAdd) throws IllegalArgumentException {
        for(int i = 0; i< composantAdd.getBlocs().length;i++){
            float xComposantAdd = composantAdd.getX()/4 + composantAdd.getBlocs()[i].getX()/4;
            float zComposantAdd = composantAdd.getZ()/-4 + composantAdd.getBlocs()[i].getZ()/-4;
            if(xComposantAdd >= longueur || xComposantAdd < 0 || zComposantAdd >= largeur || zComposantAdd < 0){
                throw new IllegalArgumentException("La position du composant est hors grille!");
            }
            for(int j = 0; j < composants.size();j++){
                for(int k = 0; k < composants.get(j).getBlocs().length;k++){
                    float xComposants = (composants.get(j).getBlocs()[k].getX()/4)+(composants.get(j).getX()/4);
                    float zComposants = (composants.get(j).getBlocs()[k].getZ()/-4)+(composants.get(j).getZ()/-4);
                    if(xComposantAdd == xComposants && zComposantAdd == zComposants){  
                        throw new IllegalArgumentException("Superposition de composant interdite!");
                    }
                }
            }
        }
        composants.add(composantAdd);
        fireUpdate();
    }
    
    /**
     * Permet de supprimer un composant de la liste des composants constituant le Monde.
     * @param indexComposant index du composant à supprimer dans la liste de Composant.
     */
    public void remove(int indexComposant){
        composants.remove(indexComposant);
        fireUpdate();
    }
    
    /**
     * Permet de supprimer un composant de la liste des composants constituant le Monde.
     * @param composant composant à supprimer de la liste des Composants constituant le monde.
     */
    public void remove(Composant composant){
        composants.remove(composant);
        fireUpdate();
    }
    
    /**
     * Fournit aà l'utilisateur une liste non modifiable des composants
     * @return la liste de composants
     */
    public List<Composant> getComposants() {
        return Collections.unmodifiableList(composants);
    }
    
    /**
     * Permet de modifier la texture de tout les murs d'un composant du Monde
     * @param c Composant dont les textures des murs vont être modifiées.
     * @param texture String contenant le nom de la texture.
     */
    public void setTexMurComposant(Composant c,String texture){
        getComposants().get(getComposants().indexOf(c)).setTexMur(texture);
        fireUpdate();
    }
    
    /**
     * Permet de modifier la texture de tout les sols d'un composant du Monde
     * @param c Composant dont les textures des sols vont être modifiées.
     * @param texture String contenant le nom de la texture.
     */
    public void setTexSolComposant(Composant c,String texture){
        getComposants().get(getComposants().indexOf(c)).setTexSol(texture);
        fireUpdate();
    }
    
    /**
     * Permet de modifier la texture de tout les plafonds d'un composant du Monde.
     * @param c Composant dont les textures des plafonds vont être modifiées.
     * @param texture String contenant le nom de la texture.
     */
    public void setTexPlafondComposant(Composant c,String texture){
        getComposants().get(getComposants().indexOf(c)).setTexPlafond(texture);
        fireUpdate();
    }
    
    /**
     * Recupere l'attribut longueur du Monde.
     * @return l'attribut longueur du Monde.
     */
    public int getLongueur() {
        return longueur;
    }
    
    /**
     * Recupere l'attribut largeur du Monde.
     * @return L'attribut largeur du Monde.
     */
    public int getLargeur() {
        return largeur;
    }
}
