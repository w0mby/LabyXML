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
 * La definition d'un arbre des differents composants disponible � la cr�ation de notre Monde.
 * @author Damien Dormal
 */
public class Config extends Subject{
    
    private List<Composant> composants;
    
    /**
     * Creer un arbre de tout les composants disponible pour creer un labyrinthe.
     * @param URN l'URN de notre document XML
     */
    public Config(String URN) {
        try {
            DocumentBuilderFactory fabrique = DocumentBuilderFactory.newInstance();
            fabrique.setValidating(true);
            DocumentBuilder constructeur = fabrique.newDocumentBuilder();
            Document document = constructeur.parse(URN);
            Element monde = document.getDocumentElement();
            
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
    
    /**
     * Permet d'exporter l'arbre de Composant vers sa repr�sentation xml.
     * @param URN le nom du futur fichier xml
     */
    public void export(String URN) {
        try {
            DocumentBuilderFactory fabrique = DocumentBuilderFactory.newInstance();
            fabrique.setValidating(true);
            DocumentBuilder constructeur = fabrique.newDocumentBuilder();
            Document document = constructeur.newDocument();

        // Propri�t�s du DOM
            document.setXmlVersion("1.0");
            document.setXmlStandalone(true);
            
        // Cr�ation de l'arborescence du DOM
            Element racine = document.createElement("monde");
            document.appendChild(racine);
            for (Composant composant:composants) {
                racine.appendChild(composant.toElement(document)) ;
            }
           
        // Cr�ation de la source DOM     
            Source source = new DOMSource(document);
            
        // Cr�ation du fichier de sortie
            File file = new File(URN);
            Result resultat = new StreamResult(URN);
            
      // Configuration du transformer
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();   
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM,"monde.dtd");
      
      // Transformation
            transformer.transform(source, resultat);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * ajout un composant dans la liste. Apres avoir exporter vers le fichier xml on aura alors un composant
     * de plus pour creer le Monde.
     * @param composant le composant a ajouter
     * @throws java.lang.IllegalArgumentException si un ou plusieur composants bloque l'ajout de votre composant
     */
    public void add(Composant composant) throws IllegalArgumentException {
        composants.add(composant);
        fireUpdate();
    }
    
    /**
     * Permet de supprimer un composant. Ce qui implique que l'on aura un composant en moins pour creer 
     * le Monde.
     * @param indexComposant index du composant dans la liste.
     */
    public void remove(int indexComposant){
        composants.remove(indexComposant);
        fireUpdate();
    }
    /**
     * fournit a l'utilisateur une liste non modifiable des composants
     * @return la list de composants
     */
    public List<Composant> getComposants() {
        return Collections.unmodifiableList(composants);
    }
}
