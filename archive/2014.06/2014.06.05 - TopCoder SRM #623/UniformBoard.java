package net.egork;

public class UniformBoard {
    public int getBoard(String[] board, int K) {
		char[][] map = new char[board.length][];
		for (int i = 0; i < board.length; i++) {
			map[i] = board[i].toCharArray();
		}
		boolean full = true;
		int apples = 0;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] == '.')
					full = false;
				if (map[i][j] == 'A')
					apples++;
			}
		}
		int answer = 0;
		for (int i = 0; i < map.length; i++) {
			for (int j = i; j < map.length; j++) {
				for (int k = 0; k < map[i].length; k++) {
					for (int l = k; l < map[i].length; l++) {
						if ((j - i + 1) * (l - k + 1) > apples)
							break;
						boolean allApples = true;
						int required = 0;
						for (int m = i; m <= j; m++) {
							for (int n = k; n <= l; n++) {
								if (map[m][n] != 'A')
									allApples = false;
								if (map[m][n] == '.')
									required++;
								if (map[m][n] == 'P')
									required += 2;
							}
						}
						if (allApples || !full && required <= K)
							answer = Math.max(answer, (j - i + 1) * (l - k + 1));
					}
				}
			}
		}
		return answer;
    }
}
