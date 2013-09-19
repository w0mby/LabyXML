#ifndef __CoordTex
#define __CoordTex

#include <gl\gl.h>

struct CoordTex {
       GLfloat x,y;
       CoordTex(){}
       CoordTex(GLfloat _x,GLfloat _y) { x=_x;y=_y;}
};

#endif
