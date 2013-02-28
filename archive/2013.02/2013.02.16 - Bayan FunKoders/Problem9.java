package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Problem9 {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int number = in.readInt();
		int delta = in.readString().length();
		long answer = 1;
		for (int i = number; i > 0; i -= delta)
			answer *= i;
		out.printLine(answer);
    }
}
