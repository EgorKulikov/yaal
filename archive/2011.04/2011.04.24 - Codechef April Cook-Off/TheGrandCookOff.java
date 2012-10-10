import net.egork.collections.ArrayUtils;
import net.egork.io.IOUtils;
import net.egork.utils.Solver;

import java.io.PrintWriter;

public class TheGrandCookOff implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int chefCount = in.readInt();
		int[] tokenCount = IOUtils.readIntArray(in, chefCount);
		int sum = (int) ArrayUtils.sumArray(tokenCount);
		double[][][] count = new double[chefCount + 1][chefCount + 1][sum + 1];
		count[0][0][0] = 1;
		for (int i = 0; i < chefCount; i++) {
			for (int j = 0; j <= i; j++) {
				for (int k = 0; k <= sum; k++) {
					if (count[i][j][k] == 0)
						continue;
					count[i + 1][j][k] += count[i][j][k];
					count[i + 1][j + 1][k + tokenCount[i]] += count[i][j][k];
				}
			}
		}
		double[][] c = new double[chefCount + 1][chefCount + 1];
		for (int i = 0; i <= chefCount; i++) {
			c[i][0] = 1;
			for (int j = 1; j <= i; j++)
				c[i][j] = c[i - 1][j - 1] + c[i - 1][j];
		}
		double result = 0;
		for (int i = 1; i < chefCount; i++) {
			double currentResult = 0;
			for (int j = 0; j <= sum; j++)
				currentResult += count[chefCount][i][j] * (Math.abs(sum - 2 * j));
			currentResult /= c[chefCount][i];
			result += currentResult;
		}
		if (chefCount != 1)
			result /= chefCount - 1;
		out.printf("%.6f\n", result);
	}
}

