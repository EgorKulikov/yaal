package on2013_03.on2013_03_31_2013_TopCoder_Open_Algorithm.TheMagicMatrix;



import net.egork.misc.ArrayUtils;
import net.egork.numbers.IntegerUtils;

import java.util.Arrays;

public class TheMagicMatrix {
	static final long MOD = 1234567891;

    public int find(int n, int[] rows, int[] columns, int[] values) {
		if (n > 10)
			return (int) IntegerUtils.power(10, n * n - 2 * n + 2 - rows.length, MOD);
		int[][] matrix = new int[n][n];
		ArrayUtils.fill(matrix, -1);
		int[] sumRow = new int[n];
		int[] sumColumn = new int[n];
		for (int i = 0; i < rows.length; i++) {
			matrix[rows[i]][columns[i]] = values[i];
			sumRow[rows[i]] += values[i];
			sumColumn[columns[i]] += values[i];
		}
		long answer = 0;
		int[] queue = new int[2 * n];
		boolean[] processed = new boolean[2 * n];
		for (int i = 0; i < 10; i++) {
			Arrays.fill(processed, false);
			boolean good = true;
			int count = 0;
			for (int j = 0; j < 2 * n; j++) {
				if (processed[j])
					continue;
				processed[j] = true;
				count++;
				queue[0] = j;
				int size = 1;
				for (int k = 0; k < size; k++) {
					int line = queue[k];
					if (line < n) {
						for (int l = 0; l < n; l++) {
							if (matrix[line][l] == -1 && !processed[l + n]) {
								queue[size++] = l + n;
								processed[l + n] = true;
							}
						}
					} else {
						for (int l = 0; l < n; l++) {
							if (matrix[l][line - n] == -1 && !processed[l]) {
								queue[size++] = l;
								processed[l] = true;
							}
						}
					}
				}
				int sumRows = 0;
				int sumColumns = 0;
				int countRows = 0;
				int countColumns = 0;
				for (int k = 0; k < size; k++) {
					if (queue[k] < n) {
						sumRows += sumRow[queue[k]];
						countRows++;
					} else {
						sumColumns += sumColumn[queue[k] - n];
						countColumns++;
					}
				}
				sumRows += (10 - i) * countRows;
				sumColumns += (10 - i) * countColumns;
				if (sumRows % 10 != sumColumns % 10) {
					good = false;
					break;
				}
			}
			if (good)
				answer += IntegerUtils.power(10, n * n - 2 * n + count - rows.length, MOD);
		}
		return (int) (answer % MOD);
	}
}
