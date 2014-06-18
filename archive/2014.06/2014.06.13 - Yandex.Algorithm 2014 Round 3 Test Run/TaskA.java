package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] degree = new int[count];
		for (int i = 0; i < 2 * count - 2; i++)
			degree[in.readInt() - 1]++;
		long answer = 0;
		for (int i : degree)
			answer += (long)i * (i - 1) / 2;
		out.printLine(answer);
    }
}
