import net.egork.io.IOUtils;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;

public class TaskB implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		System.err.println(testNumber);
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		in.readInt();
		char[][] mass = IOUtils.readTable(in, rowCount, columnCount);
		int[][] table = new int[rowCount][columnCount];
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++)
				table[i][j] = mass[i][j] - '0';
		}
		long[][] sums = new long[rowCount + 1][columnCount + 1];
		for (int i = 0; i < rowCount; i++) {
			int sum = 0;
			for (int j = 0; j < columnCount; j++) {
				sum += table[i][j];
				sums[i + 1][j + 1] = sums[i][j + 1] + sum;
			}
		}
		long[][] leftSums = new long[rowCount + 1][columnCount + 1];
		for (int i = 0; i < rowCount; i++) {
			int sum = 0;
			for (int j = 0; j < columnCount; j++) {
				sum += sums[i + 1][j + 1];
				leftSums[i + 1][j + 1] = sum;
			}
		}
		long[][] rightSums = new long[rowCount + 1][columnCount + 1];
		for (int i = 0; i < rowCount; i++) {
			int sum = 0;
			for (int j = columnCount - 1; j >= 0; j--) {
				sum += sums[i + 1][columnCount] - sums[i + 1][j];
				rightSums[i + 1][j] = sum;
			}
		}
		long[][] upSums = new long[rowCount + 1][columnCount + 1];
		for (int j = 0; j < columnCount; j++) {
			int sum = 0;
			for (int i = 0; i < rowCount; i++) {
				sum += sums[i + 1][j + 1];
				upSums[i + 1][j + 1] = sum;
			}
		}
		long[][] downSums = new long[rowCount + 1][columnCount + 1];
		for (int j = 0; j < columnCount; j++) {
			int sum = 0;
			for (int i = rowCount - 1; i >= 0; i--) {
				sum += sums[rowCount][j + 1] - sums[i][j + 1];
				downSums[i][j + 1] = sum;
			}
		}
		out.print("Case #" + testNumber + ": ");
		for (int i = Math.min(rowCount, columnCount); i >= 3; i--) {
			if (i % 2 == 1) {
				for (int j = 0; i + j <= rowCount; j++) {
					for (int k = 0; i + k <= columnCount; k++) {
						long up = get(upSums, j, j + i / 2, k, k + i) - (get(sums, 0, j, k, k + i) + table[j][k] + table[j][k + i - 1]) * (i / 2);
						long down = Math.abs(get(downSums, j + i - i / 2, j + i, k, k + i)) - (get(sums, j + i, rowCount, k, k + i) + table[j + i - 1][k] + table[j + i - 1][k + i - 1]) * (i / 2);
						if (up != down)
							continue;
						long left = get(leftSums, j, j + i, k, k + i / 2) - (get(sums, j, j + i, 0, k) + table[j][k] + table[j + i - 1][k]) * (i / 2);
						long right = Math.abs(get(rightSums, j, j + i, k + i - i / 2, k + i)) - (get(sums, j, j + i, k + i, columnCount) + table[j][k + i - 1] + table[j + i - 1][k + i - 1]) * (i / 2);
						if (left != right)
							continue;
						out.println(i);
						return;
					}
				}
			} else {
				for (int j = 0; i + j <= rowCount; j++) {
					for (int k = 0; i + k <= columnCount; k++) {
						long up = get(upSums, j, j + i / 2, k, k + i) - (get(sums, 0, j, k, k + i) + table[j][k] + table[j][k + i - 1]) * (i / 2);
						up *= 2;
						up -= get(sums, j, j + i / 2, k, k + i) - table[j][k] - table[j][k + i - 1];
						long down = Math.abs(get(downSums, j + i - i / 2, j + i, k, k + i)) - (get(sums, j + i, rowCount, k, k + i) + table[j + i - 1][k] + table[j + i - 1][k + i - 1]) * (i / 2);
						down *= 2;
						down -= get(sums, j + i - i / 2, j + i, k, k + i) - table[j + i - 1][k] - table[j + i - 1][k + i - 1];
						if (up != down)
							continue;
						long left = get(leftSums, j, j + i, k, k + i / 2) - (get(sums, j, j + i, 0, k) + table[j][k] + table[j + i - 1][k]) * (i / 2);
						left *= 2;
						left -= get(sums, j, j + i, k, k + i / 2) - table[j][k] - table[j + i - 1][k];
						long right = Math.abs(get(rightSums, j, j + i, k + i - i / 2, k + i)) - (get(sums, j, j + i, k + i, columnCount) + table[j][k + i - 1] + table[j + i - 1][k + i - 1]) * (i / 2);
						right *= 2;
						right -= get(sums, j, j + i, k + i - i / 2, k + i) - table[j][k + i - 1] - table[j + i - 1][k + i - 1];
						if (left != right)
							continue;
						out.println(i);
						return;
					}
				}
			}
		}
		out.println("IMPOSSIBLE");
	}

	private long get(long[][] table, int rowStart, int rowEnd, int columnStart, int columnEnd) {
		return table[rowStart][columnStart] + table[rowEnd][columnEnd] - table[rowStart][columnEnd] - table[rowEnd][columnStart];
	}
}

