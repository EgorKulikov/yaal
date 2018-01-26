package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class Chips {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		if (count % 6 == 0)
			out.printLine("Cody");
		else
			out.printLine("Zach", count % 6);
    }
}
