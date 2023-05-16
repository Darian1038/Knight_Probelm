/* Darian Torres
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Game {
	int[][] board;
	char[] move;
	int size;
	public Game(int size, String fileName) throws FileNotFoundException {
		this.size=size;
		
		//creates appropriate size of the board and initiates
		// the peice to (0,0)
		this.board = new int [size][size];
		this.board [0][0] = 1;
		
		//read in moves
		this.move= readMoves(fileName);
	}
	
	public char[] readMoves(String fileName) throws FileNotFoundException{
		//Finds and creates a scanner for the file we wish to use
		File input= new File(fileName);
		Scanner s = new Scanner(input);
		
		//initiates a character string and scans in each character
		//of the move file
		String moveList = "";
		while (s.hasNextLine() == true){
			moveList += s.nextLine();
		}
		this.move = new char [moveList.length()];
		
		//converts move list from a string into the move array
		for (int i = 0; i<moveList.length();i++) {
			move[i]=moveList.charAt(i);	
		}
		
		//closes document
		s.close();
		
		return move;
	}
	
	//plays the game
	public int play(int winningPlayer) {
		//determines who wins for each code
        if (winningPlayer == 1)
        	return Win1(winningPlayer);
        else
        	return Win2(winningPlayer);  
	}
	
	//algorithm for player 1 to win
	public int Win1(int WinningPlayer) {
		int currentPlayer = 1;
		
		//current move index
		int index = 0;
        char nextMove = move[index];
        
        //keeps current place on board
        int i = 0,j = 0;
		
        //ensures we dont go over the given parameters
		while (board[size-1][size-1] == 0) {
			
			//if moves are legal, use correct moves given
			if(isLegal(nextMove, i, j)) {
				
				//always want player 1 to move on an odd-odd tile
				if(currentPlayer == 1) {
					i=i+((i+1)%2);
					j=j+((j+1)%2);
					board[i][j]=currentPlayer;
				}
				
				//makes player 2 moves according to the String
				if(currentPlayer == 2) {
				
				switch (nextMove) {
				case 'r':
					j++;
					board[i][j] = currentPlayer;
					break;
				case 'b':
					i++;
					board[i][j] = currentPlayer;
					break;
				case 'd':
					i++;
					j++;
					board[i][j] = currentPlayer;
					break;
				}
				}
			}
			
			//moves player in legal direction available
			else {
				if(i>= size-1 && j<size-1) {
					j++;
					board[i][j] = currentPlayer;
				}
				else if(j>=size-1 && i < size-1) {
					i++;
					board[i][j] = currentPlayer;
				}
			}
			
			//breaks while loop soo repective player in the winning tile wins
			if (board[size-1][size-1] != 0) 
				break;
			
			if (currentPlayer == 1)	currentPlayer = 2;
			else currentPlayer = 1;
			nextMove = move[++index];
		}//end while loop
		
		
		
		//prints the whole board and player being returned and checks if the correct player wins
		//printBoard(size);
		//System.out.println(currentPlayer);
		
		//resets board
		for(int k=0; k<size; k++)
			for(int z=0; z<size; z++)
				this.board[k][z]=0;
		return currentPlayer;
	}
	
	//algorithm for player 2 to win
	public int Win2(int WinningPlayer) {
		int currentPlayer = 1;
		
		//current move index
		int index = 0;
        char nextMove = move[index];
        
        //keeps current place on board
        
        int i = 0,j = 0;
		
        //want to use same algorithm as win1 until you are moved into the size-1 dimensions
		while (j<=size-3 && i<=size-3) {
			//determines if a move is legal
			if(isLegal(nextMove, i, j)) {
				//makes same moves like algorithm 1
				if(currentPlayer == 1) {
					i=i+((i+1)%2);
					j=j+((j+1)%2);
					board[i][j]=currentPlayer;
				}
				
				//makes player 2 make moves as read from the document and inputed into the nextMove variable
				if(currentPlayer == 2) {
					
				switch (nextMove) {
				case 'r':
					j++;
					board[i][j] = currentPlayer;
					break;
				case 'b':
					i++;
					board[i][j] = currentPlayer;
					break;
				case 'd':
					i++;
					j++;
					board[i][j] = currentPlayer;
					break;
				}
				}
			}
			
			//breaks just in case code screws up
			if (board[size-1][size-1] != 0) 
				break;
			
			//switches players
			if (currentPlayer == 1)	currentPlayer = 2;
			else currentPlayer = 1;
			
			//iterates to the next move in the string
			nextMove = move[++index];
		}//end while loop
		
		//making winning move for player 2
		//if in second to final row, moves to an odd-even spot
		if(i>5) {
			if(j%2 == 0) {
				i++;
				board[i][j] = currentPlayer;
				currentPlayer = 2;
			}
			else {
				i++;
				j++;
				board[i][j] = currentPlayer;
				currentPlayer = 2;
			}
		}
		//if in second to final column, moves to an even-odd spot
		else if (j>5){
			if(i%2 == 0) {
				j++;
				board[i][j] = currentPlayer;
				currentPlayer = 2;
			}
			else {
				i++;
				j++;
				board[i][j] = currentPlayer;
				currentPlayer = 2;
			}
		}
		
		//makes players move legal moves and switches players until at winning position
		while(board[size-1][size-1] == 0) {
				if(i>= size-1 && j<size-1) {
					j++;
					board[i][j] = currentPlayer;
				}
				else if(j>=size-1 && i < size-1) {
					i++;
					board[i][j] = currentPlayer;
				}
				if (board[size-1][size-1] != 0) 
					break;
			if (currentPlayer == 1)	currentPlayer = 2;
			else currentPlayer = 1;
			nextMove = move[++index];
		}
		
		
		//prints the whole board and player being returned
		//printBoard(size);
		//System.out.println(currentPlayer);
		
		//return winning player once done
		return currentPlayer;
	}
	

	//determines if a move is legal or not
	public boolean isLegal(char nextMove, int i, int j) {
		
		if (nextMove == 'r' && j >= size-1) return false;
		if (nextMove == 'b' && i >= size-1) return false;
		if (nextMove == 'd' && ( i >= size-1 || j >= size-1 ) ) return false;

		return true;
	}
	
	//used to print the board to ensure all moves were correct
	public void printBoard(int size) {

		for(int i=0; i<size; i++) {
            for(int j=0; j<size; j++) {
                if(board[i][j] != 0)
                    System.out.print("|"+board[i][j]);
                else
                    System.out.print("| ");
            }
            System.out.print("|\n");
        }

	}
	
}
