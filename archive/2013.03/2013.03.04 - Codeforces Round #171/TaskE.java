package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] s = in.readString().toCharArray();
		int less = 0;
		int more = 1;
		for (char c : s) {
			int nextMore;
			int nextLess;
			if (c == '1') {
				nextMore = more;
				nextLess = Math.min(less, more) + 1;
			} else {
				nextLess = less;
				nextMore = Math.min(less, more) + 1;
			}
			more = nextMore;
			less = nextLess;
		}
		out.printLine(less);
    }
}
