#include "Controleur.h"

#include <cstdio>
#include <cmath>

Controleur::Controleur(char * monde,int _hauteurEcran,int _largeurEcran) {
    MapTex maptex = LoadGLTextures();
    MapModele mapmod = LoadGLModels();
    laby = new Labyrinthe(monde,mapmod,maptex);
    posJoueur.x = 2;
    posJoueur.y = 0.5 ;
    posJoueur.z = 0;
    posVue.x = -2;    
    posVue.y = 0; 
    posVue.z = 0;   
    hauteurEcran = _hauteurEcran;
    largeurEcran = _largeurEcran;
    degVue = 90.0f;
    freinJoueur = 6;

        
    for(int i = 0; i < 256;i++) {
            keys[i] = false;
    }                          
}  

void Controleur::resize(int width, int height){
    glViewport(0, 0, width, height);
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    gluPerspective(45.0f,(GLfloat)width/(GLfloat)height,0.1f,100.0f);
    glMatrixMode(GL_MODELVIEW);
    glLoadIdentity();
}

void Controleur::display(void){
     glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); 
     laby->draw(posJoueur,posVue);
     glutSwapBuffers();
}

void Controleur::keyboardUp(unsigned char key, int x, int y){
     keys[key] = false;      
}
void Controleur::keyboard(unsigned char key, int x, int y){
     keys[key] = true; 
}

void Controleur::mouseMove(int x, int y) {    
     static int mousePrcdX = -1;
     static int mousePrcdY = -1;
     float  deplacementVueY = 0.0f; 
     if ( mousePrcdX != -1) {
           degVue += ((float)(mousePrcdX - x)) /10;
           deplacementVueY = y - mousePrcdY;
     }
     calculerPosVue(); 
     posVue.y -= tan( deplacementVueY * rapportRadian ); 
     if(posVue.y > 9) posVue.y = 9;
     if(posVue.y < -9) posVue.y = -9;
    
     mousePrcdX = x;
     mousePrcdY = y; 
      
     if ( x > largeurEcran-200 || x < 200 || y > hauteurEcran-200 ||y < 200 ) {
        DestroyCursor(GetCursor());
        SetCursorPos(largeurEcran/2,hauteurEcran/2);
        mousePrcdX = -1;
        mousePrcdY = -1;
     }
       
}

void Controleur::idle(void){
     glutPostRedisplay();
}

void Controleur::timer(int value) {
     postChange();     
}

void Controleur::postChange() {
     if(keys['z']) { 
       if ( laby->estIn( Vertex( (posJoueur.x + (sin(degVue * rapportRadian) )) , 1 ,(posJoueur.z + (cos(degVue * rapportRadian))) ) ) ) { // detection de la colision
                          posJoueur.x += sin(degVue * rapportRadian)/freinJoueur ;
                          posJoueur.z += cos(degVue * rapportRadian)/freinJoueur ;
                          calculerPosVue(); 
       }         
     }
     if(keys['s']) {
       if ( laby->estIn( Vertex( (posJoueur.x - (sin(degVue * rapportRadian) )) , 1 ,(posJoueur.z - (cos(degVue * rapportRadian))) ) ) ) { // detection de la colision
                          posJoueur.x -= sin(degVue * rapportRadian)/freinJoueur ;
                          posJoueur.z -= cos(degVue * rapportRadian)/freinJoueur ;
                          calculerPosVue(); 
       }  
     } 
     if (keys['q'] ) {
       if ( laby->estIn( Vertex( (posJoueur.x + (cos(degVue * rapportRadian) )) , 1 ,(posJoueur.z - (sin(degVue * rapportRadian))) ) ) ) { // detection de la colision
                          posJoueur.x += cos(degVue * rapportRadian)/freinJoueur ;
                          posJoueur.z -= sin(degVue * rapportRadian)/freinJoueur ;
                          calculerPosVue(); 
       }      
     
     }
     if (keys['d'] ) {
        if ( laby->estIn( Vertex( (posJoueur.x - (cos(degVue * rapportRadian) )) , 1 ,(posJoueur.z + (sin(degVue * rapportRadian))) ) ) ) { // detection de la colision
                          posJoueur.x -= cos(degVue * rapportRadian)/freinJoueur ;
                          posJoueur.z += sin(degVue * rapportRadian)/freinJoueur ;
                          calculerPosVue(); 
       }   
     
     }
     
     if(keys['+']) {
       if ( freinJoueur > 6 ) freinJoueur--;
     }
     
     if(keys['-']) {
       freinJoueur++;
     }
     
     if(keys[27]) { // le code de la touche escape
       delete laby;
       exit(0);
     }     
}

