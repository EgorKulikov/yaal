package net.egork;

public class DropCoins {
	public int getMinimum(String[] board, int K) {
		int rowCount = board.length;
		int columnCount = board[0].length();
		int answer = Integer.MAX_VALUE;
		for (int i = 0; i < rowCount; i++) {
			for (int j = i; j < rowCount; j++) {
				for (int k = 0; k < columnCount; k++) {
					int current = 0;
					for (int l = k; l < columnCount; l++) {
						for (int m = i; m <= j; m++) {
							if (board[m].charAt(l) == 'o')
								current++;
						}
						if (current == K) {
							answer = Math.min(answer, i + rowCount - j - 1 + Math.min(i, rowCount - j - 1) +
								k + columnCount - l - 1 + Math.min(k, columnCount - l - 1));
						}
					}
				}
			}
		}
		if (answer == Integer.MAX_VALUE)
			answer = -1;
		return answer;
	}


}

