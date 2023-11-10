TABLE DES MATIÈRES :
1- Guide de l'utilisateur
2- Organisation du programme

Ceci est un correcteur de fichiers textes pour le
cours IFT1025 à l'UdeM.

1- GUIDE DE L'UTILISATEUR

D'abord, l'utilisateur clique sur "Dictionnaire"
et charge un dictionnaire à partir d'un fichier.
Ensuite, l'utilisateur clique sur "Fichier", ce qui fait
glisser un menu déroulant, puis "Afficher" pour choisir
un fichier à achiffer dans la zone de texte OU il entre manuellement un texte dans
la zone de texte. Puis, l'utilisateur
clique sur "vérifier" pour surligner en rouge
les mots ne figurant pas dans le dictionnaire
choisi. Il peut cliquer sur un mot en
rouge pour faire apparaitre une fenêtre
proposant les 5 mots du dictionnaire les plus
proches du mot erroné. Cela permet à l'utilisateur
de cliquer sur une de ces options, qui va ensuite
remplacer le mot erroné dans la zone de texte.
Finalement, il peut cliquer sur "Enregistrer" pour
enregistrer le fichier texte et choisir son
emplacement.

Détails sur dictionnaire :
Le dictionnaire doit être un fichier texte
avec un mot par ligne pour une lecture
adéquate.

Détails sur le texte à corriger :
Le texte à corriger doit seulement être
composé de lettres et d'espaces ou sauts
de ligne pour une vérification adéquate.

2. CLASSES ET ORGANISATION DU PROGRAMME

Le programme est divisé en 4 classes en plus de
la classe Main, qui fait rouler le programme.

La classe InterfacePrincipale contient tous les
boutons et l'affichage du menu utilisateur, le
traitement du fichier ainsi que le traitement
d'événements.

La classe Dictionnaire contient les méthodes
relatives au dictionnaire, notamment les 5 mots
les plus proches, la conversion du dictionnaire
dans le bon format et une méthode de comparaison.

La classe MistakeHighlight permet de surligner dans
la zone de texte les mots erronés et aussi pour
enlever le surligneur.

La classe CorrectionWindow permet de corriger les
mots erronés selon le dictionnaire.

Auteurs : Philippe Schoeb, Joseph Descarreaux et Anne
Cléroux
30.10.2022