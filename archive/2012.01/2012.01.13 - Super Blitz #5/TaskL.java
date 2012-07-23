package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskL {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int first = in.readInt();
		int second = in.readInt();
		int third = in.readInt();
		int day = in.readInt();
		for (int i = day + 1; ; i++) {
			if ((i - first) % 23 == 0 && (i - second) % 28 == 0 && (i - third) % 33 == 0) {
				out.printLine(i - day);
				return;
			}
		}
	}
}
