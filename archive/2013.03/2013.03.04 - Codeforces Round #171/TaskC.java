package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int queryCount = in.readInt();
		int[] numbers = IOUtils.readIntArray(in, count);
		boolean up = true;
		int start = 0;
		int sameStart = 0;
		int[] minLeft = new int[count];
		for (int i = 1; i < count; i++) {
			if (up) {
				if (numbers[i] < numbers[i - 1]) {
					up = false;
					sameStart = i;
				}
			} else {
				if (numbers[i] > numbers[i - 1]) {
					start = sameStart;
					up = true;
				} else if (numbers[i] < numbers[i - 1])
					sameStart = i;
			}
			minLeft[i] = start;
		}
		for (int i = 0; i < queryCount; i++) {
			int from = in.readInt() - 1;
			int to = in.readInt() - 1;
			if (from >= minLeft[to])
				out.printLine("Yes");
			else
				out.printLine("No");
		}
	}
}
