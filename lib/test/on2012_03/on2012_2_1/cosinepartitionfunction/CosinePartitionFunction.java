package on2012_03.on2012_2_1.cosinepartitionfunction;



import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class CosinePartitionFunction {
	private double[] cos;
	private double[][] c;
	private double[][][] answer;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int m = in.readInt();
		int n = in.readInt();
		double x = in.readDouble();
		cos = new double[n + 1];
		for (int i = 0; i <= n; i++)
			cos[i] = Math.cos(i * x / n);
		c = new double[n + 1][n + 1];
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= n; j++) {
				c[i][j] = 1;
				for (int k = 0; k < j; k++)
					c[i][j] *= m - k - i;
				for (int k = 1; k <= j; k++)
					c[i][j] /= k;
			}
		}
		answer = new double[n + 1][n + 1][n + 1];
		ArrayUtils.fill(answer, Double.NaN);
		out.printLine(go(0, n, n));
	}

	private double go(int taken, int n, int max) {
		if (n == 0)
			return 1;
		if (max == 0)
			return 0;
		if (!Double.isNaN(answer[taken][n][max]))
			return answer[taken][n][max];
		answer[taken][n][max] = 0;
		for (int i = 1; i <= max; i++) {
			for (int j = 1; j * i <= n; j++)
				answer[taken][n][max] += Math.pow(cos[i], j) * c[taken][j] * go(taken + j, n - i * j, i - 1);
		}
		return answer[taken][n][max];
	}
}
