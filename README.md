## Fonctionnalités réalisées

1. Lecture aléatoire d'un mot à deviner à partir d'un fichier texte donné à la racine du projet.
2. Affichage graphique de l'interface du jeu à l'aide de Swing.
3. Affichage graphique du pendu qui évolue en fonction des erreurs du joueur.
4. Affichage graphique des lettres déjà proposées par le joueur.
5. Affichage (ou non) de la définition (niveau de difficulté).
6. Utilisation (ou non) d'un timer (niveau de difficulté).
7. Gestion des entrées utilisateur pour proposer des lettres.
8. Vérification de la validité des entrées utilisateur (lettre de l'alphabet uniquement).
9. Gestion du décompte des tentatives restantes.
10. Gestion de la victoire ou de la défaite du joueur.
11. Possibilité de rejouer une partie après la fin d'une partie

## Conception
J'ai suivi le pattern MVC :
- Modèle qui contient le mot à deviner, avec sa version censurée (mise à jour pour conserver l'état du jeu) et sa version non censurée
- Vue qui contient les différents éléments à afficher sur la fenêtre
- Contrôleur qui traite les actions de l'utilisateur (entrée d'une lettre) et récupère aussi les mots/définitions dans le fichier texte afin de créer l'entité mot dans le modèle
