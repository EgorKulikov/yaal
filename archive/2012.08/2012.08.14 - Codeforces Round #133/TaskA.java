package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int a = in.readInt();
		int b = in.readInt();
		int c = in.readInt();
		int current = a;
		int result = 0;
		for (int i = 1; i < Math.min(b, c); i++) {
			result += 2 * current;
			current++;
		}
		result += current * (Math.max(b, c) - Math.min(b, c) + 1);
		out.printLine(result);
	}
}
