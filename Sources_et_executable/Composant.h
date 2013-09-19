#ifndef __COMPOSANT
#define __COMPOSANT

#include "Bloc.h"

/**
* Permet de représenter un composant. Un composant est un ensemble de Bloc.
* Le Composant est positionné dans l'espace 3D de manière absolue.
* @author Damien Dormal, Bavay Jonathan & Amine Rebbouh. 
*/
class Composant {
      private:
             /** Liste de tout les Blocs constituant le Composant.*/
             vector<Bloc> elements;
             /** Position absolue du Composant dans l'espace 3D.*/
             Vertex posElement;
      public :
             /**
             * Constructeur créant un Composant a partir d'un Element d'arbre DOM.
             * @param _elements Element de l'arbre DOM représentant le composant qui sera construit.
             * @param _posElement Position absolue du Composant dans l'espace 3D.
             * @param _modeles Liste de tout les modeles disponible pour creer le Labyrinthe.
             * @param textures Liste de toutes les textures disponible pour creer le Labyrinthe.
             * @see <a href="http://www.grinninglizard.com/tinyxmldocs/classTiXmlElement.html">TiXmlElement</a>
             */
             Composant(TiXmlElement* _elements,Vertex _posElement,MapModele& _modeles,MapTex& textures);
             /**
             * Permet de dessiner en 3D un composant à l'ecran. 
             * On positionne la position de dessin à la position du Composant.
             * On appel la methode de dessin de chaque Bloc contenu dans le Composant.
             * Si la position du bloc à dessiner est trop loin de la position du joueur, sa methode de dessin n'est pas appelée.
             * @see Bloc
             */
             void draw(Vertex posPerso,Vertex posVue);
             /**
             * Permet de savoir si le joueur est positionné dans le Composant ou non.
             * @return <code>true</code> Si le joueur est positionné dans le composant. <code>false</code> sinon.
             */
             bool estIn(Vertex posJoueur);
};

#endif
