#ifndef __MODELE
#define __MODELE

#include <map>
#include <string>
#include "Md2Object.h"
#include "Vertex.h"
#include <tinyxml.h>

using namespace std;

typedef map<string, GLuint , less<string> > MapTex;
typedef MapTex::value_type PaireTex;
typedef map<string, Md2Object* , less<string> > MapModele;
typedef MapModele::value_type PaireModel;

/**
* Représente un Modele complexe positionner dans le Bloc qui le contient et ayant une taille propre.
* @author Damien Dormal, Bavay Jonathan & Amine Rebbouh
* @see Bloc
*/
class Modele {
      private :
              /** Position du Modele relative a la position du Bloc qui le contient.*/
              Vertex posModele;
              /** Modele qui sera dessiné*/
              Md2Object* md2Object;
              /**texture associé au Modele*/
              GLuint texture;
              /** Echelle du Modele*/
              float scale;
              /** Rotation a faire effectué au Modele*/
              float rotation;
              bool lumiere;
      public :
             /**
             * Construit un Modele a partir d'un element d'arbre DOM.
             * @param _elements Représentation XML du Modele.
             * @param _modeles Map contenant tout les modeles disponibles pour creer le labyrinthe.
             * @param textures Map contenant toutes les textures disponibles pour creer le labyrinthe.
             * @see <a href="http://www.grinninglizard.com/tinyxmldocs/classTiXmlElement.html">TiXmlElement</a>
             */
              Modele(TiXmlElement* _elements, MapModele& _modeles, MapTex& _textures);
              /**
              * Dessine un Modele
              */
              void draw();
};

#endif
