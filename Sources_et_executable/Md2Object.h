#ifndef __MD2OBJECT
#define __MD2OBJECT
#include <gl/glu.h>
#include <gl/gl.h>
#include <stdio.h>
#include <stdlib.h>
#include <string>
#include "Vertex.h"

typedef float vec3_t[3];

/* md2 header */
typedef struct
{
  int ident;
  int version;

  int skinwidth;
  int skinheight;

  int framesize;

  int num_skins;
  int num_vertices;
  int num_st;
  int num_tris;
  int num_glcmds;
  int num_frames;

  int offset_skins;
  int offset_st;
  int offset_tris;
  int offset_frames;
  int offset_glcmds;
  int offset_end;
} md2_header_t;
/* texture name */
typedef struct { char name[64]; } md2_skin_t;
/* texture coords */
typedef struct { short s; short t; } md2_texCoord_t;
/* triangle info */
typedef struct { unsigned short vertex[3]; unsigned short st[3]; } md2_triangle_t;
/* compressed vertex */
typedef struct {unsigned char v[3]; unsigned char normalIndex; } md2_vertex_t;
/* model frame */
typedef struct{ vec3_t scale; vec3_t translate; char name[16]; md2_vertex_t *verts; } md2_frame_t;
/* gl command packet */
typedef struct{ float s; float t; int index; } md2_glcmd_t;
/* md2 model structure */
typedef struct{
  md2_header_t header;
  md2_skin_t *skins;
  md2_texCoord_t *texcoords;
  md2_triangle_t *triangles;
  md2_frame_t *frames;
  int *glcmds;
  GLuint tex_id;
} md2_model_t;

/** 
* Classe permettant de charger et de représenter un Modele de type Md2.
* @author Damien Dormal, Bavay Jonathan & Amine Rebbouh
*/
class Md2Object {
      private :
              /**Structure contenant toutes les informations sur le modele.*/
              md2_model_t* mdl;
              /**Texture associée au modele*/
              GLuint texture;
      public :      
             /**
             * Construit un modele complexe a partir d'un fichier Md2
             * @param fichier fichier .md2 contenant le modele à représenter.
             */
             Md2Object(char * fichier);
             /* Permet de dessiner un modele.
             * Il est possible de dessiner un meme modele avec une taille differente ou un emplacement different
             * @param scale echelle a laquelle sera dessiner le modele.
             * @param rotation rotation a faire effectué au modele.
             * @param posObjet Position de l'objet.
             */
             void draw(float scale,float rotation, Vertex posObjet);
             /**
             * Assigne une texture au modele.
             * @param texture entier représentant la texture en mémoire.
             */
             void setTexture(GLuint _texture){texture = _texture;};
             /**
             * Destructeur de md2Object
             */
             ~Md2Object();
             //Md2Object(const Md2Object& md2object);
};

#endif
