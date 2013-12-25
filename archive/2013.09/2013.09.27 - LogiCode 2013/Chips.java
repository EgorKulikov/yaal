package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Chips {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		if (count % 6 == 0)
			out.printLine("Cody");
		else
			out.printLine("Zach", count % 6);
    }
}
