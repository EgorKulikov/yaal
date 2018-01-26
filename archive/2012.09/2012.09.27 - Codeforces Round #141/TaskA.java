package net.egork;

import net.egork.io.IOUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.Arrays;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int[] colors = IOUtils.readIntArray(in, 4);
		Arrays.sort(colors);
		int answer = 0;
		for (int i = 1; i < 4; i++) {
			if (colors[i] == colors[i - 1])
				answer++;
		}
		out.printLine(answer);
	}
}
