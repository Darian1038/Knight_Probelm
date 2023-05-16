This repository contains code that implements a winning strategy game called Knight's Move. The game is played between two players who take turns moving a Knight chess piece on an 8x8 board. The objective of the game is to reach the bottom right corner of the board. The implementation includes strategies for both players to ensure one of them always wins.

This Java program takes two inputs: the size of the board and a text file containing the moves for player 2. The board size determines the dimensions of the 8 x 8 board, and the text file contains a sequence of moves represented by 'r' (right), 'b' (down), and 'd' (diagonal). 

The program reads the moves from the text file and stores them in an array. Then, it simulates a round of the game based on the chosen player (1 or 2). Player 1 always goes first. The program determines the appropriate strategy for each player to always win the game. 

The output of the program is an integer representing the winner of the game (1 for player 1 and 2 for player 2). The program uses the implemented strategies to ensure that the chosen player wins the game.