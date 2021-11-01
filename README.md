# bowling game kata

An implemetnation of the bowling game kata, in Clojure.

To run the tests:

> make build shell
> $ lein test

## Kata

Ten pin bowling is a pastime enjoyed by a comparitively small population of the world, so here are the rules for how to score a game.

### Ten pin bowling terms

This is our 'ubiquitous language':

* A game is made up of ten frames;
* A frame is made up of one, two, or possibly three bowls;
* A ball is the roll of a bowling ball down the lane by the bowler (player);
* A strike is when the player knocks over all ten pins, and is marked with an 'X';
* A spare is when the player doesn't strike, but knocks over all remeaining balls with their second ball, and is marked with a '/';
* A gutter is when the player rolls the ball into the groove that runs down the side of the bowling alley;
* A foul is where the player steps past the bowling line;
* An open is where there are pins left standing after the player has bowled their second ball.

### Scoring

* A foul or a gutter scores zero;
* A miss - no pins hit - scores zero;
* An open scores the number of pins knocked down by both balls;
* A spare scores the number of pins knocked down (ten) plus the score of the next ball;
* A strike scores the number of pins knocked down (ten) plus the score of the next two balls;
* In the final frame if the player scores a spare, then an extra ball may be rolled in the frame and added to the score;
* In the final frame if the player scores a strike, then an extra two balls may be rolled in the frame and added to the score.

_In this way the final frame (frame 10) is unlike the previous frames because it may contain up to three balls, unlike the maximum of two balls for the other frames._

### Acceptance criteria

A game that looks like the following ten frames (a gutter game):

0 0|0 0|0 0|0 0|0 0|0 0|0 0|0 0|0 0|0 0 - scores 0

A game that looks like the following:

7 /|  X|9 -|  X|  X|8 /|7 -|  X|  X|6 / X - scores 186

A perfect game, look like the following:

  X|  X|  X|  X|  X|  X|  X|  X|  X|X X X - scores 300

## License

See LICENSE file.
