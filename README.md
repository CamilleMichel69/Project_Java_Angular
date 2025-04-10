        Application d'évaluation de restaurants : JAVA / Spring côté Back-End - Angular côté Front-End

Pour ce projet, j'ai développé une application en Java, à l'aide de Spring, en créant une API fonctionnelle. 
Je l'ai ensuite adaptée côté front-end grâce à Angular

Objectifs du projet :
- Construire une API Web RESTful avec persistance des données à l'aide de Spring et Spring Data JPA
- Utiliser Spring Initializr pour générer le projet Java initial
- Configurer les propriétés de l'application pour certaines dépendances, y compris la base de données intégrée H2
- Définir les entités qui composent ce scénario d'application
- Définir les référentiels permettant de créer, de mettre à jour et d'interroger ces différentes entités
- Définir les contrats API qui permettront ce scénario d'application
- Utiliser cURL ou Postman pour tester les scénarios d'API

L'application d'évaluation de restaurants s'articule autour des concepts de base suivants :

- Un restaurant
- Une critique 
- Un utilisateur
  
Un restaurant attribue des notes d'avis basées sur les avis laissés par les utilisateurs. 
Sur une échelle de 1 à 5, 5 étant le meilleur, chaque restaurant attribue des notes individuelles pour les allergies alimentaires suivantes :
- arachide ("peanut")
- œuf ("egg")
- laitier ("dairy")
Chaque score individuel correspond à la moyenne de tous les scores soumis pour la catégorie donnée. En l'absence de score soumis, le score d'une catégorie donnée sera nul.


      Voici les différents scénarios de l'application :

Scénarios liés à l'entité utilisateur :

- En tant qu'utilisateur non enregistré, je souhaite créer mon profil utilisateur en utilisant un nom d'affichage qui m'est propre.
- En tant qu'utilisateur enregistré, je souhaite mettre à jour mon profil. Je ne peux pas modifier mon nom d'affichage unique.
- En tant qu'expérience d'application, je souhaite récupérer le profil utilisateur appartenant à un nom d'affichage donné.

Scénarios liés à l'entité de restauration :

- Dans le cadre de mon expérience d'application, je souhaite soumettre une nouvelle entrée de restaurant. Si un restaurant portant le même nom et le même code postal existe déjà, je verrai un échec.
- En tant qu'expérience d'application, je souhaite récupérer les détails d'un restaurant, compte tenu de son identifiant unique.
- Dans le cadre de mon expérience d'application, je souhaite récupérer les restaurants correspondant à un code postal donné et ayant au moins un score soumis par un utilisateur pour une allergie donnée. Je souhaite les voir classés par ordre décroissant.

Scénarios liés à l'évaluation d'un restaurant :

- En tant qu'utilisateur enregistré, je souhaite soumettre un avis sur un restaurant.

Scénarios liés à l'action d'évaluation des repas :

- En tant qu'administrateur, je souhaite obtenir la liste de tous les avis de restauration en attente d'approbation.
- En tant qu'administrateur, je souhaite approuver ou rejeter un avis de restauration donné.
- Dans le cadre du processus backend qui met à jour l'ensemble des scores d'un restaurant, je souhaite récupérer l'ensemble de tous les avis de restauration approuvés appartenant à ce restaurant.



