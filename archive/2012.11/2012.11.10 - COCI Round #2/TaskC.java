package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] lengths = IOUtils.readIntArray(in, count);
		Arrays.sort(lengths);
		int answer = count - 1;
		int sum = 0;
		for (int i = 0; i < count; i++) {
			sum += lengths[i];
			if (sum <= count - i - 2)
				answer = count - i - 2;
		}
		out.printLine(answer);
	}
}
