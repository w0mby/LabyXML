#ifndef __Vertex
#define __Vertex

#include <gl\gl.h>

struct Vertex {
       GLfloat x,y,z;
       Vertex(){}
       Vertex(GLfloat _x,GLfloat _y,GLfloat _z) { x=_x;y=_y;z=_z; }
};

#endif
