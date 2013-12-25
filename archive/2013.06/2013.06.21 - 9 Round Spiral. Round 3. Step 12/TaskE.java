package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
	private static final long MOD = (long) (1e9 + 7);

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		long[] answer = new long[count + 1];
		answer[0] = 1;
		for (int i = 1; i <= count; i++) {
			answer[i] = answer[i - 1];
			for (int j = 0; j < i; j++)
				answer[i] += 2 * answer[j];
			answer[i] %= MOD;
		}
		out.printLine(answer[count]);
    }
}
