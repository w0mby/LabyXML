package createurlabyrinth;
import java.awt.Component;
import java.io.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;
import org.xml.sax.*;

public class Main {    
     
    public static void main(String[] args) {
            Monde m = new Monde("test.xml");
            m.getComposants().get(0).setTexMur("mur.bmp");
            m.getComposants().get(0).setTexSol("sol.bmp");
            m.getComposants().get(0).setTexPlafond("plafond.bmp");
            m.export("good.xml");
    }
    
    public static void transformerXml(Document document, String fichier) {
   try {
      // Création de la source DOM
      Source source = new DOMSource(document);
      // Création du fichier de sortie
      File file = new File(fichier);
      Result resultat = new StreamResult(fichier);
      // Configuration du transformer
      TransformerFactory fabrique = TransformerFactory.newInstance();
      Transformer transformer = fabrique.newTransformer();
      
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");
      transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
      transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM,"monde.dtd");
      
    // Transformation
      transformer.transform(source, resultat);
   } catch(Exception e){
       e.printStackTrace();
   }
}
    
}
