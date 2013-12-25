import net.egork.utils.Solver;

import java.io.PrintWriter;

public class AEHashFunction implements Solver {
	private static final long MOD = 1000000007;
	private static long[][][] result = new long[101][101][153];

	static {
		for (int i = 1; i <= 100; i++)
			go(i, 152);
	}

	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int aCount = in.readInt();
		int eCount = in.readInt();
		int value = in.readInt();
		if (aCount == 0) {
			if (value == 0)
				out.println(1);
			else
				out.println(0);
			return;
		}
		if (value > 152) {
			out.println(0);
			return;
		}
//		boolean[] calculated = new boolean[aCount + eCount + 1];
//		go(aCount + eCount, value, calculated);
		out.println(result[aCount + eCount][aCount][value]);
	}

	private static void go(int length, int value) {
		if (value == 0) {
			result[length][0][0] = 1;
			return;
		}
		if (length == 1) {
			result[1][0][0] = 1;
			result[1][1][1] = 1;
			return;
		}
		int left = length / 2;
		int right = (length + 1) / 2;
		long[] leftLess = new long[left + 1];
		long[] rightLess = new long[right + 1];
		for (int i = 0; i <= value; i++) {
			for (int j = 0; j <= left; j++) {
				leftLess[j] += result[left][j][i];
				if (leftLess[j] >= MOD)
					leftLess[j] -= MOD;
			}
			for (int j = 0; j <= left; j++) {
				int max = Math.min(right, Math.min(50 - j, value - i - j));
				for (int k = 0; k <= max; k++) {
					result[length][j + k][i] = ((result[length][j + k][i] + result[left][j][i] * rightLess[k] + result[right][k][i] * leftLess[j]) % MOD);
				}
			}
			for (int j = 0; j <= right; j++) {
				rightLess[j] += result[right][j][i];
				if (rightLess[j] >= MOD)
					rightLess[j] -= MOD;
			}
		}
		for (int i = value; i >= 0; i--) {
			for (int j = 0; j <= length; j++) {
				if (i >= j)
					result[length][j][i] = result[length][j][i - j];
				else
					result[length][j][i] = 0;
			}
		}
	}
}

