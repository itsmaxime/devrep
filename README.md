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

Le front en react devrait être fonctionnel 

===========================================================

POUR LA PARTIE BASE DE DONNEES :  

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

===========================================================

POUR PAR PARTIE BACKEND :  

Fichiers/dossiers nécessaires :  
-> devrepBanque.war  

Avoir au préalable (déjà mis en place dans la partie base de données) :  
-> Docker  
-> Tomcat  

Tout a été fait à l'étape précedente, Tomcat a été lancé en même temps que le
la base de données dans un container Docker  

Le fichier devrepBanque.war a été directement intégré dans Tomcat  

Tomcat est accessible à l'adresse localhost:8080, pour accéder à Manage App :  
    utilisateur  : admin  
    mot de passe : admin  

Le projet est maintenant complètement fonctionnel :D  

===========================================================

Les logs des transactions sont visibles depuis le terminal qui a lancé le container Docker  

===========================================================
