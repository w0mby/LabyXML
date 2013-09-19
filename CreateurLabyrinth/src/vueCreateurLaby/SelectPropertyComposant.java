/*
 * SelectPropertyComposant.java
 *
 * Created on 16 novembre 2006, 17:27
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package vueCreateurLaby;

import createurlabyrinth.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

/**
 * Va permettre de modifier les attributs texture d'un Composant selectionné.
 * @author Bavay Jonathan & Damien Dormal
 */
public class SelectPropertyComposant extends JPanel implements ActionListener{
    private Monde monde;
    private JComboBox comboSol;
    private JComboBox comboMur;
    private JComboBox comboPlafond;
    private JButton valider;
    private Composant composant;
    
    
    /**
     * Instancie un objet de SelectPropertyComposant
     * @param monde le Monde contenant le composant qui va être modifié.
     */
    public SelectPropertyComposant(Monde monde) {
        this.monde = monde;
        File f = new File(".");
        Vector<String> itemsCombo = listerFichiers(f, "jpg");
        comboSol = new JComboBox(itemsCombo);
        comboSol.setPreferredSize(new Dimension(120,20));
        comboMur = new JComboBox(itemsCombo);
        comboMur.setPreferredSize(new Dimension(120,20));
        comboPlafond = new JComboBox(itemsCombo);
        comboPlafond.setPreferredSize(new Dimension(120,20));
        valider = new JButton("valider");
        valider.addActionListener(this);
        valider.setEnabled(false);
        add(comboSol);
        add(comboMur);
        add(comboPlafond);
        add(valider);
        String test ="";
    }
    /**
     * Methode qui va permettre de récuperer les attributs texture du composant selectionné 
     * sur la grille Labyrinthe.
     * @param e evemement ayant provoquer l'appel à la méthode.
     */
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() instanceof VueBloc && ((VueBloc)e.getSource()).getIndexComposant() != -1){
            composant = monde.getComposants().get(((VueBloc)e.getSource()).getIndexComposant());
            valider.setEnabled(true);
        } else if(e.getSource() == valider){
            monde.setTexMurComposant(composant,((String)comboMur.getSelectedItem())+".bmp");
            monde.setTexSolComposant(composant,((String)comboSol.getSelectedItem())+".bmp");
            monde.setTexPlafondComposant(composant,((String)comboPlafond.getSelectedItem())+".bmp");
        }
    }
    /**
     * Methode privée permettant de lister tout les fichiers d'un type passé en parametre.
     * La methode va donc retourner tout les fichiers ayant comme extension celle passée par le parametre <CODE>type</CODE>
     * @param type extension des fichiers qui sont recherchés.
     * @param repertoire repertoire dans lequel on veut rechercher les fichiers d'extension voulue.
     * @return retourne un tableau contenant tout les noms de fichier ayant comme extension le type 
     * passé en paramètre à la méthode.
     */
    private Vector<String> listerFichiers(File repertoire,String type){
        Vector<String> fichiersBmp = new Vector<String>();
        String[] listefichiers = repertoire.list();
        for(int i=0;i<listefichiers.length;i++){
            if(listefichiers[i].endsWith("." + type)){
                fichiersBmp.add(listefichiers[i].substring(0,listefichiers[i].length()-4));
            }
        }
        return fichiersBmp;
    }
}
