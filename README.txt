README:


Execution du projet.
====================
L'excutable du projet est l'executable XmLabyrinth3D.exe.


Modification du niveau � charger.
=================================
Deux mondes sont disponibles. monde.Xml et monde2.xml.
Pour changer de niveau executer le fichier changeLvl.bat pour passer d'un niveau � l'autre.

Documentations.
===============
La documentation a �t� g�n�r�e avec CppDoc2. Les sources ont �t� g�ner�es en html.

Source du projet.
=================
Les sources du projets sont situ�es dans le dossier "Sources_et_executable". 
Le projet � �t� d�velopp� et compil� � l'aide de l'IDE Dev-Cpp 5 (version 4.9.9.2).


Remarque
========

-Les quatres fichiers bat pr�sent dans le dosssier permettent un acces rapide aux diff�rents executables 
 et fichier utiles au lancement du projet et � la lecture de la documentation:

	- XmLabyrinth3D.Shortcut.bat permet l'executation de XmLabyrinth3D.exe. 
          C'est � dire le projet en lui-m�me.

	- ChangeLvl.Shortcut.bat permet l'execution du .bat permettant de switcher du niveau monde.xml au niveau monde.xml2
	  Ces deux fichiers sont deux niveaux diff�rents.

	- CppDoc.Shortcut.bat permet de visionner la documentation g�ner�e par CppDoc.

	- CreateurLabyrinth.Shortcut.bat permet d'executer le projet java permettant de cr�er les mondes charger dans le projet.

Les fichiers .bat ont �t� pr�fer�s aux raccourcis Windows qui ne permettent pas les chemins relatifs.
	

-Est fourni avec le projet, le createur de labyrinthe ecrit en java et executable par le fichier createurLabyrinth.jar  
 contenu dans le dossier CreateurLabyrinth. Ce projet Java est remis � titre informatif.