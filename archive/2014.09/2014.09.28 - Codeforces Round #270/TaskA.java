package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int number = in.readInt();
		if (number % 2 == 0) {
			out.printLine(4, number - 4);
		} else {
			out.printLine(9, number - 9);
		}
    }
}
