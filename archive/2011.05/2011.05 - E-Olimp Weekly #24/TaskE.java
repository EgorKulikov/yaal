import net.egork.numbers.IntegerUtils;
import net.egork.utils.Solver;

import java.io.PrintWriter;

public class TaskE implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int pointCount = in.readInt();
		long[][] typeCount = new long[2][2];
		for (int i = 0; i < pointCount; i++) {
			int x = in.readInt();
			int y = in.readInt();
			typeCount[IntegerUtils.trueMod(x, 2)][IntegerUtils.trueMod(y, 2)]++;
		}
		long result = 0;
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				for (int k = i; k < 2; k++) {
					for (int l = (k == i ? j : 0); l < 2; l++) {
						for (int m = k; m < 2; m++) {
							for (int n = (m == k ? l : 0); n < 2; n++) {
								int value = (i - k) * (j + l) + (k - m) * (l + n) + (m - i) * (n + j);
								if (IntegerUtils.trueMod(value, 2) == 1)
									continue;
								if (i == m && j == n)
									result += typeCount[i][j] * (typeCount[i][j] - 1) * (typeCount[i][j] - 2) / 6;
								else if (i == k && j == l)
									result += typeCount[i][j] * (typeCount[i][j] - 1) * typeCount[m][n] / 2;
								else if (k == m && l == n)
									result += typeCount[i][j] * typeCount[k][l] * (typeCount[k][l] - 1) / 2;
								else
									result += typeCount[i][j] * typeCount[k][l] * typeCount[m][n];
							}
						}
					}
				}
			}
		}
		out.println(result);
	}
}

