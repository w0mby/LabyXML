package vueCreateurLaby;

import createurlabyrinth.Config;
import createurlabyrinth.Monde;


public class Main {    
     
    public static void main(String[] args) {
        Monde monde = new Monde(25,25);
        Config config = new Config("configuration.xml");
        Config config2 = new Config("configuration2.xml");
        FenetreCreateur winCreateur = new FenetreCreateur(monde,config);
        FenetreCreateur winCreateur2 = new FenetreCreateur(monde,config2);
        winCreateur.setVisible(true);
        winCreateur2.setVisible(true);
    }
}
