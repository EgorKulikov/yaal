package on2012_10.on2012_10_14_OpenCup_SPb_GP.Phi;



import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class Phi {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		long from = in.readLong();
		long to = in.readLong();
		int[] primes = IntegerUtils.generatePrimes(2000000);
		int length = (int) (to - from + 1);
		long[] answer = new long[length];
		long[] remaining = new long[length];
		Arrays.fill(answer, 1);
		for (int i = 0; i < length; i++)
			remaining[i] = from + i;
		for (int p : primes) {
			int start = (int) (p - from % p);
			if (start == p)
				start = 0;
			for (int i = start; i < length; i += p) {
				remaining[i] /= p;
				answer[i] *= p - 1;
				while (remaining[i] % p == 0) {
					remaining[i] /= p;
					answer[i] *= p;
				}
			}
		}
		for (int i = 0; i < length; i++) {
			if (remaining[i] != 1)
				answer[i] *= remaining[i] - 1;
		}
		long total = 0;
		for (long i : answer)
			total += i;
		out.printLine(total);
	}
}
