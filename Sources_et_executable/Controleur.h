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
* Classe fournissant toutes les methodes permettant d'int�ragir et/ou d'afficher le Labyrinthe.
* L'interaction avec le labyrinthe est regie par les methode �venementielle fournie par la librairie glut.
* @see <a href="http://www.opengl.org/documentation/specs/glut/spec3/spec3.html">Glut</a>
* @author Damien Dormal, Bavay Jonathan & Amine Rebbouh. 
*/
class Controleur {
      private :
              /** Monde dans lequel l'utilisateur va int�ragir.*/
              Labyrinthe * laby;
              /** position de l'utilisateur dans le monde.*/
              Vertex posJoueur;
              /** position de la vue dans le monde.*/
              Vertex posVue;
              /** Angle de la vue.*/
              float degVue;
              /** Tableau repr�sentant les touches du clavier. si une touche est enfonc�e,
              * la cellule du tableau correspondant a son code ascii est mise � true.
              * Lorsque la touche est relach�e, la cellule correspondante est remise � false.
              */
              bool keys[256];
              /** Hauteur de l'�cran en pixel*/
              int hauteurEcran;
              /** Largeur de l'�cran en pixel*/
              int largeurEcran;
              /** Limitation de vitesse du joueur*/
              int freinJoueur;

      public :
             /**
             * Va permettre de creer le monde dans lequel l'utilisateur va �tre positionn�.
             * @param monde Fichier xml repr�sentant le monde � afficher.
             * @param _hauteurEcran d�finit la hauteur de l'�cran en pixel.
             * @param _largeurEcran d�finit la largeur de l'�cran en pixel.
             */
              Controleur(char * monde,int _hauteurEcran,int _largeurEcran);
              /**
              * Destructeur de Controleur.
              */        
              ~Controleur() { delete laby ;}
              /**
              * Appel�e lorsqu'un redimensionnement de la fen�tre de jeu est effectu�.
              * Cela permettra de redimensionner la scene contenue dans la fen�tre.
              * @param width nouvelle largeur de la fen�tre.
              * @param height nouvelle hauteur de la fen�tre.
              */ 
              void resize(int width, int height);
              /**
              * Appel� lorsque la scene visible doit �tre redessin�e.
              */
              void display(void);
              /**
              * Appel�e lorsqu'une touche du clavier est relach�e.
              * @param key code Ascii de la touche qui est relach�e.
              * @param x position en x du curseur � l'�cran.
              * @param y position en y du curseur � l'�cran.
              */
              void keyboardUp(unsigned char key, int x, int y);
              /**
              * Appel�e lorsqu'une touche du clavier est enfonc�e.
              * @param key code Ascii de la touche qui est enfonc�e.
              * @param x position en x du curseur � l'�cran.
              * @param y position en y du curseur � l'�cran.
              */
              void keyboard(unsigned char key, int x, int y);
              /**
              * Appel�e lorsque le curseur de la souris change de position � l'�cran.
              * @param x nouvelle position en X du curseur de la souris.
              * @param y nouvelle position en Y du curseur de la souris.
              */
              void mouseMove(int x, int y);
              /**
              * Appel�e lorsqu'il n'y a rien � faire.
              * C'est ici que l'on va demander � redessiner la sc�ne.
              */
              void idle(void);
              /**
              * Lorsque le temps pass� en param�tre est pass�, la methode est appel�e.
              * @param value temps en milliseconde.
              */
              void timer(int value);
      private :
              /** 
              * Est appell�e tout les x temps. Va modifier les parametres de vue et de position du joueur dans le monde
              * en fonction des touches appuy�es.
              * @see calculerPosVue
              */
              void postChange();
              /** Recalcule la position de la vue*/
              void calculerPosVue(); 
              /**
              * Permet de charger toutes les textures pr�sente dans le dossier "Data/".
              * Ces textures doivent �tre au format Bitmap 24bit et avoir comme extension ".bmp"
              * La taille de ces images doit <b>IMPERATIVEMENT</b> �tre une puissance de 2 (128*128, 256*256,...)
              * @return une Map contenant toutes les textures qui ont �t� charg�es.
              */
              MapTex LoadGLTextures();
              /**
              * Permet de charger tout les modeles contenu dans le dossier "Model/".
              * Ces mod�les doivent �tre au format md2 et avoir comme extension ".md2".
              * @return une Map contenant tout les modeles qui ont �t� charg�.
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
