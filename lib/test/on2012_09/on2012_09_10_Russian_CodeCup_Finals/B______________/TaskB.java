package on2012_09.on2012_09_10_Russian_CodeCup_Finals.B______________;



import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
	private static final long MOD = (7 << 20) + 1;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int sum = in.readInt();
		int count = in.readInt();
		if (sum < count) {
			out.printLine(0);
			return;
		}
		long[][] c = IntegerUtils.generateBinomialCoefficients(sum, MOD);
		long[] shift = new long[sum + 1];
		for (int i = 1; i <= sum; i++)
			shift[i] = c[i - 1][count - 1];
		long[] result = new long[sum + 1];
		result[0] = 1;
		for (int i = 1; i <= sum; i++) {
			for (int j = i - count; j >= 0; j--)
				result[i] += result[j] * shift[i - j];
			result[i] %= MOD;
		}
		out.printLine(result[sum]);
	}
}
