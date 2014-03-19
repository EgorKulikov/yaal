package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskD {
	static final int MOD = (int) (1e9 + 7);

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] array = IOUtils.readIntArray(in, count);
		int sum = (int) ArrayUtils.sumArray(array);
		long[] answer = new long[2 * sum + 1];
		long[] next = new long[2 * sum + 1];
		long result = 0;
		for (int i : array) {
			result += answer[sum];
			Arrays.fill(next, 0);
			answer[sum]++;
			for (int j = 0; j <= 2 * sum; j++) {
				if (answer[j] >= MOD)
					answer[j] -= MOD;
				if (answer[j] == 0)
					continue;
				next[j - i] += answer[j];
				next[j + i] += answer[j];
			}
			long[] t = answer;
			answer = next;
			next = t;
		}
		result += answer[sum];
		result %= MOD;
		out.printLine(result);
    }
}
