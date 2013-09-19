#include "Controleur.h"

Controleur * controleur;
const int hauteurEcran = 768;
const int largeurEcran = 1024;

void resize(int width, int height){
     controleur->resize(width,height);  
}

void display(void){
     controleur->display();
}

void keyboardUp(unsigned char key, int x, int y){
     controleur->keyboardUp(key,x,y);
}
void keyboard(unsigned char key, int x, int y){
     controleur->keyboard(key,x,y);
}

void mouseMove(int x, int y) {
     controleur->mouseMove(x,y);
}

void idle(void){
     controleur->idle();
}

void timer(int value){
     glutTimerFunc(20,timer,1);
     controleur->timer(value);
     
}

int main(int argc, char *argv[]){
    glutInit(&argc, argv);
    glutInitWindowSize(largeurEcran,hauteurEcran); 
   
    ShowCursor(false);	
    
    glutInitDisplayMode(GLUT_RGB | GLUT_DOUBLE | GLUT_DEPTH);
    glutCreateWindow("XmLabyrinth3D");
    glutReshapeFunc(resize);
    glutDisplayFunc(display);
    glutKeyboardFunc(keyboard);
    glutKeyboardUpFunc(keyboardUp);
    glutPassiveMotionFunc(mouseMove);        
    glutIdleFunc(idle);
    glutTimerFunc(20,timer,1);
    
    glEnable(GL_TEXTURE_2D);		                    // Activation des textures.
	glClearColor(0.8f, 0.8f, 0.8f, 1.0f);				// le "fond" d'ecran sera tout gris.
	glClearDepth(10.0f);				                // Activation du Depth buffer (Z-buffer)
	glEnable(GL_DEPTH_TEST);	                        // Activation du test de profondeur
    glFogi(GL_FOG_MODE, GL_EXP2);                       // Mode du brouillard
    GLfloat fogColor[]= {0.8f, 0.8f, 0.8f, 1.0f};       // Couleur du brouillard
    glFogfv(GL_FOG_COLOR, fogColor);                    // Assigne la couleur du Brouillard.
    glFogf(GL_FOG_DENSITY, 0.13f);                      // Determine la densité du brouillard.
    glHint(GL_FOG_HINT, GL_NICEST);                     // Determine la qualité du brouillard.
    glEnable(GL_FOG);                                   // Activation du brouillard.   
    //glEnable(GL_LIGHTING);
    controleur =  new Controleur("monde.xml",hauteurEcran,largeurEcran); 
    glutFullScreen();
    glutMainLoop();
    ShowCursor(true);
    delete controleur;
    return EXIT_SUCCESS;
}
