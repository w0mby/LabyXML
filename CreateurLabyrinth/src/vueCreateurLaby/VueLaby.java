/*
 * VueLaby.java
 *
 * Created on 14 novembre 2006, 17:28
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package vueCreateurLaby;

import createurlabyrinth.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.util.*;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Permet de voir le Monde dans un espace en deux dimensions. Permet aussi de selectionner un composant 
 * afin de pouvoir visionner ses differents attributs et de les modifiés.
 * @author Bavay Jonathan & Damien Dormal
 */
public class VueLaby extends JPanel implements ActionListener,Listener{
    private List<List<VueBloc> > labyrinthe;
    private Monde monde;
    private Config config;
    private VueComposant vueComp;
    private SelectPropertyComposant propComp;
    private ControleurBouton ctrlBouton;
    /**
     * Crée une nouvelle instance de VueLaby.
     * @param monde Monde que VueLaby va afficher.
     * @param propComp objet de type SelectPropertyComposant. Celui ci devant être ecouteur de la grille pour savoir 
     * quel composant est actuellement selectionner par l'utilisateur
     * @param vueComp objet de type VueComposant. Celui ci devant être ecouteur de la grille pour savoir 
     * quel composant est actuellement selectionner par l'utilisateur
     * @param ctrlBouton objet de type <code>ControleurBouton</code>. Les objets de type VueBloc doivent être ecouteur 
     * de celui-ci pour savoir quel est le composant selectionner actuellement par l'utilisateur 
     * dans la liste des composants.
     * @param config Liste de tout les composants disponible pour creer le monde.
     */
    
    public VueLaby(Monde monde,SelectPropertyComposant propComp,VueComposant vueComp,ControleurBouton ctrlBouton,Config config) {
        this.monde = monde;
        this.config = config;
        this.vueComp = vueComp;
        this.propComp = propComp;
        this.ctrlBouton = ctrlBouton;
        initAll();
        monde.addListener(this);
    }
    
    private void initAll() {
        setLayout(new GridLayout(monde.getLongueur(),monde.getLargeur()));
        labyrinthe = new ArrayList<List<VueBloc> >();
        for(int i = 0; i < monde.getLongueur(); i++)
            labyrinthe.add(new ArrayList<VueBloc>() );
        
        for(int i = 0; i < monde.getLongueur(); i++){
            for(int j = 0;j< monde.getLargeur(); j++){
                labyrinthe.get(i).add(new VueBloc());
                add(labyrinthe.get(i).get(j));
                labyrinthe.get(i).get(j).addActionListener(propComp);
                labyrinthe.get(i).get(j).addActionListener(vueComp);
                labyrinthe.get(i).get(j).addActionListener(this);
                for(int k = 0;k<ctrlBouton.getBtnComposants().size();k++){
                    ctrlBouton.getBtnComposants().get(k).addActionListener(labyrinthe.get(i).get(j));
                }
            }
        }
    }
    
    /**
     * Va effectuer des actions mettant a jour le monde.
     * @param e evenement ayant déclencher l'appel à la méthode.
     */
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() instanceof VueBloc){
            if(((VueBloc)e.getSource()).getIndexComposant() != -1) {
                for(int i = 0; i < labyrinthe.size(); i++){
                    for(int j = 0;j< labyrinthe.get(0).size(); j++){
                        if(labyrinthe.get(i).get(j).getBackground() == Color.RED)
                            labyrinthe.get(i).get(j).setBackground(Color.BLACK);
                        if(labyrinthe.get(i).get(j).getIndexComposant() == ((VueBloc)e.getSource()).getIndexComposant()){
                            labyrinthe.get(i).get(j).setBackground(Color.RED);
                        }
                    }
                }
            } else if(((VueBloc)e.getSource()).getIndexComposantCourant() != -1){
                //recupereer x et z
                int x = 0;
                int z = 0;
                for(int i = 0; i < labyrinthe.size(); i++){
                    for(int j = 0;j< labyrinthe.get(0).size(); j++){
                        if(labyrinthe.get(i).get(j) == ((VueBloc)e.getSource())){
                            z = j;
                            x = i;
                        }
                    }
                }
                Composant composantAdd = null;
                //Ici le clone() est obligatoire sans quoi ce serait toujours le meme objet qui serait juste modifier dans le modele.
                try {
                    composantAdd = ((Composant) config.getComposants().get(((VueBloc) e.getSource()).getIndexComposantCourant()).clone());
                } catch (CloneNotSupportedException ex) {
                    ex.printStackTrace();
                }
                composantAdd.setX(x*4);
                composantAdd.setZ((z*-4));
                composantAdd.setY(0);
                try{
                    monde.add(composantAdd);
                }
                catch(IllegalArgumentException ex){
                    JOptionPane.showMessageDialog(this,ex.getMessage());
                }
            }
        } else{
            int indexComposant = -1;
            for(int i = 0; i < labyrinthe.size(); i++){
                for(int j = 0;j< labyrinthe.get(0).size(); j++){
                    if(labyrinthe.get(i).get(j).getBackground() == Color.RED){
                        indexComposant = labyrinthe.get(i).get(j).getIndexComposant();
                        i = labyrinthe.size();
                        j = labyrinthe.get(0).size();
                    }
                }
            }
            if (indexComposant >= 0) {
                monde.remove(indexComposant);
            }
        }
    }
    
    /**
     * Methode permettant de mettre la vue Vuelaby à jour en fonction des modifications apportées au monde.
     * @param o Subjet d'observation.
     */
    public void update(Subject o) {
        if (((Monde)o).getLongueur() !=  labyrinthe.size() || ((Monde)o).getLargeur() != labyrinthe.get(0).size() ) { // la taille du controlleur a changer
            removeAll();  // on retire tout les composant
            for (int i=0;i<labyrinthe.size();i++) {   // on retire tout les ecouteurs
                for (int j=0;j<labyrinthe.get(0).size();j++) {
                    //remove(labyrinthe.get(i).get(j));
                    labyrinthe.get(i).get(j).removeActionListener(propComp);
                    labyrinthe.get(i).get(j).removeActionListener(vueComp);
                    labyrinthe.get(i).get(j).removeActionListener(this);
                    for(int k = 0;k<ctrlBouton.getBtnComposants().size();k++){
                        ctrlBouton.getBtnComposants().get(k).removeActionListener(labyrinthe.get(i).get(j));
                    }
                }
            }         
            initAll();
            revalidate();
        }
        for(int i = 0; i < ((Monde)o).getLongueur(); i++){
            for(int j = 0;j< ((Monde)o).getLargeur() ; j++){
                labyrinthe.get(i).get(j).setBackground(Color.WHITE);
                labyrinthe.get(i).get(j).setIndexComposant(-1);
            }
        }
        for(int i = 0;i < ((Monde)o).getComposants().size(); i++){
            for(int j = 0; j < ((Monde)o).getComposants().get(i).getBlocs().length; j++){
                int xBloc = (int)(((Monde)o).getComposants().get(i).getBlocs()[j].getX());
                int xCompo = (int)(((Monde)o).getComposants().get(i).getX());
                int zBloc = (int)(((Monde)o).getComposants().get(i).getBlocs()[j].getZ());
                int zCompo = (int)(((Monde)o).getComposants().get(i).getZ());
                int x = (int)(xCompo + xBloc)/4;
                int z = (int)(( zCompo + zBloc))/-4;
                labyrinthe.get(x).get(z).setBackground(Color.BLACK);
                labyrinthe.get(x).get(z).setIndexComposant(i);
                
            }
        }
    }
}
