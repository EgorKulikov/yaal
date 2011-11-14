package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskI {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int sum = 0;
		for (int i = 0; i < 4; i++)
			sum += in.readCharacter() - '0';
		out.printLine(sum * sum);
	}
}
