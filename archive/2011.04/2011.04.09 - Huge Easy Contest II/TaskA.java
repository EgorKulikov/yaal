package April2011.UVaHugeEasyContestII;

import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;

public class TaskA implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		int money = in.readInt();
		int[][] cost = in.readIntTable(rowCount, columnCount);
		long[][] partialSums = new long[rowCount + 1][columnCount + 1];
		for (int i = 1; i <= rowCount; i++) {
			int currentRow = 0;
			for (int j = 1; j <= columnCount; j++) {
				currentRow += cost[i - 1][j - 1];
				partialSums[i][j] = currentRow + partialSums[i - 1][j];
			}
		}
		int maxSquare = 0;
		long minCost = 0;
		for (int i = 0; i < columnCount; i++) {
			for (int j = i + 1; j <= columnCount; j++) {
				int index = 0;
				for (int k = 1; k <= rowCount; k++) {
					long currentCost = partialSums[k][j] - partialSums[k][i] - partialSums[index][j] + partialSums[index][i];
					while (currentCost > money) {
						index++;
						currentCost = partialSums[k][j] - partialSums[k][i] - partialSums[index][j] + partialSums[index][i];
					}
					int square = (j - i) * (k - index);
					if (square > maxSquare || square == maxSquare && minCost > currentCost) {
						maxSquare = square;
						minCost = currentCost;
					}
				}
			}
		}
		out.println("Case #" + testNumber + ": " + maxSquare + " " + minCost);
	}
}

