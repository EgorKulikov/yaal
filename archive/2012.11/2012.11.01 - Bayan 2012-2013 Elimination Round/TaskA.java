package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int refillTime = in.readInt();
		int[] distance = IOUtils.readIntArray(in, count);
		int[] fuel = IOUtils.readIntArray(in, count);
		int[] next = new int[count];
		for (int i = 0; i < count; i++) {
			next[i] = count;
			for (int j = i + 1; j < count; j++) {
				if (fuel[j] > fuel[i]) {
					next[i] = j;
					break;
				}
			}
		}
		int current = 0;
		int remaining = 0;
		int answer = 0;
		for (int i = 0; i < count; i++) {
			remaining += fuel[i];
			remaining -= distance[i];
			if (i == next[current])
				current = i;
			answer += distance[i];
			if (remaining < 0) {
				int refills = (-remaining - 1) / fuel[current] + 1;
				answer += refillTime * refills;
				remaining += refills * fuel[current];
			}
		}
		out.printLine(answer);
	}
}
