package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int edgeCount = in.readInt();
		int[] degree = new int[count];
		for (int i = 0; i < 2 * edgeCount; i++)
			degree[in.readInt() - 1]++;
		long answer = (long) count * (count - 1) * (count - 2) / 6;
		answer -= (long) edgeCount * (count - 2);
		for (int i : degree)
			answer += (long) i * (i - 1) / 2;
		out.printLine(answer);
	}
}
