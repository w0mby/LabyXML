#ifndef __CONTROLEUR
#define __CONTROLEUR

#include "Labyrinthe.h"
#include "GL/glut.h"
#include <stdlib.h>

#define PI 3.1415926535897932384
#define rapportRadian PI / 180

typedef struct {
    unsigned long sizeX;
    unsigned long sizeY;
    char *data;
} Image;

/**
* Classe fournissant toutes les methodes permettant d'intéragir et/ou d'afficher le Labyrinthe.
* L'interaction avec le labyrinthe est regie par les methode évenementielle fournie par la librairie glut.
* @see <a href="http://www.opengl.org/documentation/specs/glut/spec3/spec3.html">Glut</a>
* @author Damien Dormal, Bavay Jonathan & Amine Rebbouh. 
*/
class Controleur {
      private :
              /** Monde dans lequel l'utilisateur va intéragir.*/
              Labyrinthe * laby;
              /** position de l'utilisateur dans le monde.*/
              Vertex posJoueur;
              /** position de la vue dans le monde.*/
              Vertex posVue;
              /** Angle de la vue.*/
              float degVue;
              /** Tableau représentant les touches du clavier. si une touche est enfoncée,
              * la cellule du tableau correspondant a son code ascii est mise à true.
              * Lorsque la touche est relachée, la cellule correspondante est remise à false.
              */
              bool keys[256];
              /** Hauteur de l'écran en pixel*/
              int hauteurEcran;
              /** Largeur de l'écran en pixel*/
              int largeurEcran;
              /** Limitation de vitesse du joueur*/
              int freinJoueur;

      public :
             /**
             * Va permettre de creer le monde dans lequel l'utilisateur va être positionné.
             * @param monde Fichier xml représentant le monde à afficher.
             * @param _hauteurEcran définit la hauteur de l'écran en pixel.
             * @param _largeurEcran définit la largeur de l'écran en pixel.
             */
              Controleur(char * monde,int _hauteurEcran,int _largeurEcran);
              /**
              * Destructeur de Controleur.
              */        
              ~Controleur() { delete laby ;}
              /**
              * Appelée lorsqu'un redimensionnement de la fenêtre de jeu est effectué.
              * Cela permettra de redimensionner la scene contenue dans la fenêtre.
              * @param width nouvelle largeur de la fenêtre.
              * @param height nouvelle hauteur de la fenêtre.
              */ 
              void resize(int width, int height);
              /**
              * Appelé lorsque la scene visible doit être redessinée.
              */
              void display(void);
              /**
              * Appelée lorsqu'une touche du clavier est relachée.
              * @param key code Ascii de la touche qui est relachée.
              * @param x position en x du curseur à l'écran.
              * @param y position en y du curseur à l'écran.
              */
              void keyboardUp(unsigned char key, int x, int y);
              /**
              * Appelée lorsqu'une touche du clavier est enfoncée.
              * @param key code Ascii de la touche qui est enfoncée.
              * @param x position en x du curseur à l'écran.
              * @param y position en y du curseur à l'écran.
              */
              void keyboard(unsigned char key, int x, int y);
              /**
              * Appelée lorsque le curseur de la souris change de position à l'écran.
              * @param x nouvelle position en X du curseur de la souris.
              * @param y nouvelle position en Y du curseur de la souris.
              */
              void mouseMove(int x, int y);
              /**
              * Appelée lorsqu'il n'y a rien à faire.
              * C'est ici que l'on va demander à redessiner la scène.
              */
              void idle(void);
              /**
              * Lorsque le temps passé en paramètre est passé, la methode est appelée.
              * @param value temps en milliseconde.
              */
              void timer(int value);
      private :
              /** 
              * Est appellée tout les x temps. Va modifier les parametres de vue et de position du joueur dans le monde
              * en fonction des touches appuyées.
              * @see calculerPosVue
              */
              void postChange();
              /** Recalcule la position de la vue*/
              void calculerPosVue(); 
              /**
              * Permet de charger toutes les textures présente dans le dossier "Data/".
              * Ces textures doivent être au format Bitmap 24bit et avoir comme extension ".bmp"
              * La taille de ces images doit <b>IMPERATIVEMENT</b> être une puissance de 2 (128*128, 256*256,...)
              * @return une Map contenant toutes les textures qui ont été chargées.
              */
              MapTex LoadGLTextures();
              /**
              * Permet de charger tout les modeles contenu dans le dossier "Model/".
              * Ces modèles doivent être au format md2 et avoir comme extension ".md2".
              * @return une Map contenant tout les modeles qui ont été chargé.
              */
              MapModele LoadGLModels();
              /**
              * Permet de charger un fichier Bitmap 24bit.
              * @return 1
              */
              int ImageLoad(char *filename, Image *image);
              /** Permet de recuperer et de se deplacer d'un short dans un fichier.*/
              unsigned int getshort(FILE *fp);
              /** Permet de recuperer et de se deplacer d'un int dans un fichier.*/
              unsigned int getint(FILE *fp);
};
#endif
