package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		String s = in.next();
		int best = s.length() + 1;
		char bc = ' ';
		for (char c : new char[]{'A', 'C', 'G', 'T'}) {
			int count = 0;
			for (int i = 0; i < s.length(); ++i)
				if (s.charAt(i) == c) ++count;
			if (count < best) {
				best = count;
				bc = c;
			}
		}
		out.printLine(best);
		for (int i = 0; i < s.length(); ++i) out.print(bc);
		out.printLine();
	}
}
