# Scrabble

## Overview

Scrabble is a word game in which players pick up random letter tiles from a bag to fill the seven places on their "rack". They then attempt to place the tiles from their rack onto the board to make words. (See http://en.wikipedia.org/wiki/Scrabble)

In the full game, there are 2 - 4 players and each word placed must build on the words that are already on the board. Secrecy is critical to the game - no player should be able to see the other player's tiles. Implementing the full game would therefore require a networked game in which each player could see the board and their own rack of tiles, but not the racks of the other players. Instead, you will implement a solitaire version of the game for one player who can practice Scrabble by making words on the board.

The English version of Scrabble has 100 tiles, each with a letter and score (except for the two blank tiles, which have no letter and no score). The number of tiles for each letter and their scores is given below. The board is a 15 x 15 grid. Some of the squares are "special", marked with score multipliers. There are four different multipliers, each corresponding to a different colour:

- `Triple word` is	red
- `Double word`	is pink
- `Triple letter`	is blue
- `Double letter`	is light blue

The layout of the special squares can be seen on the wikipedia page above.

The rack can hold up to seven tiles. The player typically rearranges the order of the tiles while trying to form words that they can place on the board below. For each turn, the player can move the tiles around on the rack and can move them to the board. In this solitaire version, the player can move the tiles around on the board, and can move them back to the rack, until they are satisfied with the word they have formed and "commit" to the word. At that point, the tiles they have put on the board are "fixed" and cannot be moved again in the game. When the player commits, their rack is filled up with random tiles from the bag (unless the bag has run out).

## Compiling Java files using Eclipse IDE

1. Download this repository as ZIP
2. Create new `Java Project` in `Eclipse`
3. Right click on your `Java Project` --> `Import`
4. Choose `General` --> `Archive File`
5. Put directory where you downloaded ZIP in `From archive file`
6. Put `ProjectName/src` in `Into folder`
7. Click `Finish`
8. Move the `tiles` directory and `tile-bag.txt` from `{ProjectName}/src` to the root of your `Java Project` i.e. `{ProjectName}`

### Linking the UI Library

9. Right click on your `Java Project` --> `Build Path` --> `Add External Archives`
10. Select `ecs100.jar` and link it to the project. That JAR will be in the directory where you downloaded ZIP

## Running the program

1. Right click on your `Java Project` --> `Run As` --> `Java Application` --> `Scrabble Solitaire`
2. `Commit` makes the tile placements permamnent
