import java.util.Scanner;

public class TicTacToe {
	Scanner sc;
	byte[] board = new byte[] {1,2,3,4,5,6,7,8,9};

	void setX(int pos) { board[pos] = 'X'; }
	byte[] getStCopy() { return board.clone(); }
	byte[] getStCopy(byte[] bArr) { return bArr.clone(); }
	
	int minimax(byte[] stOrig, int depth, boolean max_turn) {
		byte[] st = getStCopy(stOrig); 
		int value;
		if(depth == 0)
			return 0;
		if(max_turn) {
			value = -Integer.MIN_VALUE;
			for(byte b: st) {
				if(b != 'o' && b != 'x') {
					b = 'x';
//					value = Math.max(value, minimax())
				}
			}
		}
		return 0;
	}
//	function minimax(node, depth, maximizingPlayer) is
//    if depth = 0 or node is a terminal node then
//        return the heuristic value of node
//    if maximizingPlayer then
//        value := −∞
//        for each child of node do
//            value := max(value, minimax(child, depth − 1, FALSE))
//        return value
//    else (* minimizing player *)
//        value := +∞
//        for each child of node do
//            value := min(value, minimax(child, depth − 1, TRUE))
//        return value
//(* Initial call *)
//minimax(origin, depth, TRUE)

	void play() {
		drawBoard();
		humanMove();
		drawBoard();
		for(int i = 0; i < 4; i++) {
			humanMove();
			drawBoard();
			
		}
		sc.close();
	}
	
	void humanMove() {
		int pos = -1;
		while(true) {
			System.out.print("Your move: ");
			
			char input = sc.next().charAt(0);
			if(Character.isDigit(input))					//CHECK that char(0) is a digit
				pos = Character.digit(input, 10);
			if(!(pos > 0 && pos < 10))						//CHECK that pos input is from 1 to 9
				System.out.println("Move input must be an integer from 1 to 9.");
			else if(board[pos-1] > 58)						//CHECK that pos-1 isn't taken
				System.out.println("position " + (pos) + " already taken.");
			else
				break;
			sc.reset();
		}
		setX(pos - 1);
		System.out.println();
	}
	
	byte getWinner(byte[] s) {
		byte[] win_rows = new byte[] {s[0],s[1],s[2],  s[3],s[4],s[5],  s[6],s[7],s[8],  s[0],s[3],s[6],
									  s[1],s[4],s[7],  s[2],s[5],s[8],  s[0],s[4],s[8],  s[2],s[4],s[6]};
		for(int i = 0; i < 24; i+=3)
			if(win_rows[i] > 59 && win_rows[i]==win_rows[i+1] && win_rows[i]==win_rows[i+2])
				return win_rows[i];
		return 0;
	}
	
	void drawBoard() {
		System.out.println(" " + ch(board[0]) + " | " + ch(board[1]) + " | " + ch(board[2]) + " ");
		System.out.println("---+---+---");
		System.out.println(" " + ch(board[3]) + " | " + ch(board[4]) + " | " + ch(board[5]) + " ");
		System.out.println("---+---+---");
		System.out.println(" " + ch(board[6]) + " | " + ch(board[7]) + " | " + ch(board[8]) + " ");
	}
	String ch(byte b) { return (b < 58) ? Byte.toString(b) : (b == 88) ? "X" : "O"; }
	public static void main(String[] args) {
		TicTacToe game = new TicTacToe();
		game.sc = new Scanner(System.in);
		game.play();
		game.sc.close();
	}
}


//byte getWinner(byte[] s) {
//	for(int i = 0; i < 3; i++) {										//check a row and col each cycle
//		if(s[3*i] > 57 && s[3*i]==s[3*i+1] && s[3*i]==s[3*i+2])
//			return s[3*i];
//		if(s[i] > 57 && s[i]==s[i+3] && s[i]==s[i+6])
//			return s[i];
//	} 
//	if(s[4] > 59 && ((s[4]==s[0] && s[4]==s[8]) ||(s[4]==s[2] && s[4]==s[6])))	//check diagonals
//		return s[4];
//	return 0;
//}



//byte[] st = new byte[] {
//2,		6,		1,
//1,		6,		3,
//3,		6,		7	};

/*
0	1	2
3	4	5
6	7	8 

0	3	6
1	4	7
2	5	8

0	4	8
2	4	6
*/
