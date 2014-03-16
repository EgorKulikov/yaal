package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] s = in.readString().toCharArray();
		int length = -1;
		int last = 0;
		int answer = 0;
		for (char c : s) {
			if (c != last) {
				if (length % 2 == 0)
					answer++;
				length = 0;
				last = c;
			}
			length++;
		}
		if (length % 2 == 0)
			answer++;
		out.printLine(answer);
    }
}
