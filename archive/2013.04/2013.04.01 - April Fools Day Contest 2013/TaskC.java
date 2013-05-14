package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] numbers = IOUtils.readIntArray(in, count);
		double answer = 0;
		int sum = 0;
		for (int i = 0; i < count; i++) {
			sum += numbers[i];
			answer = Math.max(answer, (double)sum / (i + 1));
		}
		out.printLine(answer);
	}
}
