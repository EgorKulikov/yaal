package net.egork;

import net.egork.collections.intcollection.IntPair;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskE {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		IntPair[] first = new IntPair[count - 3];
		for (int i = 0; i < count - 3; i++) {
			int a = in.readInt() - 1;
			int b = in.readInt() - 1;
			first[i] = new IntPair(a, b);
		}
		List<IntPair> answer = solve(count, first, true);
		IntPair[] second = new IntPair[count - 3];
		for (int i = 0; i < count - 3; i++) {
			int a = in.readInt() - 1;
			int b = in.readInt() - 1;
			second[i] = new IntPair(a, b);
		}
		List<IntPair> secondAnswer = solve(count, second, false);
		Collections.reverse(secondAnswer);
		answer.addAll(secondAnswer);
		out.printLine(answer.size());
		for (IntPair pair : answer) {
			out.printLine(pair.first, pair.second);
		}
	}

	private List<IntPair> solve(int count, IntPair[] edges, boolean direct) {
		boolean[][] g = new boolean[count][count];
		for (int i = 1; i < count; i++) {
			g[i - 1][i] = g[i][i - 1] = true;
		}
		g[0][count - 1] = g[count - 1][0] = true;
		for (IntPair pair : edges) {
			g[pair.first][pair.second] = g[pair.second][pair.first] = true;
		}
		List<IntPair> answer = new ArrayList<>();
		int last = 1;
		for (int i = 2; i < count - 1; i++) {
			if (g[0][i]) {
				solve(last, i, g, answer, direct);
				last = i;
			}
		}
		solve(last, count - 1, g, answer, direct);
		return answer;
	}

	private void solve(int from, int to, boolean[][] g, List<IntPair> answer, boolean direct) {
		if (from + 1 == to) {
			return;
		}
		for (int i = from + 1; i < to; i++) {
			if (g[from][i] && g[i][to]) {
				if (direct) {
					answer.add(new IntPair(from + 1, to + 1));
				} else {
					answer.add(new IntPair(1, i + 1));
				}
				g[from][to] = g[to][from] = false;
				g[0][i] = g[i][0] = true;
				solve(from, i, g, answer, direct);
				solve(i, to, g, answer, direct);
			}
		}
	}
}
