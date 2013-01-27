package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] fragility = IOUtils.readIntArray(in, count - 1);
		long answer = ArrayUtils.sumArray(fragility);
		int countOdd = 0;
		for (int i = 0; i < count; i++) {
			int curDegree = 0;
			if (i != 0)
				curDegree += fragility[i - 1];
			if (i != count - 1)
				curDegree += fragility[i];
			if (curDegree % 2 == 1)
				countOdd++;
		}
		if (countOdd <= 2) {
			out.printLine(answer);
			return;
		}
		answer = 0;
		long[] current = new long[3];
		long[] next = new long[3];
		current[2] = Long.MIN_VALUE;
		for (int i = 0; i < count - 1; i++) {
			answer = Math.max(answer, current[2]);
			if (fragility[i] != 1) {
				next[0] = current[0] + fragility[i] - fragility[i] % 2;
				next[2] = current[2] + fragility[i] - fragility[i] % 2;
			} else
				next[0] = next[2] = 0;
			next[1] = current[1] + fragility[i] - (fragility[i] + 1) % 2;
			next[1] = Math.max(next[1], next[0]);
			next[2] = Math.max(next[2], next[1]);
			long[] temp = next;
			next = current;
			current = temp;
		}
		answer = Math.max(answer, current[2]);
		out.printLine(answer);
	}
}
