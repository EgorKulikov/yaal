package net.egork;

public class EllysCheckers {
	int[] winner;
	private int length;

	public String getWinner(String board) {
		length = (board.length() - 1);
		winner = new int[1 << length];
		int startState = 0;
		for (int i = 0; i < board.length() - 1; i++) {
			if (board.charAt(board.length() - 2 - i) == 'o')
				startState += 1 << i;
		}
		return go(startState) == 1 ? "YES" : "NO";
	}

	private int go(int state) {
		if (winner[state] != 0)
			return winner[state];
		winner[state] = -1;
		for (int i = 0; i < length; i++) {
			if ((state >> i & 1) == 0)
				continue;
			int mask = 1 << i;
			if ((state & (mask >> 1)) == 0)
				winner[state] = Math.max(winner[state], -go(state - mask + (mask >> 1)));
			else if ((state & (mask >> 2)) == 1 && (state & (mask >> 3)) == 1)
				winner[state] = Math.max(winner[state], -go(state - mask + (mask >> 3)));
		}
		return winner[state];
	}


}

