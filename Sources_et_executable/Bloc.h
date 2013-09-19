#ifndef __BLOC
#define __BLOC

#include "Surface.h"
#include "Modele.h"
/**
* Permet de représenter un bloc. Composé de rectangles.
* Ce meme bloc peut contenir des modeles complexes. Il est situé dans l'espace par rapport a la position du Composant
* auquel il appartient (position relative par rapport au composant).
* @author Damien Dormal, Bavay Jonathan & Amine Rebbouh. 
*/
class Bloc {
      private :
              /** Liste de toute les Surfaces composant le Bloc*/
              vector<Surface>  rectangles;
              /** Liste de tout les Modeles contenus dans le Bloc*/
              vector<Modele>  modeles;
              /** Position du Bloc relative a la position du Composant le contenant.*/
              Vertex posElement;
              /** Point minimum en x du Bloc*/
              GLfloat minX;
              /** Point maximum en x du Bloc*/
              GLfloat maxX;
              /** Point minimum en z du Bloc*/
              GLfloat minZ;
              /** Point maximum en z du Bloc*/
              GLfloat maxZ; 
              /** Definit si le bloc est traversable ou non.*/
              bool traversable;
      public :      
             /**
             * Constructeur de Bloc il permet de construire un bloc grace a un element d'un arbre DOM.
             * Il va aussi definir les points maximum et minimum du Bloc permettant la detection de bord.
             * @param _elements Elements de l'arbre DOM représentant le Bloc.
             * @param _posElement position relative du Bloc par rapport au Composant le contenant et cela dans un espace 3D.
             * @param _modeles Map contenant tout les modeles disponibles pour la creation du Monde.
             * @param textures Map contenant toutes les textures disponibles pour la creation du Monde.
             * @see Composant
             * @see <a href="http://www.grinninglizard.com/tinyxmldocs/classTiXmlElement.html">TiXmlElement</a>
             */
              Bloc(TiXmlElement* _elements,Vertex _posElement, MapModele& _modeles, MapTex& textures);
              /**
              * Methode permettant de dessiner un bloc. Elle se borne à faire une translation jusqu'a la position relative du bloc
              * et à appeler les methode de dessins des differentes Surfaces et Modeles composant le Bloc.
              * @see Surface
              * @see Modele
              */
              void draw(); 
              /**
              * Permet de detecter si le joueur est actuellement positionné dans le Bloc ou non.
              * @param posJoueur position actuelle du joueur qui sera comparée avec les minimum et maximum du Bloc.
              * @return <code>true</code> Si le joueur est position dans le Bloc. <code>false</code> sinon.
              */
              bool estIn(Vertex posJoueur);
              /**
              * Permet de definir si le joueur entre en colision avec le bloc ou non.
              * @deprecated Fusionnée avec la methode <code>estIn()</code>
              * @param posJoueur position actuelle du joueur qui sera comparée avec les minimum et maximum du Bloc.
              * @return <code>true</code> si le joueur rentre en colision avec le bloc. <code>false</code> Sinon.
              */
              bool estColision(Vertex posJoueur);
              /**
              * Retourne la position de l'element. Cette position est relative à celle du Composant contenant le Bloc.
              * @return la position de l'element relative au Composant le contenant.
              */
              Vertex getPos(){return posElement;}
              /**
              * Retourne le point Minimum en X du Bloc.
              * @return le point minimum en X du Bloc.
              */
              GLfloat getMinX(){return minX;}
              /**
              * Retourne le point Maximum en X du Bloc.
              * @return le point Maximum en X du Bloc.
              */
              GLfloat getMaxX(){return maxX;}
              /**
              * Retourne le point Minimum en Z du Bloc.
              * @return le point minimum en Z du Bloc.
              */
              GLfloat getMinZ(){return minZ;}
              /**
              * Retourne le point Maximum en Z du Bloc.
              * @return le point Maximum en Z du Bloc.
              */
              GLfloat getMaxZ(){return maxZ;}
};

#endif
