package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TheBallAndCups {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int at = in.readInt() - 1;
		int swapCount = in.readInt();
		for (int i = 0; i < swapCount; i++) {
			int from = in.readInt() - 1;
			int to = in.readInt() - 1;
			if (at >= from && at <= to)
				at = from + to - at;
		}
		out.printLine(at + 1);
    }
}
