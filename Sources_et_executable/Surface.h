#ifndef __RECTANGLE
#define __RECTANGLE

#include <stdlib.h>
#include <gl\gl.h>			// Header File For The OpenGL32 Library
#include <gl\glu.h>			// Header File For The GLu32 Library
#include <tinyxml.h>
#include "Vertex.h"
#include "CoordTex.h"

#include <vector>
#include <map>
#include <string>

using namespace std;

typedef map<string, GLuint , less<string> > MapTex ;
typedef MapTex::value_type PaireTex;

/**
* Permet de stocker un rectangle avec une texture attaché, et cela dans un espace à 3 dimensions.
* permet aussi de le dessiner a l'ecran.
* @author Damien Dormal, Bavay Jonathan & Amine Rebbouh.
*/
class Surface {
      private:
              /** Liste de tout les vertexs constituant la Surface.*/
              vector<Vertex> vertexs;
              /** Liste de toutes les coordonnées permettant d'appliquer la texture à la Surface.*/
              vector<CoordTex> coordTexs;
              /** Texture à appliqué à la Surface.*/
              GLuint texture;
      public :
             /**
             * Constructeur de Surface il permet de construire une surface grace a un element d'un arbre DOM.
             * @param _surface Elements de l'arbre DOM représentant la surface.
             * @param textures Map contenant toutes les textures disponibles pour la creation du Monde.
             * @see <a href="http://www.grinninglizard.com/tinyxmldocs/classTiXmlElement.html">TiXmlElement</a>
             */            
             Surface(TiXmlElement* _surface, MapTex& textures);
             /**
             * Permet de dessiner une surface dans un espace 3D.
             */
             void draw();
             /**
             * Detecte le point minimum en X de la surface.
             * @return le point minimum en X de la surface.
             */
             GLfloat getMinX();
             /**
             * Detecte le point maximum en X de la surface.
             * @return le point maximum en X de la surface.
             */
             GLfloat getMaxX();
             /**
             * Detecte le point minimum en Z de la surface.
             * @return le point minimum en Z de la surface.
             */
             GLfloat getMinZ();
             /**
             * Detecte le point maximum en Z de la surface.
             * @return le point maximum en Z de la surface.
             */
             GLfloat getMaxZ();
};

#endif
