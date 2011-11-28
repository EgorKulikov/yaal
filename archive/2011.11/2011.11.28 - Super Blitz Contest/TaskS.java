package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskS {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		long answer = 0;
		for (int i = 1; i <= count; i++)
			answer += i;
		for (int i = 0; i < count - 1; i++)
			answer -= in.readInt();
		out.printLine(answer);
	}
}
