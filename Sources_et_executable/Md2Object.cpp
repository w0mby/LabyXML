#include "Md2Object.h"

Md2Object::Md2Object(char * fichier){
     FILE *fp;
     mdl = (md2_model_t *)malloc (sizeof (md2_model_t));
     int i;
     fp = fopen (fichier, "rb");
     /* read header */
     fread (&mdl->header, 1, sizeof (md2_header_t), fp);
     /* memory allocation */
     mdl->skins = (md2_skin_t *)malloc (sizeof (md2_skin_t) * mdl->header.num_skins);
     mdl->texcoords = (md2_texCoord_t *)malloc (sizeof (md2_texCoord_t) * mdl->header.num_st);
     mdl->triangles = (md2_triangle_t *)malloc (sizeof (md2_triangle_t) * mdl->header.num_tris);
     mdl->frames = (md2_frame_t *)malloc (sizeof(md2_frame_t) * mdl->header.num_frames);
     mdl->glcmds = (int *)malloc (sizeof (int) * mdl->header.num_glcmds);
     fseek (fp, mdl->header.offset_st, SEEK_SET);
     fread (mdl->texcoords, sizeof (md2_texCoord_t), mdl->header.num_st, fp);
     fseek (fp, mdl->header.offset_tris, SEEK_SET);
     fread (mdl->triangles, sizeof (md2_triangle_t), mdl->header.num_tris, fp);
     fseek (fp, mdl->header.offset_glcmds, SEEK_SET);
     fread (mdl->glcmds, sizeof (int), mdl->header.num_glcmds, fp);
     /* read frames */
     fseek (fp, mdl->header.offset_frames, SEEK_SET);
     for (i = 0; i < mdl->header.num_frames; ++i){
         /* memory allocation for vertices of this frame */
         mdl->frames[i].verts = (md2_vertex_t *)
	     malloc (sizeof (md2_vertex_t) * mdl->header.num_vertices);
         /* read frame data */
         fread (mdl->frames[i].scale, sizeof (vec3_t), 1, fp);
         fread (mdl->frames[i].translate, sizeof (vec3_t), 1, fp);
         fread (mdl->frames[i].name, sizeof (char), 16, fp);
         fread (mdl->frames[i].verts, sizeof (md2_vertex_t), mdl->header.num_vertices, fp);
     }
     fclose (fp);
}

Md2Object::~Md2Object(){
     int i;
     if (mdl->skins){
        free (mdl->skins);
        mdl->skins = NULL;
     }
     if (mdl->texcoords){
        free (mdl->texcoords);
        mdl->texcoords = NULL;
     }
     if (mdl->triangles){
        free (mdl->triangles);
        mdl->triangles = NULL;
     }
     if (mdl->glcmds){
        free (mdl->glcmds);
        mdl->glcmds = NULL;
     }
     if (mdl->frames){
        for (i = 0; i < mdl->header.num_frames; ++i){
            free (mdl->frames[i].verts);
	        mdl->frames[i].verts = NULL;
        }
        free (mdl->frames);
        mdl->frames = NULL;
     }
     //free(mdl);
    // mdl = NULL;
}

void Md2Object::draw(float scale,float rotation, Vertex posObjet){
  int i, j;
  GLfloat s, t;
  vec3_t v;
  md2_frame_t *pframe;
  md2_vertex_t *pvert;
  //active la texture en attribut comme texture courante.
  glBindTexture (GL_TEXTURE_2D, texture);
  //dessine le modele
  glPushMatrix();               //Sauvegarde de la matrice avant toutes les modifications
  glTranslatef(posObjet.x,posObjet.y,posObjet.z);
  glScalef(scale,scale,scale);
  glRotatef(rotation,0,1,0);
  glRotatef(-90,1,0,0);       //le modele semble enregistré avec un dephasage de 90° dans le fichier
  glBegin (GL_TRIANGLES);
    //dessine chaque triangle
    for (i = 0; i < mdl->header.num_tris; ++i){
	   //dessine chaque vertex
	   for (j = 0; j < 3; ++j){
	       pframe = &mdl->frames[0];
	       pvert = &pframe->verts[mdl->triangles[i].vertex[j]];
           // recuperer les coordonnée de la texture à appliqué
	       s = (GLfloat)mdl->texcoords[mdl->triangles[i].st[j]].s / mdl->header.skinwidth;
	       t = (GLfloat)mdl->texcoords[mdl->triangles[i].st[j]].t / mdl->header.skinheight;
	       // passe les coordonnées de la texture à OpenGL
	       glTexCoord2f (s, t);
	       // calcul la position réel du vertex
	       v[0] = (pframe->scale[0] * pvert->v[0]) + pframe->translate[0];
	       v[1] = (pframe->scale[1] * pvert->v[1]) + pframe->translate[1];
	       v[2] = (pframe->scale[2] * pvert->v[2]) + pframe->translate[2];
	       glVertex3fv (v);
        }
      }
  glEnd ();
  glPopMatrix();
}
