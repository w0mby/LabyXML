README:


Execution du projet.
====================
L'excutable du projet est l'executable XmLabyrinth3D.exe.


Modification du niveau à charger.
=================================
Deux mondes sont disponibles. monde.Xml et monde2.xml.
Pour changer de niveau executer le fichier changeLvl.bat pour passer d'un niveau à l'autre.

Documentations.
===============
La documentation a été générée avec CppDoc2. Les sources ont été génerées en html.

Source du projet.
=================
Les sources du projets sont situées dans le dossier "Sources_et_executable". 
Le projet à été développé et compilé à l'aide de l'IDE Dev-Cpp 5 (version 4.9.9.2).


Remarque
========

-Les quatres fichiers bat présent dans le dosssier permettent un acces rapide aux différents executables 
 et fichier utiles au lancement du projet et à la lecture de la documentation:

	- XmLabyrinth3D.Shortcut.bat permet l'executation de XmLabyrinth3D.exe. 
          C'est à dire le projet en lui-même.

	- ChangeLvl.Shortcut.bat permet l'execution du .bat permettant de switcher du niveau monde.xml au niveau monde.xml2
	  Ces deux fichiers sont deux niveaux différents.

	- CppDoc.Shortcut.bat permet de visionner la documentation génerée par CppDoc.

	- CreateurLabyrinth.Shortcut.bat permet d'executer le projet java permettant de créer les mondes charger dans le projet.

Les fichiers .bat ont été préferés aux raccourcis Windows qui ne permettent pas les chemins relatifs.
	

-Est fourni avec le projet, le createur de labyrinthe ecrit en java et executable par le fichier createurLabyrinth.jar  
 contenu dans le dossier CreateurLabyrinth. Ce projet Java est remis à titre informatif.