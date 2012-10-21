package net.egork;

import net.egork.string.StringUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int a = in.readInt();
		int b = Integer.parseInt(StringUtils.reverse(in.readString()));
		out.printLine(a + b);
	}
}
