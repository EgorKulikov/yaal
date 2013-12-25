package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		boolean[] present = new boolean[count + 1];
		int answer = count;
		int[] array = IOUtils.readIntArray(in, count);
		for (int i : array) {
			if (i <= count && !present[i]) {
				present[i] = true;
				answer--;
			}
		}
		out.printLine(answer);
	}
}
