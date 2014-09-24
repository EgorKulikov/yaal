package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int size = in.readInt();
		int count = in.readInt();
		double result = 0;
		double total = 0;
		for (int i = 1; i <= size; i++) {
			double current = Math.pow((double)i / size, count) - total;
			total += current;
			result += current * i;
		}
		out.printLine(result);
    }
}
