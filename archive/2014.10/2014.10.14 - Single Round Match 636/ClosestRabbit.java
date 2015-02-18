package net.egork;

public class ClosestRabbit {
    public double getExpected(String[] board, int r) {
		int empty = 0;
		int rowCount = board.length;
		int columnCount = board[0].length();
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (board[i].charAt(j) == '.') {
					empty++;
				}
			}
		}
		double[][] c = new double[empty + 1][empty + 1];
		for (int i = 0; i <= empty; i++) {
			c[i][0] = 1;
			for (int j = 1; j <= i; j++) {
				c[i][j] = c[i - 1][j - 1] + c[i - 1][j];
			}
		}
		double answer = 0;
		int[][][][] distance = new int[rowCount][columnCount][rowCount][columnCount];
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				for (int k = 0; k < rowCount; k++) {
					for (int l = 0; l < columnCount; l++) {
						distance[i][j][k][l] = (i - k) * (i - k) + (j - l) * (j - l);
					}
				}
			}
		}
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (board[i].charAt(j) == '#') {
					continue;
				}
				for (int k = i; k < rowCount; k++) {
					for (int l = (k == i) ? j + 1 : 0; l < columnCount; l++) {
						if (board[k].charAt(l) == '#') {
							continue;
						}
						int curDist = distance[i][j][k][l];
						int suitable = 0;
						for (int m = 0; m < rowCount; m++) {
							for (int n = 0; n < columnCount; n++) {
								if (board[m].charAt(n) == '.' &&
									(distance[i][j][m][n] > curDist || distance[i][j][m][n] == curDist && (k < m || k == m && l < n)) &&
									(distance[k][l][m][n] > curDist || distance[k][l][m][n] == curDist && (i < m || i == m && j < n)))
								{
									suitable++;
								}
							}
						}
						answer += c[suitable][r - 2];
					}
				}
			}
		}
		return answer / c[empty][r];
    }
}
