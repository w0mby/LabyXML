#include "Bloc.h"

Bloc::Bloc(TiXmlElement* _elements, Vertex _posElement, MapModele& _modeles, MapTex& textures) {
       posElement = _posElement; 
       traversable = true;
       Vertex posComposant;
       TiXmlElement* elem;
       int travers; _elements->QueryIntAttribute("traverse",&travers);
       if(!travers)
          traversable = false;
       elem = _elements->FirstChildElement("rectangle");
       while (elem)
       {
           rectangles.push_back(Surface(elem,textures));
           elem = elem->NextSiblingElement("rectangle"); // iteration
       }
       elem = _elements->FirstChildElement("modele");
       while (elem)
       {
           modeles.push_back(Modele(elem,_modeles, textures));
           elem = elem->NextSiblingElement("modele"); // iteration
       }
       minX = rectangles[0].getMinX();
       for (int i=1;i<rectangles.size();i++) {
           if ( rectangles[i].getMinX() < minX ) minX = rectangles[i].getMinX();     
       }
       minX += posElement.x;
       maxX = rectangles[0].getMaxX();
       for (int i=1;i<rectangles.size();i++) {
           if ( rectangles[i].getMaxX() > maxX ) maxX = rectangles[i].getMaxX();     
       }
       maxX += posElement.x;
       minZ = rectangles[0].getMinZ();
       for (int i=1;i<rectangles.size();i++) {
           if ( rectangles[i].getMinZ() < minZ ) minZ = rectangles[i].getMinZ();     
       }
       minZ += posElement.z;
       maxZ = rectangles[0].getMaxZ();
       for (int i=1;i<rectangles.size();i++) {
           if ( rectangles[i].getMaxZ() > maxZ ) maxZ = rectangles[i].getMaxZ();     
       }
       maxZ += posElement.z;
}


void Bloc::draw() {
     glTranslatef(posElement.x,posElement.y,posElement.z);
     for (int i=0;i<rectangles.size();i++)
         rectangles[i].draw();     
     for (int i=0;i<modeles.size();i++)
         modeles[i].draw();            
}

bool Bloc::estIn(Vertex posJoueur) {
     if(!traversable) return false;
     return ( (posJoueur.x > minX) && (posJoueur.z > minZ) && (posJoueur.x < maxX) && (posJoueur.z < maxZ) ) ; 
}

bool Bloc::estColision(Vertex posJoueur) {
    // return ((posJoueur.x < minX) || (posJoueur.z < minZ) || (posJoueur.x > maxX) || (posJoueur.z > maxZ));    
}
