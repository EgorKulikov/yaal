package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int firstCount = in.readInt();
		int secondCount = in.readInt();
		if (firstCount <= secondCount) {
			out.printLine("Second");
		} else {
			out.printLine("First");
		}
    }
}
