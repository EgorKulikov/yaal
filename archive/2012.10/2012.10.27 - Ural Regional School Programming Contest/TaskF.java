package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskF {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int answer = in.readInt() - 2;
		for (int i = 0; i < count; i++) {
			answer += in.readInt() - 2;
			answer -= in.readInt();
		}
		if (answer >= 0)
			out.printLine(answer);
		else
			out.printLine("Big Bang!");
	}
}
