package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskQ {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int length = in.readInt();
		out.print(1);
		int current = 2;
		int currentCount = 0;
		for (int i = 1; i < length; i++) {
			if (currentCount == current) {
				current++;
				currentCount = 1;
			} else
				currentCount++;
			out.print(" " + current);
		}
		out.printLine();
	}
}
