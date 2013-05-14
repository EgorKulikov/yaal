package on2012_12.on2012_12_20_Volume_6._1586___Threeprime_Numbers;



import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class Task1586 {
	private static final long MOD = (long) (1e9 + 9);

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		long[] result = new long[100];
		for (int i = 10; i < 100; i++)
			result[i] = 1;
		long[] next = new long[100];
		int[] primes = IntegerUtils.generatePrimes(1000);
		for (int j = 2; j < count; j++) {
			Arrays.fill(next, 0);
			for (int k = 10; k < 100; k++) {
				for (int l = 1; l < 10; l += 2) {
					if (Arrays.binarySearch(primes, k * 10 + l) >= 0)
						next[k % 10 * 10 + l] += result[k];
				}
			}
			for (int k = 0; k < 100; k++)
				result[k] = next[k] % MOD;
		}
		long answer = 0;
		for (int i = 0; i < 100; i++)
			answer += result[i];
		out.printLine(answer % MOD);
	}
}
