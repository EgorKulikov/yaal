package net.egork;

public class TheMountain {
	public static final int INFINITY = Integer.MAX_VALUE / 4;

	public int minSum(int n, int m, int[] rowIndex, int[] columnIndex, int[] element) {
		int[][] leftUp = new int[n][m];
		int[][] sumLeftUp = build(leftUp, rowIndex, columnIndex, element);
		for (int i = 0; i < rowIndex.length; i++)
			rowIndex[i] = n - 1 - rowIndex[i];
		int[][] leftDown = new int[n][m];
		int[][] sumLeftDown = build(leftDown, rowIndex, columnIndex, element);
		for (int i = 0; i < rowIndex.length; i++)
			columnIndex[i] = m - 1 - columnIndex[i];
		int[][] rightDown = new int[n][m];
		int[][] sumRightDown = build(rightDown, rowIndex, columnIndex, element);
		for (int i = 0; i < rowIndex.length; i++)
			rowIndex[i] = n - 1 - rowIndex[i];
		int[][] rightUp = new int[n][m];
		int[][] sumRightUp = build(rightUp, rowIndex, columnIndex, element);
		int answer = INFINITY;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (sumLeftUp[i][j] == INFINITY || sumLeftDown[n - i - 1][j] == INFINITY
					|| sumRightUp[i][m - j - 1] == INFINITY || sumRightDown[n - i - 1][m - j - 1] == INFINITY)
				{
					continue;
				}
				int current = sumLeftUp[i][j] + sumLeftDown[n - i - 1][j] + sumRightUp[i][m - j - 1] + sumRightDown[n - i - 1][m - j - 1];
				for (int k = 0; k < i; k++) {
					int value = Math.max(leftUp[k][j], rightUp[k][m - j - 1]);
					if (value == INFINITY) {
						current = INFINITY;
						break;
					}
					current += value;
				}
				if (current == INFINITY)
					continue;
				for (int k = i + 1; k < n; k++) {
					int value = Math.max(leftDown[n - k - 1][j], rightDown[n - k - 1][m - j - 1]);
					if (value == INFINITY) {
						current = INFINITY;
						break;
					}
					current += value;
				}
				if (current == INFINITY)
					continue;
				for (int k = 0; k < j; k++) {
					int value = Math.max(leftUp[i][k], leftDown[n - i - 1][k]);
					if (value == INFINITY) {
						current = INFINITY;
						break;
					}
					current += value;
				}
				if (current == INFINITY)
					continue;
				for (int k = j + 1; k < m; k++) {
					int value = Math.max(rightUp[i][m - k - 1], rightDown[n - i - 1][m - k - 1]);
					if (value == INFINITY) {
						current = INFINITY;
						break;
					}
					current += value;
				}
				if (current == INFINITY)
					continue;
				current += Math.max(Math.max(leftUp[i][j], leftDown[n - i - 1][j]), Math.max(rightUp[i][m - j - 1], rightDown[n - i - 1][m - j - 1]));
				answer = Math.min(answer, current);
			}
		}
		if (answer == INFINITY)
			return -1;
		return answer;
    }

	private int[][] build(int[][] matrix, int[] rowIndex, int[] columnIndex, int[] element) {
		for (int i = 0; i < rowIndex.length; i++)
			matrix[rowIndex[i]][columnIndex[i]] = element[i];
		int[][] sum = new int[matrix.length + 1][matrix[0].length + 1];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				int current = 0;
				if (i > 0)
					current = Math.max(current, matrix[i - 1][j]);
				if (j > 0)
					current = Math.max(current, matrix[i][j - 1]);
				current++;
				current = Math.min(current, INFINITY);
				if (matrix[i][j] != 0 && matrix[i][j] < current)
					current = INFINITY;
				matrix[i][j] = Math.max(matrix[i][j], current);
				if (matrix[i][j] == INFINITY)
					sum[i + 1][j + 1] = INFINITY;
				else
					sum[i + 1][j + 1] = sum[i][j + 1] + sum[i + 1][j] - sum[i][j] + matrix[i][j];
				sum[i + 1][j + 1] = Math.min(sum[i + 1][j + 1], INFINITY);
			}
		}
		return sum;
	}
}
