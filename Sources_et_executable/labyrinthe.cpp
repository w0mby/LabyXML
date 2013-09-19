#include "labyrinthe.h"

void Labyrinthe::draw(Vertex posPerso,Vertex posVue) {
     for (int i=0;i<elemComplexe.size();i++) {
         elemComplexe[i]->draw(posPerso,posVue);    
     }            
}

Labyrinthe::Labyrinthe(char * nomFichier, MapModele& _modeles,MapTex& textures){
     TiXmlDocument doc(nomFichier);
     doc.LoadFile();
     TiXmlHandle hdl(&doc);
     TiXmlElement *elem = hdl.FirstChild("monde").FirstChildElement("composant").Element();
     Vertex posComposant;
     while (elem){
           elem->QueryDoubleAttribute("x",&posComposant.x);
           elem->QueryDoubleAttribute("y",&posComposant.y);
           elem->QueryDoubleAttribute("z",&posComposant.z);
           elemComplexe.push_back(new Composant(elem,posComposant,_modeles,textures));
           elem = elem->NextSiblingElement("composant");
     }
}
bool Labyrinthe::estIn(Vertex posJoueur){
     for(int i=0;i<elemComplexe.size();i++){
         if(elemComplexe[i]->estIn(posJoueur)){
                   return true;
         }                     
     } 
     return false;
}
