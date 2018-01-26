package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		long price = in.readLong();
		while (price % 3 == 0 && price != 0)
			price /= 3;
		long answer = price / 3 + 1;
		out.printLine(answer);
    }
}
