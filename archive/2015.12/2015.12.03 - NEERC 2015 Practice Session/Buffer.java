package net.egork;

import net.egork.io.IOUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class Buffer {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int k = in.readInt();
		char[] blocks = IOUtils.readCharArray(in, n);
		int worthiness = 0;
		int locked = 0;
		for (int i = 0; i < k - 1 && i < n; i++) {
			if (Character.isDigit(blocks[i]))
				worthiness += blocks[i] - '0';
			else
				locked++;
		}
		int answer = Integer.MAX_VALUE;
		int at = 0;
		for (int i = k - 1; i < n; i++) {
			if (Character.isDigit(blocks[i]))
				worthiness += blocks[i] - '0';
			else
				locked++;
			if (locked == 0 && worthiness < answer) {
				answer = worthiness;
				at = i - (k - 1) + 1;
			}
			int j = i - (k - 1);
			if (Character.isDigit(blocks[j]))
				worthiness -= blocks[j] - '0';
			else
				locked--;
		}
		out.printLine(at);
	}
}
