package on2014_06.on2014_06_08_RCC_2014___________.D__________________;



import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskD {
	private static final long MOD = (long) (1e9 + 7);
	long[][] c;
	long[] answer;
	long[] reverse;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		answer = new long[count + 1];
		Arrays.fill(answer, -1);
		reverse = new long[count + 1];
		Arrays.fill(reverse, -1);
		c = IntegerUtils.generateBinomialCoefficients(count + 1, MOD);
		out.printLine(calculate(count));
    }

	private long calculate(int count) {
		if (answer[count] != -1)
			return answer[count];
		if (count <= 1)
			return answer[count] = 1;
		answer[count] = 0;
		for (int i = 0; i < count; i += 2)
			answer[count] += calculate(i) * calculateReverse(count - i - 1) % MOD * c[count - 1][i] % MOD;
		return answer[count] %= MOD;
	}

	private long calculateReverse(int count) {
		if (reverse[count] != -1)
			return reverse[count];
		if (count <= 1)
			return reverse[count] = 1;
		reverse[count] = 0;
		for (int i = 1; i < count; i += 2)
			reverse[count] += calculateReverse(i) * calculateReverse(count - i - 1) % MOD * c[count - 1][i] % MOD;
		return reverse[count] %= MOD;
	}
}
