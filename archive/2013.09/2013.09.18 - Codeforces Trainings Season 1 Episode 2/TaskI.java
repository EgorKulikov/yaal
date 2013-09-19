package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskI {
	int[] permutation;
	long[] answer;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int edgeCount = in.readInt();
		int[] degree = new int[count];
		for (int i = 0; i < edgeCount; i++) {
			int from = in.readInt();
			int to = in.readInt();
			if (from < to)
				degree[from]++;
			else
				degree[to]++;
		}
		boolean[] free = new boolean[count];
		Arrays.fill(free, true);
		permutation = new int[count];
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < count; j++) {
				if (free[j]) {
					if (degree[i] == 0) {
						permutation[i] = j;
						free[j] = false;
						break;
					} else
						degree[i]--;
				}
			}
		}
		answer = new long[count];
		Arrays.fill(answer, -1);
		long result = 0;
		int max = count;
		for (int i = 0; i < count; i++) {
			if (permutation[i] < max) {
				result += go(i);
				max = permutation[i];
			}
		}
		out.printLine(result);
    }

	private long go(int current) {
		if (answer[current] != -1)
			return answer[current];
		answer[current] = 0;
		int max = permutation.length;
		for (int i = current + 1; i < permutation.length; i++) {
			if (permutation[i] > permutation[current] && permutation[i] < max) {
				answer[current] += go(i);
				max = permutation[i];
			}
		}
		if (max == permutation.length)
			answer[current]++;
		return answer[current];
	}
}
