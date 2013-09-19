#include "Modele.h"
#include <gl\glut.h>	

Modele::Modele(TiXmlElement* _elements, MapModele& _modeles, MapTex& _textures){
   lumiere = false;
   string nom =  _elements->Attribute("nom");
   MapModele::iterator p = _modeles.find(nom);
   if (p != _modeles.end()) {
      md2Object = p->second;
   }
   string nomTex =_elements->Attribute("textModele");
   MapTex::iterator iter = _textures.find(nomTex);
   if (iter != _textures.end()) {
      md2Object->setTexture(iter->second);
      texture = iter->second;
   }   
       int travers; _elements->QueryIntAttribute("lampe",&travers);

       if(travers == 1){
          lumiere = true;
       }
   _elements->QueryDoubleAttribute("rotation",&rotation);
   _elements->QueryDoubleAttribute("scale",&scale);
   _elements->QueryDoubleAttribute("x",&posModele.x);
   _elements->QueryDoubleAttribute("y",&posModele.y);
   _elements->QueryDoubleAttribute("z",&posModele.z);
}
void Modele::draw(){
float MatSpec[4] = {1.f,1.f,1.f,1.f};
float MatDif[4] = {.8f,.8f,.5f,1.f};
float MatAmb[4] = {.8f,.8f,.5f,1.f};
int   LightPos[4] = {0,0,0,0};
float LightDif[4] = {.6f,1.f,1.f,1.f};
float LightSpec[4] = {.6f,1.f,1.f,1.f};
float LightAmb[4] = {.0f,.0f,1.1f,1.f};
     md2Object->setTexture(texture);
     md2Object->draw(scale,rotation,posModele);
     if(lumiere){
     }
     else
               glDisable(GL_LIGHT0);
}


