public class Notes {
	Notes() {
		int test = minimax(new byte[9], 1, true);
	}
	
	
	
	
	int minimax(byte[] node, int depth, boolean maxTurn) {
		if(depth == 0) //or node is a terminal node then
			return 9999; //the heuristic value of node
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



}
