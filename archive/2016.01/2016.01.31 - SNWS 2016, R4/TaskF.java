package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskF {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		in = new InputReader(System.in);
		out = new OutputWriter(System.out);
		int n = in.readInt();
		int s = in.readInt();
		out.printLine(1);
		out.flush();
		int first = in.readInt();
		if (first == s) {
			return;
		}
		int left = 1;
		int right = n - 1;
		while (left <= right) {
			int middle = (left + right) >> 1;
			out.printLine(middle + 1);
			out.flush();
			int id = in.readInt();
			if (id == s) {
				return;
			}
			if (id > first && s < first) {
				left = middle + 1;
			} else if (id < first && s > first) {
				right = middle - 1;
			} else if (id < s) {
				left = middle + 1;
			} else {
				right = middle - 1;
			}
		}
	}
}
