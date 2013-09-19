#include "Composant.h"

Composant::Composant(TiXmlElement* _elements,Vertex _posElement, MapModele& _modeles, MapTex& textures){
     posElement = _posElement;
     Vertex posRelComposant;
     TiXmlElement* elem;
     elem = _elements->FirstChildElement("bloc");
      while (elem)
      {
           elem->QueryDoubleAttribute("x",&posRelComposant.x);
           elem->QueryDoubleAttribute("y",&posRelComposant.y);
           elem->QueryDoubleAttribute("z",&posRelComposant.z);
           elements.push_back(Bloc(elem,posRelComposant,_modeles,textures));                
           elem = elem->NextSiblingElement("bloc"); // iteration
       }
}

void Composant::draw(Vertex posPerso,Vertex posVue) {					   	
     for (int i=0;i<elements.size();i++) {
         glLoadIdentity();	
         gluLookAt (posPerso.x,posPerso.y,posPerso.z,posVue.x,posVue.y,posVue.z,0,1,0);	
         glTranslatef(posElement.x,posElement.y,posElement.z);
         if(!((posElement.x + elements[i].getPos().x) > (posPerso.x + 20) ||
            (posElement.x + elements[i].getPos().x) < (posPerso.x - 20) ||
            (posElement.z + elements[i].getPos().z) > (posPerso.z + 20) ||
            (posElement.z + elements[i].getPos().z) < (posPerso.z - 20) ) ){
              elements[i].draw();    
         }
     }             
}

bool Composant::estIn(Vertex posJoueur){
     posJoueur.x -= posElement.x;
     posJoueur.z -= posElement.z;
     for (int i=0;i<elements.size();i++){
         if(elements[i].estIn(posJoueur)){
                   return true;
         }                     
     } 
     return false;
}
