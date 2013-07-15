package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int carriageCount = in.readInt();
		int[][] cost = IOUtils.readIntTable(in, count, count);
		int[][] sum = new int[count + 1][count + 1];
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < count; j++)
				sum[i + 1][j + 1] = sum[i + 1][j] + sum[i][j + 1] - sum[i][j] + cost[i][j];
		}
		for (int i = 0; i < count; i++) {
			for (int j = i; j < count; j++)
				cost[i][j] = (sum[j + 1][j + 1] - sum[i][j + 1] - sum[j + 1][i] + sum[i][i]) >> 1;
		}
		int[] answer = new int[count];
		System.arraycopy(cost[0], 0, answer, 0, count);
		int[] next = new int[count];
		for (int i = 0; i < carriageCount - 1; i++) {
			System.arraycopy(answer, 0, next, 0, count);
			go(next, answer, cost, 0, count - 1, 0, count - 1);
			int[] t = answer;
			answer = next;
			next = t;
		}
		out.printLine(answer[count - 1]);
    }

	private void go(int[] next, int[] answer, int[][] cost, int left, int right, int from, int to) {
		if (left > right)
			return;
		int current = (left + right) >> 1;
		int best = from;
		for (int i = from; i <= to && i < current; i++) {
			if (next[current] > answer[i] + cost[i + 1][current]) {
				next[current] = answer[i] + cost[i + 1][current];
				best = i;
			}
		}
		go(next, answer, cost, left, current - 1, from, best);
		go(next, answer, cost, current + 1, right, best, to);
	}
}
