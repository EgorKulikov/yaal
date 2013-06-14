package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int current = count;
		int second = 1;
		while (current != 2 * count - 1) {
			current /= 2;
			current += count;
			second++;
		}
		out.printLine(current, second);
    }
}
