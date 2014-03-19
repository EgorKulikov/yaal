package on2014_02.on2014_02_26_Codeforces_Round__232__Div__1_.A___On_Number_of_Decompositions_into_Multipliers;



import net.egork.collections.map.Counter;
import net.egork.io.IOUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
	private static final long MOD = (long) (1e9 + 7);

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] divisors = IOUtils.readIntArray(in, count);
		Counter<Integer> counter = new Counter<Integer>();
		for (int i : divisors) {
			for (int j = 2; j * j <= i; j++) {
				while (i % j == 0) {
					counter.add(j);
					i /= j;
				}
			}
			if (i != 1)
				counter.add(i);
		}
		long[][] c = new long[count * 30][count];
		for (int i = 0; i < count * 30; i++) {
			c[i][0] = 1;
			for (int j = 1; j <= i && j < count; j++)
				c[i][j] = (c[i - 1][j - 1] + c[i - 1][j]) % MOD;
		}
		long answer = 1;
		for (long i : counter.values()) {
			answer *= c[((int) (i + count - 1))][count - 1];
			answer %= MOD;
		}
		out.printLine(answer);
	}
}
