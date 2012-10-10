package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		long answer = 1;
		if (count >= 1)
			answer = 2;
		for (int i = 1; i < count; i++)
			answer += 2 * i;
		out.printLine(answer);
	}
}
