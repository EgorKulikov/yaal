package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		if (count == 1) {
			out.printLine(1);
			return;
		}
		int[] numbers = IOUtils.readIntArray(in, count);
		int[][] answer = new int[count][count];
		int[] last = new int[1000001];
		Arrays.fill(last, -1);
		int result = 0;
		for (int i = 0; i < count; i++) {
			for (int j = i + 1; j < count; j++) {
				answer[i][j] = 2;
				int same = last[numbers[j]];
				if (same != -1)
					answer[i][j] = answer[same][i] + 1;
				result = Math.max(result, answer[i][j]);
			}
			last[numbers[i]] = i;
		}
		out.printLine(result);
	}
}
