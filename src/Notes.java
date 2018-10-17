public class Notes {
	Notes() {
		int test = minimax(new byte[9], 1, true);
	}
	
	
	
	
	int minimax(byte[] node, int depth, boolean maxTurn) {
		if(depth == 0)
			return getWinner(node);
		if(maxTurn) {
			int value = Integer.MIN_VALUE;
			for(byte bt: node) 	// each child of node do
//				value = max(value, minimax(child, depth − 1, false));
				value = Math.max(value, minimax(node, depth-1, false));
			return value;
		}
		else {
			int value = Integer.MAX_VALUE;
			for(byte bt: node)
//				value = Math.min(value, minimax(child, −1, true));
				value = Math.min(value, minimax(node, depth-1, true));
			return value;
		}
	}
	
	
	int getWinner(byte[] s) {
		byte[] win_rows = new byte[] {s[0],s[1],s[2],  s[3],s[4],s[5],  s[6],s[7],s[8],  s[0],s[3],s[6],
									  s[1],s[4],s[7],  s[2],s[5],s[8],  s[0],s[4],s[8],  s[2],s[4],s[6]};
		for(int i = 0; i < 24; i+=3)
			if(win_rows[i] > 59 && win_rows[i]==win_rows[i+1] && win_rows[i]==win_rows[i+2])
				return (win_rows[i] == 'O') ? 1 : -1;
		return 0;
	}


}