void Controleur::calculerPosVue() {
    posVue.x = posJoueur.x+(sin(degVue * rapportRadian ) * 5);
    posVue.z = posJoueur.z+(cos(degVue * rapportRadian )* 5);  
}

MapTex Controleur::LoadGLTextures(){
    MapTex mapTex;    
    WIN32_FIND_DATA File;
    HANDLE hSearch;
    Image imageBmp;
    bool re = true;
    int i = 0;
    hSearch=FindFirstFile("Textures/*.bmp", &File);
    do{
       string path  = "Textures/";
       path += File.cFileName;    
       ImageLoad((char*)path.c_str(),&imageBmp); 
       string nom(File.cFileName);
       GLuint texture;
       glGenTextures(1, &texture);
	   glBindTexture(GL_TEXTURE_2D, texture);
	   mapTex.insert(PaireTex(nom,texture));
       glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MIN_FILTER,GL_LINEAR);
       glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MAG_FILTER,GL_LINEAR);
       glTexImage2D(GL_TEXTURE_2D, 0, 3, imageBmp.sizeX, imageBmp.sizeY, 0, GL_RGB, GL_UNSIGNED_BYTE, imageBmp.data);
       free(imageBmp.data);        // liberation de l'espace memoire alloué pour l'image.
     re = FindNextFile(hSearch, &File);
       i++;
    } 
    while(re);
    FindClose(hSearch);
    return mapTex;
}

MapModele Controleur::LoadGLModels(){
    MapModele mapModel;    
    WIN32_FIND_DATA File;
    HANDLE hSearch;
    bool re = true;
    int i = 0;
    string dossier ="Model/";
    string path;
    hSearch=FindFirstFile("Model/*.md2", &File);
    do{
       string nom = File.cFileName;
       path = dossier + nom;
	   mapModel.insert(PaireModel(nom,new Md2Object((char *)path.c_str())));
	   re = FindNextFile(hSearch, &File);
    } 
    while(re);
    FindClose(hSearch);
    return mapModel;        
}

int Controleur::ImageLoad(char *filename, Image *image) {
    FILE *file;
    unsigned long size; 
    unsigned long i;    
    unsigned short int planes;
    unsigned short int bpp;   
    char temp;          
    file = fopen(filename, "rb");
    fseek(file, 18, SEEK_CUR);
    image->sizeX = getint (file);         //recuperation de la largeur dans le header du fichier
    image->sizeY = getint (file);         //recuperation de la longueur dans le header du fichier
    size = image->sizeX * image->sizeY * 3;
    // la taille de l'image se trouve dans le fichier. Seulement certains logiciel ne la mettent pas
    // il est donc plus sur de s'en passer d'office et de faire le calcul.
    // REmarque: le 3 proviens du nombre de "Composante" de la couleur. Comme on a un codage de la couleur
    // sous forme RGB on a donc 3 composantes.
    bpp = getshort(file); // recupere le nombre de bit representant une couleur.
    planes = getshort(file);
    fseek(file, 24, SEEK_CUR);
    image->data = (char *) malloc(size);
    fread(image->data, size, 1, file);
    //La boucle suivante va permettre d'avoir a nouveau un codage RGB. En effet les fichiers bmp ont un codage des couleurs
    // ordonnées tel que BRG. il faut donc inverser les composantes sans quoi les couleurs seront inccorectes.
    for (i=0;i<size;i+=3) { 
	    temp = image->data[i];
	    image->data[i] = image->data[i+2];
	    image->data[i+2] = temp;
    }
    return 1;
}

// fonctions utilisees par le loader 
// on se deplace de char en char dans le fichier (fread,fseek,...)
// pour se deplacer d'un int et le recuperer on va donc se deplacer de 4 char et concatener les 4 char pour en faire un int.
unsigned int Controleur::getint(FILE *fp) {
  int c = getc(fp), c1 = getc(fp), c2 = getc(fp), c3 = getc(fp);
  return ((unsigned int) c) +   (((unsigned int) c1) << 8) + (((unsigned int) c2) << 16) +(((unsigned int) c3) << 24);
}
// pour se deplacer d'un short et le recuperer on va donc se deplacer de 2 char et concatener les 2 char pour en faire un short.
unsigned int Controleur::getshort(FILE *fp) {
  int c = getc(fp), c1 = getc(fp);
  return ((unsigned int) c) + (((unsigned int) c1) << 8);
}
