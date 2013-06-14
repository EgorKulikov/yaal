package net.egork;

import net.egork.collections.sequence.Array;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[][] table = IOUtils.readIntTable(in, count, count);
		int[] answer = new int[count];
		for (int i = 0; i < count; i++) {
			for (int j = i + 1; j < count; j++) {
				answer[i] |= table[i][j];
				answer[j] |= table[i][j];
			}
		}
		out.printLine(Array.wrap(answer).toArray());
	}
}
