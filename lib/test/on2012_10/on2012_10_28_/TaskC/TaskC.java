package on2012_10.on2012_10_28_.TaskC;



import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.math.BigInteger;

public class TaskC {
	private static final long MOD = (long) (1e9 + 7);

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		long n = in.readInt();
		if (n == 1) {
			out.printLine(0);
			return;
		}
		long left = (n + 1) * BigInteger.valueOf(2 * n).modInverse(BigInteger.valueOf(MOD)).longValue() % MOD;
		long first = (IntegerUtils.power(n * (n + 1), n + 1, MOD) - 1) * BigInteger.valueOf(n * (n + 1) - 1).modInverse(BigInteger.valueOf(MOD)).longValue() % MOD;
		long second = (IntegerUtils.power(n, n + 1, MOD) - 1) * BigInteger.valueOf(n - 1).modInverse(BigInteger.valueOf(MOD)).longValue() % MOD;
		long third = IntegerUtils.power(2, n + 1, MOD);
		long fourth = IntegerUtils.power(n + 2, n, MOD) * 2 % MOD;
		long answer = left * (first - second + third - fourth) % MOD;
		answer += n * (n + 1) / 2;
		answer += MOD;
		answer %= MOD;
		out.printLine(answer);
	}
}
