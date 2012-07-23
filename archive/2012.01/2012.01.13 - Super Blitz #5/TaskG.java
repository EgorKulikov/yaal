package net.egork;

import net.egork.string.StringUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskG {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		String s = in.readString();
		int from = in.readInt() - 1;
		int to = in.readInt();
		String result = s.substring(0, from) + StringUtils.reverse(s.substring(from, to)) + s.substring(to);
		out.printLine(result);
	}
}
