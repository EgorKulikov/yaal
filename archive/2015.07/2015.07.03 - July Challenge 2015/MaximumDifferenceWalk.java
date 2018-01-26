package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class MaximumDifferenceWalk {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int size = in.readInt();
		int[][] cost = IOUtils.readIntTable(in, size, size);
		int[] maxAvailable = new int[size];
		fillMax(maxAvailable, cost);
		int from = -1;
		int to = -1;
		int best = Integer.MIN_VALUE;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (cost[i][j] != 0 && cost[i][j] - maxAvailable[j] > best) {
					best = cost[i][j] - maxAvailable[j];
					from = i;
					to = j;
				}
			}
		}
		out.printLine(from + 1, to + 1);
		cost[from][to] = 0;
		out.flush();
		while (true) {
			from = in.readInt() - 1;
			to = in.readInt() - 1;
			if (from == -1 || to == -1) {
				return;
			}
			cost[from][to] = 0;
			fillMax(maxAvailable, cost);
			if (maxAvailable[to] == 0) {
				break;
			}
			best = Integer.MIN_VALUE;
			int current = to;
			for (int i = 0; i < size; i++) {
				if (cost[current][i] != 0 && cost[current][i] - maxAvailable[i] > best) {
					best = cost[current][i] - maxAvailable[i];
					from = current;
					to = i;
				}
			}
			out.printLine(from + 1, to + 1);
			out.flush();
			cost[from][to] = 0;
			if (maxAvailable[to] == 0) {
				break;
			}
		}
	}

	private void fillMax(int[] maxAvailable, int[][] cost) {
		for (int i = 0; i < maxAvailable.length; i++) {
			maxAvailable[i] = ArrayUtils.maxElement(cost[i]);
		}
	}
}
