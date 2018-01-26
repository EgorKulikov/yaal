package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		long a = in.readLong();
		long b = in.readLong();
		long answer = 0;
		while (b != 0) {
			answer += a / b;
			long t = b;
			b = a % b;
			a = t;
		}
		out.printLine(answer);
    }
}
