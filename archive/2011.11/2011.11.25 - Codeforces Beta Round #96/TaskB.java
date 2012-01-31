package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		long[] perMark = new long[21];
		for (int i = 0; i < count; i++)
			perMark[in.readInt() + 10]++;
		long answer = 0;
		for (int i = 0; i < 10; i++)
			answer += perMark[i] * perMark[20 - i];
		answer += perMark[10] * (perMark[10] - 1) / 2;
		out.printLine(answer);
	}
}
