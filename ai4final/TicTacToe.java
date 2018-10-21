import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
	Random r = new Random();
	Scanner sc = new Scanner(System.in);
	int moves_left = 9;
	byte[] board = new byte[] {1,2,3,4,5,6,7,8,9};
	int[] move_history = new int[9];
	boolean show_board = true, show_final_board = true;

	void play() {
		drawBoard();
		humanMove();
		while(moves_left > 0 && getWinner(board) == 0) {
			aiMove();
			if(getWinner(board) != 0)
				break;
			humanMove();
		}
		results();
		sc.close();
	}
	
	void aiMove() {
		int move = minimax(copyBoard(board), moves_left, true, true);
		makeMove(board, move, false);
	}
	
	int minimax(byte[] node, int depth, boolean maxTurn, boolean top) {
		int best_move = -1;
		int score = getWinner(node);
		if(score != 0 || depth == 0)
			return score;
		if(maxTurn) {
			int value = Integer.MIN_VALUE;
			for(int i = 0; i < 9; i++) 	// each child of node do
				if(node[i] < 59) {
					byte[] tmp = copyBoard(node);
					tmp[i] = 'O';
					int mm_value = minimax(tmp, depth-1, false, false);
					if(mm_value > value) {
						value = mm_value;
						best_move = i;
					}
				}
			if(top)
				return best_move;
			else
				return value;
		}
		else {
			int value = Integer.MAX_VALUE;
			for(int i = 0; i < 9; i++)
				if(node[i] < 59) {
					byte[] tmp = copyBoard(node);
					tmp[i] = 'X';
					int mm_value = minimax(tmp, depth-1, true, false);
					if(mm_value < value) {
						value = mm_value;
						best_move = i;
					}
				}
			return value;
		}
	}
	
	void humanMove() {
		int pos = -1;
		while(true) {
			System.out.print("Your move: ");
			char input = sc.next().charAt(0);
			if(Character.isDigit(input))					//CHECK that char(0) is a digit
				pos = Character.digit(input, 10);
			if(!(pos > 0 && pos < 10)) System.out.println("Move input must be an integer from 1 to 9.");
			else if(board[pos-1] > 58)	System.out.println("position " + (pos) + " already taken.");
			else break;
			sc.reset();
		}
		makeMove(board, pos - 1, true);
	}
	
	void makeMove(byte[] a_board, int pos, boolean isHuman) { 
		a_board[pos] = (isHuman) ? (byte)'X' : (byte)'O';
		move_history[9-moves_left] = pos;
		moves_left--;
		drawBoard();
	}
	
	int getWinner(byte[] s) {
		byte[] win_rows = new byte[] {s[0],s[1],s[2],  s[3],s[4],s[5],  s[6],s[7],s[8],  s[0],s[3],s[6],
									  s[1],s[4],s[7],  s[2],s[5],s[8],  s[0],s[4],s[8],  s[2],s[4],s[6]};
		for(int i = 0; i < 24; i+=3)
			if(win_rows[i] > 59 && win_rows[i]==win_rows[i+1] && win_rows[i]==win_rows[i+2])
				return (win_rows[i] == 'O') ? 1 : -1;
		return 0;
	}
	
	void results() {
		int winner = getWinner(board);
		show_board = true;
		System.out.println((winner == 0) ? "TIE GAME" : (winner == 1) ? "AI WINS" : "HUMAN WINS");
		showFinal();
		reset();
	}
	
	TicTacToe() {
		show_board = true;
		show_final_board = true;
	}
	
	public static void main(String[] args) {
//		(new TicTacToe()).play();
		(new TicTacToe()).testRandomHuman(1);
//		(new TicTacToe()).testOptimalHuman(1);
	}

	
/* UTILITIES==================================== */
	char ch(byte b) { 
		if(b > 47 && b < 58)
			return Byte.toString(b).charAt(0);
		else
			return (char)b;
	}
	
	
//	String ch(byte b) { 
//		System.out.println(b);
////		if(b > 47 && b < 58) {
//		if(b < 10) {
////			System.out.println(Byte.toString(b).charAt(0) + Byte.toString(b).charAt(0));
////			return Byte.toString(b).charAt(0);
//			return Byte.toString(b);
//		}
////			return b;
//		else {
//			return String.valueOf(b);
////			return " ";
////			return (char)b;
//		}
//		
//	}
	
	byte[] copyBoard(byte[] bArr) { return bArr.clone(); }
	
	void drawBoard() {
		if(show_board) {
			System.out.println("\nLEFT=" + moves_left + "     ");
			System.out.println(" " + ch(board[0]) + " | " + ch(board[1]) + " | " + ch(board[2]) + "\n---+---+---");
			System.out.println(" " + ch(board[3]) + " | " + ch(board[4]) + " | " + ch(board[5]) + "\n---+---+---");
			System.out.println(" " + ch(board[6]) + " | " + ch(board[7]) + " | " + ch(board[8]) + "\n---+---+---");
		}
	}
	void showFinal() {
		if(show_final_board) {
			int num = 9 - moves_left;
			String[] str = new String[] {"", "", "", "", "", ""};
			byte[] tmp_board = new byte[] {(byte)' ',(byte)' ',(byte)' ',(byte)' ',(byte)' ',(byte)' ',(byte)' ',(byte)' ',(byte)' '};
			boolean is_human = true;
			for(int i = 0; i < num; i++) {
				int space_num = move_history[i];
				char piece = (is_human) ? 'X' : 'O';
				tmp_board[space_num] = (byte)piece;
				
				str[0] += "LEFT=" + (8-i) + "       ";
				str[1] += " " + ch(tmp_board[0]) + " | " + ch(tmp_board[1]) + " | " + ch(tmp_board[2]) + "   ";
				str[2] += "---+---+---  ";
				str[3] += " " + ch(tmp_board[3]) + " | " + ch(tmp_board[4]) + " | " + ch(tmp_board[5]) + "   ";
				str[4] += "---+---+---  ";
				str[5] += " " + ch(tmp_board[6]) + " | " + ch(tmp_board[7]) + " | " + ch(tmp_board[8]) + "   ";
				is_human = !is_human;
				
				
				
			}
			System.out.println();
			for(int i = 0; i < 6; i++)
				System.out.println(str[i]);
			System.out.println();
		}	
	}
	
	void reset() {
		moves_left = 9;
		board = new byte[] {1,2,3,4,5,6,7,8,9};
		move_history = new int[9];
	}

/* TESTING===============================================*/
	
	/* RANDOM HUMAN TESTING */
	void testRandomHuman(int num_of_games) {
//		show_final = false;
		for(int i = 0; i < num_of_games; i++) {
			randomHumanMove();
			while(moves_left > 0 && getWinner(board) == 0) {
				aiMove();
				if(getWinner(board) != 0)
					break;
				randomHumanMove();
			}
			results();
		}
		sc.close();
	}
	
	void randomHumanMove() {
		int pos = r.nextInt(9);
		while(board[pos] > 58) {
			pos = r.nextInt(9);
		}
		makeMove(board, pos, true);
	}
	
	/* OPTIMAL HUMAN TESTING  */
	void testOptimalHuman(int num_of_games) {
		for(int i = 0; i < num_of_games; i++) {
			optimalHumanMove();
			while(moves_left > 0 && getWinner(board) == 0) {
				aiMove();
				if(getWinner(board) != 0)
					break;
				optimalHumanMove();
			}
			results();
		}
		sc.close();
	}
	
	void optimalHumanMove() {
		int move = minimax(copyBoard(board), moves_left, true, true);
		makeMove(board, move, true);
	}
}