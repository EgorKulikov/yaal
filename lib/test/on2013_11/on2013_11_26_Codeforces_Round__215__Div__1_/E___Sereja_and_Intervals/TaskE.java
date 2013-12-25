package on2013_11.on2013_11_26_Codeforces_Round__215__Div__1_.E___Sereja_and_Intervals;



import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
	private static final int MOD = (int) (1e9 + 7);

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int size = in.readInt();
		int special = in.readInt() - 1;
		if (count > size) {
			out.printLine(0);
			return;
		}
		int[][] result = new int[count + 1][];
		for (int i = 0; i <= count; i++)
			result[i] = new int[i + 2];
		result[0][0] = 1;
		for (int i = 0; i < size; i++) {
			if (i == special) {
				for (int j = Math.min(count, i + 1); j > 0; j--) {
					for (int k = j; k > 0; k--) {
						result[j][k] = result[j - 1][k] + result[j - 1][k - 1];
						if (result[j][k] >= MOD)
							result[j][k] -= MOD;
					}
					result[j][0] = result[j - 1][0];
				}
				result[0][0] = 0;
			} else {
				for (int j = Math.min(count, i + 1); j > 0; j--) {
					for (int k = j; k > 0; k--) {
						result[j][k] += result[j][k - 1];
						if (result[j][k] >= MOD)
							result[j][k] -= MOD;
						result[j][k] += result[j - 1][k];
						if (result[j][k] >= MOD)
							result[j][k] -= MOD;
						result[j][k] += result[j - 1][k - 1];
						if (result[j][k] >= MOD)
							result[j][k] -= MOD;
					}
					result[j][0] += result[j - 1][0];
					if (result[j][0] >= MOD)
						result[j][0] -= MOD;
				}
			}
		}
		out.printLine(result[count][count] * IntegerUtils.factorial(count, MOD) % MOD);
    }
}
