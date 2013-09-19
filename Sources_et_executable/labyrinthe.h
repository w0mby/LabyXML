#ifndef __LABYRINTHE
#define __LABYRINTHE

#include <stdlib.h>
#include <gl\gl.h>			// Header File For The OpenGL32 Library
#include <gl\glu.h>			// Header File For The GLu32 Library
#include <tinyxml.h>

#include "Bloc.h"
#include "Composant.h"
#include <vector>
#include <map>
#include <string>

/**
* Représente un monde constitué de Composant.
* @see Composant
* @author Damien Dormal, Bavay Jonathan & Amine Rebbouh
*/
class Labyrinthe {
      private:
              /** Représente le composant étant la fin du labyrinthe.*/
              Composant* finLabyrinthe;
              /** Représente le composant étant le début du Labyrinthe*/
              Composant* debutLabyrinthe;
              /** Tableau de Composant constituant l'entierté du Monde.*/
              vector<Composant*> elemComplexe;
             
      public :
             /**
             * Construit le Monde à partir d'un fichier xml.
             * @param nomFichier Fichier xml constituant le monde.
             * @param _modeles Map contenant tout les modeles disponibles pour la creation du Monde.
             * @param textures Map contenant toutes les textures disponibles pour la creation du Monde.
             * @see <a href="http://www.grinninglizard.com/tinyxmldocs/index.html">TiXml</a>
             */
             Labyrinthe(char * nomFichier, MapModele & _modeles,MapTex & textures);
             //~Labyrinthe();  //deleter tout les modele contenu dans la map.
             /**
             * Dessine le labyrinthe. Ne fait qu'appeler la methode draw() de tout les composants constituant le Labyrinthe.
             * @param posPerso Position de l'utilisateur.
             * @param posVue Position de la vue de l'utilisateur.
             * @see Composant
             */
             void draw(Vertex posPerso,Vertex posVue);
             /**
             * Determine si l'utilisateur est toujours positionner dans le labyrinthe.
             * @param posJoueur position du joueur dans le monde.
             * @return <code>true</code> Si l'utilisateur est positionner dans le monde et <code>false</code> sinon.
             */
             bool estIn(Vertex posJoueur);
             /**
             * Determine si l'utilisateur est sur le composant de fin.
             * @return <code>true</code> Si l'utilisateur est positionner dans le Composant de fin 
             * et <code>false</code> sinon.
             */
             bool estFini(Vertex posJoueur);
};

#endif
