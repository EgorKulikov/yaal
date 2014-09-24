package on2014_08.on2014_08_30_Petr_Mitrichev_Contest_12.Jumping;



import net.egork.collections.intervaltree.SumIntervalTree;
import net.egork.misc.ArrayUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class Jumping {
	private static final long MOD = (long) (1e9 + 7);

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int from = in.readInt();
		int to = in.readInt();
		int[] primes = IntegerUtils.generatePrimes((int) (Math.sqrt(to) + 2));
		ArrayUtils.reverse(primes);
		int[] divisor = new int[to - from + 1];
		Arrays.fill(divisor, Integer.MAX_VALUE);
		for (int i : primes) {
			int start = (i - from % i) % i;
			for (int j = start; j < divisor.length; j += i) {
				divisor[j] = i;
			}
		}
		SumIntervalTree tree = new SumIntervalTree(to - from + 1) {
			@Override
			protected long joinValue(long left, long right) {
				return super.joinValue(left, right) % MOD;
			}

			@Override
			protected long joinDelta(long was, long delta) {
				return super.joinDelta(was, delta) % MOD;
			}

			@Override
			protected long accumulate(long value, long delta, int length) {
				return super.accumulate(value, delta, length) % MOD;
			}
		};
		long answer = 0;
		for (int i = from; i <= to; i++) {
			if (divisor[i - from] >= i) {
				continue;
			}
			long result = (i == from ? 1 : tree.query(i - from - i / divisor[i - from], i - from - divisor[i - from]));
			if (i == to) {
				answer += result;
			}
			tree.update(i - from + divisor[i - from], i - from + i / divisor[i - from], result);
		}
		answer += tree.query(to - from, to - from);
		answer %= MOD;
		out.printLine(answer);
    }
}
