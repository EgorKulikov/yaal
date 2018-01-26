package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		double answer = 0;
		for (int i = 1; i <= count; i++) {
			answer += 1d / i;
		}
		answer *= count;
		out.printLine(answer);
    }
}
