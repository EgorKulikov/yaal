package net.egork;

import net.egork.misc.ArrayUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskG {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = ArrayUtils.count(in.readLine().toCharArray(), '!');
		if (count == 0) {
			out.printLine("Pfff");
		} else {
			out.print("W");
			for (int i = 0; i < count; i++) {
				out.print('o');
			}
			out.printLine("w");
		}
	}
}
