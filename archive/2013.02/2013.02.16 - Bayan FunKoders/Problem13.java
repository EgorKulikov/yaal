package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class Problem13 {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		long n = in.readLong();
		long answer = n / 3 + (n + 1) / 3;
		out.printLine(answer);
    }
}
