#include "Surface.h"

Surface::Surface(TiXmlElement* _surface,MapTex& textures){
     TiXmlElement* elem;
     string textureName = _surface->Attribute("tex");   
     MapTex::iterator p = textures.find(textureName);
     if (p != textures.end()) {
        texture = p->second;
     }    
     elem = _surface->FirstChildElement("vertex");
     Vertex vertex;
      while (elem){
           elem->QueryDoubleAttribute("x",&vertex.x);   
           elem->QueryDoubleAttribute("y",&vertex.y);  
           elem->QueryDoubleAttribute("z",&vertex.z);   
           vertexs.push_back(vertex);
           elem = elem->NextSiblingElement("vertex");
       }
     elem = _surface->FirstChildElement("coordTex");
     CoordTex coordtex;
      while (elem){
           elem->QueryDoubleAttribute("x",&coordtex.x);   
           elem->QueryDoubleAttribute("y",&coordtex.y);  
           coordTexs.push_back(coordtex);
           elem = elem->NextSiblingElement("coordTex");
       }
}

void Surface::draw(){
     glBindTexture(GL_TEXTURE_2D, texture);
     glBegin(GL_QUADS);
     for (int i=0;i<vertexs.size();i++){
         glTexCoord2f(coordTexs[i].x,coordTexs[i].y);
         glVertex3f(vertexs[i].x,vertexs[i].y, vertexs[i].z);   
             
     }
     glEnd();    
}

GLfloat Surface::getMinX() {
        GLfloat min = vertexs[0].x;
        for (int i=1;i<vertexs.size();i++){
            if ( vertexs[i].x < min ) min = vertexs[i].x ;
        }
        return min;
}
GLfloat Surface::getMaxX() {
        GLfloat max = vertexs[0].x;
        for (int i=1;i<vertexs.size();i++){
            if ( vertexs[i].x > max ) max = vertexs[i].x ;
        }
        return max;        
}
GLfloat Surface::getMinZ() {
        GLfloat min = vertexs[0].z;
        for (int i=1;i<vertexs.size();i++){
            if ( vertexs[i].z < min ) min = vertexs[i].z ;
        }
        return min;        
}
GLfloat Surface::getMaxZ() {
        GLfloat max = vertexs[0].z;
        for (int i=1;i<vertexs.size();i++){
            if ( vertexs[i].z > max ) max = vertexs[i].z ;
        }
        return max;        
}
