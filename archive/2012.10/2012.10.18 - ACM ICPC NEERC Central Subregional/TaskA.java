package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		char[] position = in.readString().toCharArray();
		char start = 'A';
		char end = 'B';
		for (int i = count - 1; i >= 0; i--) {
			if (position[i] == start)
				end = (char) ('A' + 'B' + 'C' - start - end);
			else if (position[i] == end)
				start = (char) ('A' + 'B' + 'C' - start - end);
			else {
				out.printLine("NO");
				return;
			}
		}
		out.printLine("YES");
	}
}
