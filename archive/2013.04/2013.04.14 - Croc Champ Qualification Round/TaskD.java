package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int moveCount = in.readInt();
		int[][] answer = new int[moveCount][count];
		int[] current = new int[count];
		Arrays.fill(current, 0, count - 1, 1);
		for (int i = 0; i < moveCount; i++) {
			int index = count - (1 << i);
			int bit = (1 << i);
			for (int j = 0; j < count; j++) {
				if (((count - j - 1) & bit) != (current[j] & bit)) {
					answer[i][j] = index;
					current[j] += bit;
				} else
					answer[i][j] = count;
			}
		}
		for (int[] row : answer)
			out.printLine(row);
    }
}
