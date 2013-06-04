package net.egork;

public class YetAnotherBoardGame {
	int[] base;
	int[] initial;
	int mask;

    public int minimumMoves(String[] board) {
		int rowCount = board.length;
		int columnCount = board[0].length();
		initial = new int[rowCount];
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (board[i].charAt(j) == 'W')
					initial[i] += 1 << j;
			}
		}
		base = new int[rowCount];
		int answer = Integer.MAX_VALUE;
		mask = (1 << columnCount) - 1;
		for (int i = 0; i < (1 << columnCount); i++) {
			int j = i;
			while (j != 0) {
				makeFirstMove(j);
				answer = Math.min(answer, calculate(i, j));
				j = (j - 1) & i;
			}
			makeFirstMove(0);
			answer = Math.min(answer, calculate(i, j));
			int all = (1 << columnCount) - 1 - i;
			j = all;
			while (j != 0) {
				makeSecondMove(j);
				answer = Math.min(answer, calculate(i, j));
				j = (j - 1) & all;
			}
		}
		if (answer == Integer.MAX_VALUE)
			answer = -1;
		return answer;
    }

	private int calculate(int partition, int startMove) {
		int result = Integer.bitCount(startMove);
		for (int i = 0; i < base.length - 1; i++) {
			if ((base[i] & partition) != 0 && (base[i] & partition) != base[i])
				return Integer.MAX_VALUE;
			if ((base[i] & partition) == 0)
				base[i + 1] ^= base[i];
			base[i + 1] ^= base[i] << 1;
			base[i + 1] ^= base[i] >> 1;
			base[i + 1] &= mask;
			if (i + 2 < base.length)
				base[i + 2] ^= base[i];
			result += Integer.bitCount(base[i]);
		}
		if (base[base.length - 1] != 0)
			return Integer.MAX_VALUE;
		return result;
	}

	private void makeFirstMove(int move) {
		System.arraycopy(initial, 0, base, 0, base.length);
		base[0] ^= move >> 1;
		base[0] ^= move << 1;
		base[0] &= mask;
		if (base.length > 1)
			base[1] ^= move;
	}

	private void makeSecondMove(int move) {
		System.arraycopy(initial, 0, base, 0, base.length);
		base[0] ^= move >> 1;
		base[0] ^= move << 1;
		base[0] ^= move;
		base[0] &= mask;
		if (base.length > 1)
			base[1] ^= move;
	}
}
