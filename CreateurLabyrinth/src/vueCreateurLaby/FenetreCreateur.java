/*
 * FenetreCreateur.java
 *
 * Created on 14 novembre 2006, 18:23
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package vueCreateurLaby;

import createurlabyrinth.*;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.*;

/**
 * Fenetre principale de l'application
 * @author Bavay Jonathan & Damien Dormal
 */
public class FenetreCreateur extends JFrame{
    private VueLaby vueLaby;
    private ControleurComposant ctrlComposant;
    private Monde monde;
    private Config config;
    private ControleurBouton ctrlBouton;
    private String nomFichierCourant;
    
    
    /**
     * Crée une fenetre principale de l'application contenant tout les composants utiles.
     * @param monde instanciation de Monde utile pour ajouter des Composants au monde, supprimer des Composants 
     * ou les modifier.
     * @param config Instanciation de Config permettant d'avoir une liste de tout les Composants disponible 
     * pour creer un Monde.
     */
    public FenetreCreateur(Monde monde, Config config) {
        this.monde = monde;
        this.config = config;
        nomFichierCourant = new String("nouveau.xml");
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.CENTER,0,20));
        setTitle("Labyrinthe");
        setResizable(false);
        ctrlBouton = new ControleurBouton(config);
        ctrlComposant = new ControleurComposant(monde);
        
        
        vueLaby = new VueLaby(monde,ctrlComposant.getPropComposant(),ctrlComposant.getTexComposant(),ctrlBouton,config);
        JScrollPane jScrollPane = new JScrollPane(vueLaby);
        jScrollPane.setPreferredSize(new Dimension(378,378));
        
        JMenuBar jMenuBar = new JMenuBar();
        JMenu fichier = new JMenu();
        fichier.setFont(new Font("Tahoma",Font.PLAIN,12));
        JMenuItem enregistrer = new JMenuItem();
        enregistrer.setFont(new Font("Tahoma",Font.PLAIN,12));
        fichier.setText("Fichier");
        enregistrer.setText("Enregistrer");
        enregistrer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,InputEvent.CTRL_MASK));
        enregistrer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                enregistrerActionPerformed();
            }
        });
        JMenuItem enregistrerSous = new JMenuItem();
        enregistrerSous.setFont(new Font("Tahoma",Font.PLAIN,12));
        enregistrerSous.setText("Enregistrer sous");
        enregistrerSous.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                enregistrerSousActionPerformed();
            }
        });
        JMenuItem supprimer = new JMenuItem();
        supprimer.setFont(new Font("Tahoma",Font.PLAIN,12));
        supprimer.setText("Supprimer");
        supprimer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,InputEvent.CTRL_MASK));
        supprimer.addActionListener(vueLaby);
        
        JMenuItem ouvrir = new JMenuItem();
        ouvrir.setFont(new Font("Tahoma",Font.PLAIN,12));
        ouvrir.setText("Ouvrir");
        ouvrir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                ouvrirActionPerformed();
            }
        });
        
        JMenuItem nouveau = new JMenuItem();
        nouveau.setFont(new Font("Tahoma",Font.PLAIN,12));
        nouveau.setText("Nouveau");
        nouveau.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                nouveauActionPerformed();
            }
        });
        
        JMenuItem fermer = new JMenuItem();
        fermer.setFont(new Font("Tahoma",Font.PLAIN,12));
        fermer.setText("Fermer");
        fermer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                fermerActionPerformed();
            }
        });
        
        fichier.add(nouveau);
        fichier.add(ouvrir);
        fichier.add(enregistrer);
        fichier.add(enregistrerSous);
        fichier.add(supprimer);
        fichier.add(fermer);
        jMenuBar.add(fichier);
        setJMenuBar(jMenuBar);
        add(jScrollPane);
        add(ctrlComposant);
        add(ctrlBouton);
        pack();
    }
    private void enregistrerActionPerformed() {
        monde.export(nomFichierCourant);
        JOptionPane.showMessageDialog(this,"Ecriture du fichier terminée");
    }
    private void enregistrerSousActionPerformed() {
        JFileChooser chooser = new JFileChooser();
        int returnVal = chooser.showSaveDialog(this);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            nomFichierCourant = chooser.getSelectedFile().getPath();   
            monde.export(nomFichierCourant);
        }      
    }
    private void ouvrirActionPerformed() {
        JFileChooser chooser = new JFileChooser();
        int returnVal = chooser.showOpenDialog(this);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            nomFichierCourant = chooser.getSelectedFile().getPath();   
            monde.importe(nomFichierCourant);
        }      
    }
    private void nouveauActionPerformed() {
        DialogConfigMonde dialog = new DialogConfigMonde(this,true);
        dialog.setVisible(true);
        if ( dialog.isApprouve() ) {
            monde.resetMonde(dialog.getLongueur(),dialog.getLargeur());
        }
    }
    
    private void fermerActionPerformed() {
        System.exit(0);
    }
   
    
    
    
}
