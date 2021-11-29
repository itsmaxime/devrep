# DEVREP_BANQUE
Mini-projet d'application


INSTALLATION

===========================================================

POUR LA PARTIE FRONTEND :

Fichiers/dossiers nécessaires :  
-> devrep_banque

Avoir au préalable :  
-> Node.js d’installé pour avoir la commande npm  
https://nodejs.org/en/download/  
-> commande create-react-app avec "npm install -g create-react-app"  

Créer un projet react  
    $ create-react-app devrep_app  

Se placer dans le dossier créé  
    $ cd devrep_app

Recupérer les fichiers depuis Github  
    - Remplacer à l'identique les fichiers/dossiers du projet devrep_app crée juste avant
      par ceux dans le dossier devrep_banque sur Github

On peut maintenant démarrer, après les commandes l'application doit s'ouvrir dans un navigateur à l'adresse localhost:3000  
    $ cd devrep_app  
    $ npm start  

Il doit normalement manquer des dépendances, il faut donc les installer  
    $ npm i --save @fortawesome/free-brands-svg-icons

Recharger la page, le front en react devrait être fonctionnel 

===========================================================

POUR LA PARTIE BASE DE DONNEES :  

DEUX METHODES POSSIBLES, lire les deux avant d'en appliquer une

METHODE 1 : Avec Docker

Fichiers/dossiers nécessaires :  
-> docker-compose.yml  
-> Compte.sql  

Avoir au préalable :  
-> Docker installé  

Créer un dossier docker_db et y placer le fichier docker-compose.yml  
    $ cd docker_db  
    $ docker-compose up  

PhpMyAdmin sera maintenant accessible à l'adresse localhost:3312  
    utilisateur  : root  
    mot de passe : root  

Créer ensuite une base de données  
    - Nouvelle base de données  
    - Nom : devrep_banque  
    - Créer  

Cliquer sur Importer  
    - Choisir le fichier Compte.sql  
    - Exécuter  

Maintenant la base de données est prête pour l'application  

METHODE 2 : MySQL et MySQL Workbench

Fichiers/dossiers nécessaires :  
    -> Compte.sql  

Avoir au préalable :  
    -> MySQL  
    -> MySQL Workbench  

Importer Compte.sql dans une base de données nommée devrep_banque

===========================================================

POUR LA PARTIE BACKEND :  


Des modifications devront probablement être apportées dans le fichier "db.properties" 
selon la configuration de votre sgbd  


Fichiers/dossiers nécessaires :  
-> devrep_banque_backend  

Avoir au préalable :  
-> Eclipse JEE 2020-03  
-> Tomcat 9.x 

Récupérer le projet devrep_banque_backend et l'ouvrir dans Eclipse

Placer le projet dans le serveur web Tomcat intégré à Eclipse

Démarrer le serveur web Tomcat

Le projet entier devrait maintenant fonctionner  

===========================================================

Les logs des transactions sont visibles depuis le terminal qui a lancé le container Docker  
