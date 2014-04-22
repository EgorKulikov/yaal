package net.egork;

public class TorusSailing {
    public double expectedTime(int N, int M, int goalX, int goalY) {
		int total = N + M;
		double[][] c = new double[total + 1][total + 1];
		double[] pow = new double[total + 1];
		pow[0] = 1;
		for (int i = 1; i <= total; i++)
			pow[i] = pow[i - 1] * 2;
		for (int i = 0; i <= total; i++) {
			c[i][0] = 1;
			for (int j = 1; j <= i; j++)
				c[i][j] = c[i - 1][j - 1] + c[i - 1][j];
		}
		double[][] mat = new double[total - 1][total];
		int[] row = new int[total - 1];
		int[] column = new int[total - 1];
		for (int i = 1; i < N; i++)
			row[i] = i;
		for (int i = 1; i < M; i++)
			column[i - 1 + N] = i;
		for (int i = 0; i < total - 1; i++) {
			mat[i][i] = 1;
			if (row[i] == 0) {
				int targetRow = 0;
				int targetColumn = (column[i] + 1) % M;
				mat[i][total - 1] += .5;
				for (int j = 0; j < total - 1; j++) {
					if (row[j] == targetRow && column[j] == targetColumn) {
						mat[i][j] -= .5;
						break;
					}
				}
			} else {
				int startRow = row[i];
				int startColumn = 1;
				for (int j = row[i]; j < N - 1; j++) {
					int endRow = j;
					int endColumn = M - 1;
					double p = c[endRow - startRow + endColumn - startColumn][endRow - startRow] / pow[endRow - startRow + endColumn - startColumn] / 4;
					int targetRow = j;
					int targetColumn = 0;
					mat[i][total - 1] += p * (endRow - startRow + endColumn - startColumn + 2);
					for (int k = 0; k < total - 1; k++) {
						if (targetRow == row[k] && targetColumn == column[k]) {
							mat[i][k] -= p;
							break;
						}
					}
				}
				for (int j = 1; j < M - 1; j++) {
					int endRow = N - 1;
					int endColumn = j;
					double p = c[endRow - startRow + endColumn - startColumn][endRow - startRow] / pow[endRow - startRow + endColumn - startColumn] / 4;
					int targetRow = 0;
					int targetColumn = j;
					mat[i][total - 1] += p * (endRow - startRow + endColumn - startColumn + 2);
					for (int k = 0; k < total - 1; k++) {
						if (targetRow == row[k] && targetColumn == column[k]) {
							mat[i][k] -= p;
							break;
						}
					}
				}
				int endRow = N - 1;
				int endColumn = M - 1;
				double p = c[endRow - startRow + endColumn - startColumn][endRow - startRow] / pow[endRow - startRow + endColumn - startColumn] / 2;
				mat[i][total - 1] += p * (endRow - startRow + endColumn - startColumn + 1);
			}
			if (column[i] == 0) {
				int targetRow = (row[i] + 1) % N;
				int targetColumn = 0;
				mat[i][total - 1] += .5;
				for (int j = 0; j < total - 1; j++) {
					if (row[j] == targetRow && column[j] == targetColumn) {
						mat[i][j] -= .5;
						break;
					}
				}
			} else {
				int startRow = 1;
				int startColumn = column[i];
				for (int j = 1; j < N - 1; j++) {
					int endRow = j;
					int endColumn = M - 1;
					double p = c[endRow - startRow + endColumn - startColumn][endRow - startRow] / pow[endRow - startRow + endColumn - startColumn] / 4;
					int targetRow = j;
					int targetColumn = 0;
					mat[i][total - 1] += p * (endRow - startRow + endColumn - startColumn + 2);
					for (int k = 0; k < total - 1; k++) {
						if (targetRow == row[k] && targetColumn == column[k]) {
							mat[i][k] -= p;
							break;
						}
					}
				}
				for (int j = column[i]; j < M - 1; j++) {
					int endRow = N - 1;
					int endColumn = j;
					double p = c[endRow - startRow + endColumn - startColumn][endRow - startRow] / pow[endRow - startRow + endColumn - startColumn] / 4;
					int targetRow = 0;
					int targetColumn = j;
					mat[i][total - 1] += p * (endRow - startRow + endColumn - startColumn + 2);
					for (int k = 0; k < total - 1; k++) {
						if (targetRow == row[k] && targetColumn == column[k]) {
							mat[i][k] -= p;
							break;
						}
					}
				}
				int endRow = N - 1;
				int endColumn = M - 1;
				double p = c[endRow - startRow + endColumn - startColumn][endRow - startRow] / pow[endRow - startRow + endColumn - startColumn] / 2;
				mat[i][total - 1] += p * (endRow - startRow + endColumn - startColumn + 1);
			}
		}
		for (int i = 0; i < total - 1; i++) {
			double max = -1;
			int at = -1;
			for (int j = i; j < total - 1; j++) {
				if (Math.abs(mat[j][i]) > max) {
					max = Math.abs(mat[j][i]);
					at = j;
				}
			}
			for (int j = i; j < total; j++) {
				double temp = mat[i][j];
				mat[i][j] = mat[at][j];
				mat[at][j] = temp;
			}
			for (int j = total - 1; j >= i; j--)
				mat[i][j] /= mat[i][i];
			for (int j = 0; j < total - 1; j++) {
				if (i == j)
					continue;
				for (int k = total - 1; k >= i; k--)
					mat[j][k] -= mat[i][k] * mat[j][i];
			}
		}
		double answer = (goalX + goalY) * c[goalX + goalY][goalX] / pow[goalX + goalY];
		int startRow = 0;
		int startColumn = 0;
		for (int i = 0; i < goalY; i++) {
			int targetRow = 0;
			int targetColumn = M - 1 - (goalY - i);
			int endRow = goalX;
			int endColumn = i;
			double p = c[endRow - startRow + endColumn - startColumn][endRow - startRow] / pow[endRow - startRow + endColumn - startColumn] / 2;
			answer += p * (endRow - startRow + endColumn - startColumn + 1);
			for (int j = 0; j < total - 1; j++) {
				if (row[j] == targetRow && column[j] == targetColumn) {
					answer += p * mat[j][total - 1];
					break;
				}
			}
		}
		for (int i = 0; i < goalX; i++) {
			int targetRow = N - 1 - (goalX - i);
			int targetColumn = 0;
			int endRow = i;
			int endColumn = goalY;
			double p = c[endRow - startRow + endColumn - startColumn][endRow - startRow] / pow[endRow - startRow + endColumn - startColumn] / 2;
			answer += p * (endRow - startRow + endColumn - startColumn + 1);
			for (int j = 0; j < total - 1; j++) {
				if (row[j] == targetRow && column[j] == targetColumn) {
					answer += p * mat[j][total - 1];
					break;
				}
			}
		}
		return answer;
    }
}
