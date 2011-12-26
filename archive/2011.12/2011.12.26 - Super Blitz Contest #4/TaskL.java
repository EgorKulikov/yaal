package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskL {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int index = in.readInt();
		long cube = 1;
		long square = 1;
		long last = 0;
		for (int i = 0; i < index; i++) {
			long curCube = cube * cube * cube;
			long curSquare = square * square;
			if (curCube == curSquare) {
				cube++;
				square++;
				last = curCube;
			} else if (curCube < curSquare) {
				cube++;
				last = curCube;
			} else {
				square++;
				last = curSquare;
			}
		}
		out.printLine(last);
	}
}
