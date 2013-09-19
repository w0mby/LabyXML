#ifndef __COMPOSANT
#define __COMPOSANT

#include "Bloc.h"

/**
* Permet de repr�senter un composant. Un composant est un ensemble de Bloc.
* Le Composant est positionn� dans l'espace 3D de mani�re absolue.
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
             * Constructeur cr�ant un Composant a partir d'un Element d'arbre DOM.
             * @param _elements Element de l'arbre DOM repr�sentant le composant qui sera construit.
             * @param _posElement Position absolue du Composant dans l'espace 3D.
             * @param _modeles Liste de tout les modeles disponible pour creer le Labyrinthe.
             * @param textures Liste de toutes les textures disponible pour creer le Labyrinthe.
             * @see <a href="http://www.grinninglizard.com/tinyxmldocs/classTiXmlElement.html">TiXmlElement</a>
             */
             Composant(TiXmlElement* _elements,Vertex _posElement,MapModele& _modeles,MapTex& textures);
             /**
             * Permet de dessiner en 3D un composant � l'ecran. 
             * On positionne la position de dessin � la position du Composant.
             * On appel la methode de dessin de chaque Bloc contenu dans le Composant.
             * Si la position du bloc � dessiner est trop loin de la position du joueur, sa methode de dessin n'est pas appel�e.
             * @see Bloc
             */
             void draw(Vertex posPerso,Vertex posVue);
             /**
             * Permet de savoir si le joueur est positionn� dans le Composant ou non.
             * @return <code>true</code> Si le joueur est positionn� dans le composant. <code>false</code> sinon.
             */
             bool estIn(Vertex posJoueur);
};

#endif
