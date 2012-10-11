package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int top = in.readInt();
		if (top >= 4)
			top = 7 - top;
		for (int i = 0; i < count; i++) {
			int first = in.readInt();
			int second = in.readInt();
			if (first >= 4)
				first = 7 - first;
			if (second >= 4)
				second = 7 - second;
			if (first + second + top != 6) {
				out.printLine("NO");
				return;
			}
		}
		out.printLine("YES");
	}
}
