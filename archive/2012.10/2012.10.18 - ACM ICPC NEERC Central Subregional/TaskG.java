package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskG {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		long value = in.readInt();
		long answer = 0;
		while ((answer ^ (answer >> 1)) != value) {
			long bit = Long.highestOneBit(answer ^ (answer >> 1) ^ value);
			answer ^= bit;
		}
		out.printLine(answer);
	}
}
