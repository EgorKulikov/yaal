package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int capacity = in.readInt();
		int[] floor = IOUtils.readIntArray(in, count);
		Arrays.sort(floor);
		int answer = 0;
		for (int i = count - 1; i >= 0; i -= capacity) {
			answer += 2 * floor[i] - 2;
		}
		out.printLine(answer);
    }
}
