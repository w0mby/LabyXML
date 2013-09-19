/*
 * VueComposant.java
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
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Permet de voir les attributs texture ainsi que l'attribut type du composant selectionner sur la <code>VueLaby</code>.
 * @author Bavay jonathan & Damien Dormal
 */
public class VueComposant extends JPanel implements Listener,ActionListener{
    private Composant composant;
    private VueTexture solTex;
    private VueTexture murTex;
    private VueTexture plafondTex;
    private JLabel solNom;
    private JLabel murNom;
    private JLabel plafondNom;
    private JLabel txtTypeComposant;
    private VueTexture typeComposant;
    private Monde monde;
    private JPanel nomEtImageSol;
    private JPanel nomEtImageMur;
    private JPanel nomEtImagePlafond;
    private JPanel PnlTypeComposant;
    /**
     * Creation de la VueComposant
     * @param monde Monde complet dont la vue a besoin pour pouvoir s'y attacher afin de pouvoir evoluer 
     * en fonction des evolution de celui-ci.
     */
    public VueComposant(Monde monde) {
        setBorder(BorderFactory.createLineBorder(Color.ORANGE));
        this.monde = monde;
        solTex = new VueTexture();
        murTex = new VueTexture();
        plafondTex = new VueTexture();
        typeComposant = new VueTexture();
        txtTypeComposant = new JLabel();
        txtTypeComposant.setPreferredSize(new Dimension(20,15));
        solNom = new JLabel();
        solNom.setPreferredSize(new Dimension(20,15));
        murNom = new JLabel();
        murNom.setPreferredSize(new Dimension(20,15));
        plafondNom = new JLabel();
        plafondNom.setPreferredSize(new Dimension(20,15));
        nomEtImageSol = new JPanel();
        nomEtImageSol.setLayout(new BoxLayout(nomEtImageSol,BoxLayout.PAGE_AXIS));
        nomEtImageSol.add(new JLabel("Sol"));
        nomEtImageSol.add(solTex);
        nomEtImageSol.add(solNom);
        nomEtImageMur = new JPanel();
        nomEtImageMur.setLayout(new BoxLayout(nomEtImageMur,BoxLayout.PAGE_AXIS));
        nomEtImageMur.add(new JLabel("Mur"));
        nomEtImageMur.add(murTex);
        nomEtImageMur.add(murNom);
        nomEtImagePlafond = new JPanel();
        nomEtImagePlafond.setLayout(new BoxLayout(nomEtImagePlafond,BoxLayout.PAGE_AXIS));
        nomEtImagePlafond.add(new JLabel("Plafond"));
        nomEtImagePlafond.add(plafondTex);
        nomEtImagePlafond.add(plafondNom);
        PnlTypeComposant = new JPanel();
        PnlTypeComposant.setLayout(new BoxLayout(PnlTypeComposant,BoxLayout.PAGE_AXIS));
        PnlTypeComposant.add(new JLabel("Type"));
        PnlTypeComposant.add(typeComposant);
        PnlTypeComposant.add(txtTypeComposant);
        add(nomEtImageSol);
        add(nomEtImageMur);
        add(nomEtImagePlafond);
        add(PnlTypeComposant);
        monde.addListener(this);
    }
    
    /**
     * Methode appelée par le sujet d'observation lorsque celui-ci est modifié.
     * @param o est le sujet d'observation
     */
    public void update(Subject o){
        if(composant != null){
            solTex.setImage(composant.getTexSol().substring(0,composant.getTexSol().indexOf(".")) + ".jpg");
            murTex.setImage(composant.getTexMur().substring(0,composant.getTexMur().indexOf(".")) + ".jpg");
            plafondTex.setImage(composant.getTexPlafond().substring(0,composant.getTexPlafond().indexOf(".")) + ".jpg");
            solNom.setText(composant.getTexSol().substring(0,composant.getTexSol().indexOf(".")));
            murNom.setText(composant.getTexMur().substring(0,composant.getTexMur().indexOf(".")));
            plafondNom.setText(composant.getTexPlafond().substring(0,composant.getTexPlafond().indexOf(".")));
            typeComposant.setImage(composant.getType() + ".GIF");
            txtTypeComposant.setText(composant.getType());
            revalidate();
        }
    }
    /**
     * methode permettant de recupérer les attributs textures et type du Composant passé en paramètre.
     * @param composant Composant dont les attributs textures sont récuperés.
     */
    public void setComposant(Composant composant){
        this.composant = composant;
        solTex.setImage(composant.getTexSol().substring(0,composant.getTexSol().indexOf(".")) + ".jpg");
        murTex.setImage(composant.getTexMur().substring(0,composant.getTexMur().indexOf(".")) + ".jpg");
        plafondTex.setImage(composant.getTexPlafond().substring(0,composant.getTexPlafond().indexOf(".")) + ".jpg");
        solNom.setText(composant.getTexSol().substring(0,composant.getTexSol().indexOf(".")));
        murNom.setText(composant.getTexMur().substring(0,composant.getTexMur().indexOf(".")));
        plafondNom.setText(composant.getTexPlafond().substring(0,composant.getTexPlafond().indexOf(".")));
        typeComposant.setImage(composant.getType() + ".GIF");
        txtTypeComposant.setText(composant.getType());
        revalidate();
    }
    
    /**
     * Va permettre de recuperer les attributs d'un composant lorsque l'utilisateur selectionne celui-ci 
     * dans la <code>VueLaby</code>
     * @param e evement ayant déclencher l'appelle à la méthode.
     */
    public void actionPerformed(ActionEvent e) {
        int i = ((VueBloc)e.getSource()).getIndexComposant();
        if(i != -1)
            setComposant(monde.getComposants().get(i));
    }
}
