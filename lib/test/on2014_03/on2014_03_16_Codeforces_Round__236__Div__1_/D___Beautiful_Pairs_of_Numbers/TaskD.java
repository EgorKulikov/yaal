package on2014_03.on2014_03_16_Codeforces_Round__236__Div__1_.D___Beautiful_Pairs_of_Numbers;



import net.egork.misc.ArrayUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
	private static final int MOD = (int) (1e9 + 7);
	long[][][] result = new long[1001][][];
//	long[][][] strict = new long[1001][][];
	long[] factorial = IntegerUtils.generateFactorial(1001, MOD);

	{
		result[0] = new long[0][0];
//		strict[0] = new long[0][0];
		for (int i = 1; i <= 1000; i++) {
			int maxLength;
			for (int j = 1; ; j++) {
				if (j * (j + 1) / 2 > i) {
					maxLength = j - 1;
					break;
				}
			}
			result[i] = new long[maxLength + 1][];
//			strict[i] = new long[maxLength + 1][];
			result[i][0] = new long[0];
//			strict[i][0] = new long[0];
			for (int j = 1; j <= maxLength; j++) {
				result[i][j] = new long[i - j * (j - 1) / 2];
//				strict[i][j] = new long[i - j * (j - 1) / 2];
			}
		}
		ArrayUtils.fill(result, -1);
//		ArrayUtils.fill(strict, -1);
		for (int i = 1; i <= 1000; i++) {
			for (int j = 1; j < result[i].length; j++)
				calculateStrict(i, j, result[i][j].length - 1);
			for (int j = 1; j < result[i - 1].length; j++) {
				for (int k = 0; k < result[i][j].length; k++) {
					result[i][j][k] += result[i - 1][j][Math.min(k, result[i - 1][j].length - 1)];
					result[i][j][k] %= MOD;
				}
			}
		}
	}

	private long calculate(int remaining, int qty, int max) {
		if (qty == 0)
			return 1;
		if (result[remaining].length <= qty || max < 0)
			return 0;
		max = Math.min(max, result[remaining][qty].length - 1);
		if (result[remaining][qty][max] != -1)
			return result[remaining][qty][max];
		return result[remaining][qty][max] = (calculate(remaining - 1, qty, max) + calculateStrict(remaining, qty, max)) % MOD;
	}

	private long calculateStrict(int remaining, int qty, int max) {
		if (result[remaining].length <= qty || result[remaining][qty].length <= max || max < 0)
			return 0;
		if (result[remaining][qty][max] != -1)
			return result[remaining][qty][max];
		return result[remaining][qty][max] = (calculateStrict(remaining, qty, max - 1) + calculate(remaining - max - 1, qty - 1, max - 1)) % MOD;
	}

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int qty = in.readInt();
		if (result[count].length <= qty)
			out.printLine(0);
		else
			out.printLine(result[count][qty][result[count][qty].length - 1] * factorial[qty] % MOD);
    }
}
