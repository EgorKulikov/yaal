package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
	private int[] colors;
	private int required;
	private int[][] answer;
	private int[][][] result;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		required = in.readInt();
		colors = IOUtils.readIntArray(in, count);
		answer = new int[count][count];
		result = new int[required][count][count];
		ArrayUtils.fill(answer, -1);
		ArrayUtils.fill(result, -1);
		out.printLine(go(0, count - 1));
    }

	private int go(int from, int to) {
		if (from > to) {
			return 0;
		}
		if (answer[from][to] != -1) {
			return answer[from][to];
		}
		return answer[from][to] = go(required - 1, from + 1, to);
	}

	private int go(int cost, int from, int to) {
		cost = Math.max(cost, 0);
		if (from > to) {
			return cost;
		}
		if (result[cost][from][to] != -1) {
			return result[cost][from][to];
		}
		result[cost][from][to] = cost + go(from, to);
		for (int i = from; i <= to; i++) {
			if (colors[i] == colors[from - 1]) {
				result[cost][from][to] = Math.min(result[cost][from][to], go(from, i - 1) + go(cost - 1, i + 1, to));
			}
		}
		return result[cost][from][to];
	}
}
