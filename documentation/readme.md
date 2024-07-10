employee-app/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── com/
│   │   │   │   ├── example/
│   │   │   │   │   ├── employee/
│   │   │   │   │   │   ├── controller/
│   │   │   │   │   │   │   └── EmployeeController.java
│   │   │   │   │   │   ├── model/
│   │   │   │   │   │   │   └── Employee.java
│   │   │   │   │   │   ├── repository/
│   │   │   │   │   │   │   └── EmployeeRepository.java
│   │   ├── resources/
│   │   │   ├── application.properties
├── pom.xml

Structure de l'application
L'application ECF4_Restful se compose de plusieurs couches :

1. Entity : Représente les données persistantes. Elle utilise les annotations JPA pour la persistance.
2. DTO : Objet de transfert de données, simplifie les échanges entre les couches.
Deux DTOs sont définis : TodoDtoGet pour l'affichage et TodoDtoPost pour la création et la mise à jour.
3. Repository : gère les interactions avec la base de données.
4. Service : contient la logique métier. Il gère la conversion entre DTOs et entités et utilise le repository pour les opérations CRUD.
5. Controller : Gère les requêtes HTTP et les réponses.Les controllers exposent les API REST.
6. Gestion des Exceptions : pour gérer les exceptions, nous utilisons une classe annotée avec @ControllerAdvice. Cette classe gère les exceptions spécifiques et renvoie des réponses HTTP appropriées.
Explication détaillée
NotFoundException :

Cette classe d'exception personnalisée étend RuntimeException. Elle est utilisée pour signaler qu'une entité demandée n'a pas été trouvée.
Il y a deux constructeurs : un par défaut avec un message fixe et un qui accepte un message personnalisé.
GeneralExceptionHandler :

@ControllerAdvice : Cette annotation indique que cette classe fournit des conseils globaux pour tous les contrôleurs de l'application. Elle permet de centraliser la gestion des exceptions.
notFoundExceptionHandler : Cette méthode est annotée avec @ExceptionHandler(NotFoundException.class). Elle gère les NotFoundException et renvoie une réponse HTTP avec le statut 404 (Not Found) et le message d'erreur.
dateTimeParseExceptionHandler : Cette méthode gère les exceptions DateTimeParseException (qui se produisent lors de l'analyse d'une date incorrecte) et renvoie une réponse HTTP avec le statut 400 (Bad Request) et un message d'erreur approprié.
globalExceptionHandler : Cette méthode gère toutes les autres exceptions non spécifiées et renvoie une réponse HTTP avec le statut 500 (Internal Server Error) et un message d'erreur générique.
Ces classes et méthodes d'exception garantissent que votre application gère gracieusement les erreurs et fournit des messages d'erreur significatifs aux utilisateurs de l'API.

Avec ces tests, vous pouvez vérifier que l'application de gestion des employés reçoit correctement les données nécessaires pour la création d'un nouvel employé suite à la validation de l'entretien du candidat. Les tests TDD vérifient la logique interne de l'application tandis que les tests BDD assurent que le processus de bout en bout fonctionne correctement, y compris la communication entre les deux applications.
Tests TDD
Test de réception des données d'un nouvel employé
Classe de test JUnit pour vérifier la réception des données d'un nouvel employé :

Configuration de l'application
Le fichier application.properties configure la connexion à la base de données.
Le fichier pom.xml contient les dépendances nécessaires pour le projet Spring Boot.


Communication entre les deux applications

Lorsque l'application de recrutement décide de recruter un nouvel employé, elle envoie une requête HTTP POST à l'application de gestion des employés. L'application de gestion des employés reçoit ces données et les enregistre dans sa base de données.

Points clés de la communication
RESTful Services : Les deux applications communiquent via des points de terminaison REST.
RestTemplate : Utilisé dans l'application de recrutement pour envoyer les requêtes HTTP à l'application de gestion des employés.
Base de données : Chaque application utilise sa propre base de données MySQL pour stocker les informations pertinentes.
En suivant cette structure, les deux applications pourront communiquer efficacement et gérer les données des employés de manière centralisée et cohérente.

En résumé, l'application de recrutement doit définir une classe Employee pour transférer les données vers l'application de gestion des employés. Ensuite, vous configurez RestTemplate pour envoyer les données via un point de terminaison REST. De l'autre côté, l'application de gestion des employés définit le point de terminaison pour recevoir et sauvegarder les données. Les fichiers essentiels sont :

RecruitmentRestController.java
Employee.java (dans les deux applications)
AppConfig.java
EmployeeController.java
EmployeeRepository.java
RecruitmentRestControllerTest.java
Ces fichiers assurent la communication correcte entre les deux applications et la gestion des employés.