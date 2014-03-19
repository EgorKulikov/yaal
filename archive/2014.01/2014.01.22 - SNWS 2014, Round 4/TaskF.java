package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskF {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int step = in.readInt();
		long answer = 1L << step;
		for (int i = step - 1; i > step - 7 && i > 0; i--)
			answer += 1L << (i - 1);
		out.printLine((answer / 2) + "." + (answer % 2 * 5));
    }
}
