package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskM {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int length = in.readInt();
		for (int i = 0; i < length; i++) {
			if ((i & 1) == 0)
				out.print(9);
			else
				out.print(8);
		}
		out.printLine();
	}
}
