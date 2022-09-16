import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

	static String[] board;
	static String turn;

	//Checking all scenarios for a winner
	static String checkWinner() {
		for (int a = 0; a < 8; a++) {
			String line = null;

			switch (a) {
			case 0:
				line = board[0] + board[1] + board[2];
				break;
			case 1:
				line = board[3] + board[4] + board[5];
				break;
			case 2:
				line = board[6] + board[7] + board[8];
				break;
			case 3:
				line = board[0] + board[3] + board[6];
				break;
			case 4:
				line = board[1] + board[4] + board[7];
				break;
			case 5:
				line = board[2] + board[5] + board[8];
				break;
			case 6:
				line = board[0] + board[4] + board[8];
				break;
			case 7:
				line = board[2] + board[4] + board[6];
				break;
			}

			// For X winner
			if (line.equals("XXX")) 
				return "X";
			

			// For O winner
			else if (line.equals("OOO")) 
				return "O";
			
		}

		//Checking for draw
		for (int a = 0; a < 9; a++) {
			if (Arrays.asList(board).contains(String.valueOf(a + 1))) 
				break;
			 else if (a == 8) 
				return "draw";
		}

		// To enter the X Or O at the exact place on board.
		System.out.println(turn + "'s turn: enter a slot number to place " + turn + " in:");
		return null;
	}

	//Printing board function
	static void printBoard() {

		System.out.println("|---|---|---|" + "\n" + 
						   "| " + board[0] + " | " + board[1] + " | " + board[2] + " |" + "\n" +
						   "|-----------|" + "\n" + 
						   "| " + board[3] + " | " + board[4] + " | " + board[5] + " |" + "\n" +
						   "|-----------|" + "\n" + 
						   "| " + board[6] + " | " + board[7] + " | " + board[8] + " |" + "\n" + 
						   "|---|---|---|");}

	//Run Game function
	public static void runGame() {
		Random rand = new Random();
		int StartingPlayer = rand.nextInt(2);

		// Game logic:
		Scanner in = new Scanner(System.in);
		board = new String[9];

		if (StartingPlayer % 2 == 1) 
			turn = "X";
		else 
			turn = "O";
		

		String winner = null;

		for (int a = 0; a < 9; a++)
			board[a] = String.valueOf(a + 1);
		

		System.out.println("Let's play Tic Tac Toe!");
		printBoard();
		System.out.println(turn + " starts: Enter a slot number to place " + turn + " in:");

		while (winner == null) {
			int numInput;

			//If not in range from 1 to 9: error "Invalid input."
			try {
				numInput = in.nextInt();
				if (!(numInput > 0 && numInput <= 9)) {
					System.out.println("Invalid input: re-enter slot number:");
					continue;
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid input: re-enter slot number:");
				continue;
			}

			//Logic to decide the turn.
			if (board[numInput - 1].equals(String.valueOf(numInput))) {
				board[numInput - 1] = turn;

				if (turn.equals("X")) 
					turn = "O";
				 else 
					turn = "X";
				
				printBoard();
				winner = checkWinner();
			} else {
				System.out.println("Slot occupied: re-enter slot number:");
			}
		}

		//Prints draw
		if (winner.equalsIgnoreCase("draw")) 
			System.out.println("It's a draw! Thank you for playing.");
		//Congratulations to winner message
		else 
			System.out.println("Congratulations! " + winner + "'s have won! Thank you for playing.");
		
	}

	//Instantiating game and checks if player wants to play again
	public static void main(String[] args) {
		boolean isValid;
		Scanner userReply = new Scanner(System.in);
		do {
			isValid = true;
			runGame();
			boolean playerResponse = true;
			while (playerResponse) {
				System.out.println("Do you want to play again? Answer with (Y/N)");
				String answer = userReply.nextLine().toLowerCase();
				if ("y".equals(answer) || "yes".equals(answer)) {
					isValid = true;
					playerResponse = false;
				} else if ("n".equals(answer) || "no".equals(answer)) {
					isValid = false;
					System.out.println("Game exit");
					playerResponse = false;
				} else {
					System.out.println("Invalid input");
					playerResponse = true;
				}
			}
		} while (isValid);
		userReply.close();
	}
}