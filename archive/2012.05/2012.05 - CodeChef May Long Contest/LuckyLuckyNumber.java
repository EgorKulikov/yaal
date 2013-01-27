package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class LuckyLuckyNumber {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		while (count > 0 && count % 7 != 0)
			count -= 4;
		out.printLine(Math.max(count, -1));
	}
}
