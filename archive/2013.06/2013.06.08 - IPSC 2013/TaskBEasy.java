package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskBEasy {
	final int MAX = 1000000;

	long[] answer = new long[MAX + 1];

	{
		Arrays.fill(answer, -1);
		answer[1] = 0;
	}

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		out.printLine(go(in.readInt()));
    }

	private long go(int x) {
		if (answer[x] != -1)
			return answer[x];
		if (x % 3 == 0)
			return answer[x] = go(x / 3) + go(2 * x / 3) + (long) 2 * x * x / 9;
		if (x % 2 == 0)
			return answer[x] = 2 * go(x / 2) + (long)x * x / 4;
		return answer[x] = go(x - 1) + x - 1;
	}
}
