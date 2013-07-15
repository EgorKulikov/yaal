package net.egork;

import net.egork.collections.comparators.IntComparator;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskF {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int teamCount = in.readInt();
		int problemCount = in.readInt();
		int index = in.readInt() - 1;
		boolean[][] solved = new boolean[teamCount][problemCount];
		for (int i = 0; i < teamCount; i++) {
			for (int j = 0; j < problemCount; j++)
				solved[i][j] = in.readInt() == 1;
		}
		final int[] points = new int[teamCount];
		final int[] problems = new int[teamCount];
		int[] cost = new int[problemCount];
		for (int i = 0; i < teamCount; i++) {
			for (int j = 0; j < problemCount; j++) {
				if (!solved[i][j])
					cost[j]++;
			}
		}
		for (int i = 0; i < teamCount; i++) {
			for (int j = 0; j < problemCount; j++) {
				if (solved[i][j]) {
					problems[i]++;
					points[i] += cost[j];
				}
			}
		}
		int[] order = ArrayUtils.createOrder(teamCount);
		ArrayUtils.sort(order, new IntComparator() {
			public int compare(int first, int second) {
				if (points[first] != points[second])
					return points[second] - points[first];
				if (problems[first] != problems[second])
					return problems[second] - problems[first];
				return first - second;
			}
		});
		for (int i = 0; i < teamCount; i++) {
			if (order[i] == index) {
				out.printLine(points[index], i + 1);
				return;
			}
		}
    }
}
