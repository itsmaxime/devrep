## DEVREP_BANQUE
Mini-projet d'application


# INSTALLATION

__POUR LA PARTIE FRONTEND :__

Dossier nécessaire :  
-> devrep_banque

Avoir sur la machine :  
-> Nodejs installé pour avoir la commande `npm`  
-> commande `create-react-app` avec `npm install -g create-react-app`  

Créer un projet react  
- `create-react-app devrep_app`  

Se placer dans le dossier créé  
- `cd devrep_app`

Recupérer les fichiers depuis Github  
- Remplacer les fichiers/dossiers du projet devrep_app créé juste avant par ceux dans le dossier devrep_banque. Garder le dossier node_modules. 

On peut maintenant démarrer, après les commandes l'application doit s'ouvrir dans un navigateur à l'adresse __localhost:3000__  
- `cd devrep_app`  
- `npm start`  

Il doit normalement manquer des dépendances, il faut donc les installer  
- `cd devrep_app`  
- `npm i --save @fortawesome/free-brands-svg-icons`

Recharger la page, le front en react devrait être fonctionnel. 

---

__POUR LA PARTIE BASE DE DONNEES :__

_DEUX METHODES POSSIBLES, lire les deux avant d'en appliquer une_

__METHODE 1 : Avec Docker__

Fichiers nécessaires :  
-> docker-compose.yml  
-> Compte.sql  

Avoir sur la machine :  
-> Docker installé et __démarré__  
-> commande `docker-compose`

Récupérer le dossier docker_db   
- `cd docker_db`  
- `docker-compose up` 

PhpMyAdmin sera maintenant accessible à l'adresse __localhost:3312__  
- utilisateur  : root  
- mot de passe : root  

Créer ensuite une base de données  
- Nouvelle base de données  
- Nom : devrep_banque  
- Créer  

Cliquer sur Importer  
- Choisir le fichier Compte.sql  
- Exécuter  

La base de données est prête pour l'application.  

__METHODE 2 : MySQL et MySQL Workbench__

Fichier nécessaire :  
    -> Compte.sql  

Avoir sur la machine :  
    -> MySQL  
    -> MySQL Workbench  

Importer Compte.sql dans une base de données nommée devrep_banque

---

__POUR LA PARTIE BACKEND :__  


_Optionnel : des modifications devront probablement être apportées dans le fichier "db.properties" selon la configuration de votre sgbd_  


Dossier nécessaire :  
-> devrep_banque_backend (projet Maven)  

Avoir sur la machine :  
-> Eclipse JEE 2020-03  
-> Tomcat 9.x 

- Récupérer le projet __devrep_banque_backend__ et l'ouvrir dans Eclipse
- Placer le projet dans le serveur web Tomcat intégré à Eclipse
- Démarrer le serveur web Tomcat  

Le projet entier devrait maintenant fonctionner.  

Les __logs des événements__ seront visibles dans la console d'Eclipse. 

# UTILISATION  

Pour la démo il faudra utiliser trois comptes :  
- devrepadm@gmail.com  
- devrepclient@gmail.com  
- devrepclient2@gmail.com  

Avant de commencer, plusieurs choses à savoir :  
- je n'ai implémenté que la connexion avec Google mais le principe reste le même pour Microsoft ou Github. Il est possible de cliquer sur Github mais la connexion ne marchera pas à cause d'un conflit dans Firebase.  
- la déconnexion ne fonctionne pas tout à fait, le compte Google reste connecté après avoir cliqué sur logout car il est toujours connecté sur le navigateur.  
- il faudra donc ouvrir un deuxième onglet pour se déconnecter de Google afin de continuer la démo sur un autre compte.   

## Page de connexion  

![alt text](https://github.com/itsmaxime/DEVREP_Banque/blob/main/images/connexion.png)

Nous avons ici la page de connexion, la première connexion se fera avec le compte administrateur "devrepadm@gmail.com" afin de fixer le montant de découvert autorisé et créer des comptes.  

## Page administrateur  

![alt text](https://github.com/itsmaxime/DEVREP_Banque/blob/main/images/administrateur.png)

Le compte administrateur peut gérer tout le système.  

Il peut fixer le montant de découvert et créer les comptes clients.  

Pour l'exemple, fixer le découvert à -500.  

Créer deux comptes que les clients vont pouvoir utiliser:
- numéro de compte : 11223344, email : devrepclient@gmail.com  
- numéro de compte : 55667788, email : devrepclient2@gmail.com  

Maintenant que l'administrateur a créé les deux comptes, les clients peuvent se connecter et accéder à leurs comptes.  

## Page client  

![alt text](https://github.com/itsmaxime/DEVREP_Banque/blob/main/images/client.png)

Sur la page de connexion, se connecter avec l'un des deux comptes Google pour arriver sur la page cliente ci-dessus.  

On peut voir à gauche le numéro de compte du client ainsi que le solde sur le compte. Les deux boutons `Withdraw`  et  `Deposit` permettent respectivement de retirer et déposer 200 sur le compte.  

Au milieu le client peut réaliser le __transfert__ d'un certain montant sur un autre compte.  

Pour l'exemple, faire des dépots jusqu'à ce que le solde du compte passe à 2000, puis réaliser un transfert de 1000 sur le compte 55667788. Le solde du compte va descendre à 1000 et le solde du compte 55667788 passera à 1000 (qui peut être vérifié en se connectant avec l'autre compte Google).  

Enfin à droite le client dispose d'un outil de conversion de devise.  

Une opération de retrait est rejetée lorsque le découvert maximum autorisé est atteint. Signalée par une alerte.

# OBJECTIFS

__Services côté client :__
- __(FAIT)__ Se connecter à son compte bancaire en utilisant plusieurs services d'authentification standards comme Google, Microsoft, GitHub, etc.    
- __(FAIT)__ Manipuler, une fois connecté, son compte bancaire avec les opérations classiques de retrait, dépôt et de transfert.  
- __(FAIT)__ Utiliser des services de conversion de devise.  

__Fonctionnalités côté administrateur :__
- __(FAIT)__ Il existe un rôle d’administrateur de l’ensemble du système, qu’on peut octroyer à seulement un compte. Ce rôle possède tous les droits possibles pour administrer l’ensemble du système (y compris créer des comptes pour les clients).  
- __(FAIT)__ L’administrateur aura aussi la possibilité de fixer des informations globales pour la gestion de compte. Un exemple de ces données est la définition du montant de découvert autorisé pour les clients.  

__Architecture de l'application__  
Il serait bien de concevoir et développer le système selon une architecture n-tiers. Vous pouvez constituer une architecture micro service si vous le souhaitez et avez suffisamment de temps pour le faire.  

__(FAIT)__ Architecture n-tiers :
- Front-end : React  
- Back-end : JEE, Servlet, Tomcat  
- Base de données : MySQL  

__Logging__
- __(FAIT)__ Journaliser les événements pendant le fonctionnement du système, utilisation de la bibliothèque Logback qui est une implémentation de la spécification slf4j.

