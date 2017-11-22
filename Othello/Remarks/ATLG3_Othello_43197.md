# 43197

## Facade
* Absente

## JavaFX
* `player1 = new Player("White");` a discuter
* Classe `Board` : on accède direction au taille du plateau de jeu sans passer par la Facade
* Ne pas oublier d'enlever `wallsCpt = new WallsCpt(0);` dans la classe `Board`
* `Board` : l'initialisation doit provenir du modèle, la méthode `firstPieces` est une erreur
* `Tile` : pourquoi ne pas avoir utilisé la clase `Circle` aulieu de `Ellipse` ?
* `Tile` : la méthode `switchColor` n'est pas utilisée       
* `Tile` : `shapeSizeFact` le nom de la constante doit s'écrire en majuscule     
* `WallsCpt` : le décompte des murs doit se faire dans le modèle et pas via la méthode `addWall`

## Console
* ok

## Model

### Initialisation du jeu
* ok

### Recherche des coups possibles
* Ok

### Captures des pièces adverses
* ok

### Gestion des scores
* ok

### Remarques générales
* Les données affichées dans la vue JavaFX doivent provenir du modèle
* La visibilité de la majorité des méthodes est `public`. Réfléchir à la visibilité adéquate.
* Utilisation de clone a la place des copies

### Game
* `accessibles = new LinkedList<>();` : pourquoi avoir utilisé une liste chaînée ?
* `startAgain` : pourquoi la méthode est `final` ?
* `canPlay` : attention la vue ordonne au modèle de changer de joueur
* `Players` : bonne idée de créer cette classe. Pour aller plus loin tu peux regarder ce qu'est un Iterator.
* `getAccessibles` : afin de ne pas passer par un clone, on peut envisager `Collections.unmodifiableList(accessibles);`

### Board
* L'utilisation de clone est selon moi déconseillée http://www.javapractices.com/topic/TopicAction.do?Id=71

### Coordinates
* `increment(savePos, dir)` : pourquoi une méthode static ?

### Rack
* quel est l'utilité de la classe `Rack` ?
