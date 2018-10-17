import java.util.Scanner;

public class TicTacToe {
	Scanner sc;
	int moves_left = 9;
	byte[] board = new byte[] {1,2,3,4,5,6,7,8,9};

	void play() {
		drawBoard();
		humanMove();
		for(int i = 0; i < 4; i++) {
			aiMove();
			humanMove();
		}
		if(getWinner(board) == 0)
			System.out.println("tie");
		sc.close();
		
	}
	
	void aiMove() {
		drawBoard();
		setO(minimax(getStCopy(), moves_left, true, true));
	}
	
	int minimax(byte[] node, int depth, boolean maxTurn, boolean top) {
		int best_move = -1;
		if(depth == 0)
			return getWinner(node);
		if(maxTurn) {
			int value = Integer.MIN_VALUE;
			for(int i = 0; i < 9; i++) 	// each child of node do
				if(board[i] < 59) {
					int mm_value = minimax(node, depth-1, false, false);
					if(mm_value > value) {
						value = mm_value;
						best_move = i;
					}
					value = Math.max(value, minimax(node, depth-1, false, false));
				}
			if(top)
				return best_move;
			else
				return value;
		}
		else {
			int value = Integer.MAX_VALUE;
			for(int i = 0; i < 9; i++)
				if(board[i] < 59) {
					int mm_value = minimax(node, depth-1, true, false);
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
			if(!(pos > 0 && pos < 10))						//CHECK pos input range: 1 to 9
				System.out.println("Move input must be an integer from 1 to 9.");
			else if(board[pos-1] > 58)						//CHECK that pos-1 isn't taken
				System.out.println("position " + (pos) + " already taken.");
			else
				break;
			sc.reset();
		}
		setX(pos - 1);
		drawBoard();
	}
	
	void setX(int pos) { board[pos] = 'X'; moves_left--;}
	void setO(int pos) { board[pos] = 'O'; moves_left--;}
	
	byte[] getStCopy() { return board.clone(); }
	byte[] getStCopy(byte[] bArr) { return bArr.clone(); }
	int getWinner(byte[] s) {
		byte[] win_rows = new byte[] {s[0],s[1],s[2],  s[3],s[4],s[5],  s[6],s[7],s[8],  s[0],s[3],s[6],
									  s[1],s[4],s[7],  s[2],s[5],s[8],  s[0],s[4],s[8],  s[2],s[4],s[6]};
		for(int i = 0; i < 24; i+=3)
			if(win_rows[i] > 59 && win_rows[i]==win_rows[i+1] && win_rows[i]==win_rows[i+2])
				return (win_rows[i] == 'O') ? 1 : -1;
		return 0;
	}
	
	void drawBoard() {
		System.out.println();
		System.out.println("*****MOVES MADE=" + moves_left);
		System.out.println(" " + ch(board[0]) + " | " + ch(board[1]) + " | " + ch(board[2]) + " ");
		System.out.println("---+---+---");
		System.out.println(" " + ch(board[3]) + " | " + ch(board[4]) + " | " + ch(board[5]) + " ");
		System.out.println("---+---+---");
		System.out.println(" " + ch(board[6]) + " | " + ch(board[7]) + " | " + ch(board[8]) + " ");
	}
	char ch(byte b) { return (b < 58) ? Byte.toString(b).charAt(0) : (char)b; }

	TicTacToe() { sc = new Scanner(System.in);}
	public static void main(String[] args) {
		TicTacToe game = new TicTacToe();
		game.play();
	}
}