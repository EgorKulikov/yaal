package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskH {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int maxPower = in.readInt();
		int[] values = IOUtils.readIntArray(in, count);
		int[] countPerPower = new int[1000001];
		for (int i : values)
			countPerPower[i]++;
		int answer = 0;
		int times = 0;
		int current = 0;
		for (int i = 1; i <= 1000000; i++) {
			if ((current + countPerPower[i]) * i > maxPower) {
				if (current != 0)
					times++;
				answer += current;
				current = 0;
				if (countPerPower[i] * i > maxPower)
					break;
			}
			current += countPerPower[i];
		}
		if (current != 0) {
			answer += current;
			times++;
		}
		out.printLine(answer, times);
	}
}
