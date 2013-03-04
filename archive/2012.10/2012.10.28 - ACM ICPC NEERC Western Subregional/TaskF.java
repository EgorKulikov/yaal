package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskF {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int answer = Integer.bitCount(Integer.highestOneBit(count - 1) - 1) + 1;
		out.printLine(answer);
		for (int i = 0; i < count; i++) {
			for (int j = i + 1; j < count; j++) {
				int index = Integer.bitCount(Integer.lowestOneBit(i ^ j) - 1) + 1;
				if (j == i + 1)
					out.print(index);
				else
					out.print(" " + index);
			}
			out.printLine();
		}
	}
}
