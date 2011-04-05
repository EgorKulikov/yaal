package net.egork.timus;

import net.egork.collections.pair.Pair;
import net.egork.utils.solver.Solver;
import net.egork.utils.io.inputreader.InputReader;

import java.io.PrintWriter;
import java.util.Arrays;

public class Task1203 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int n = in.readInt();
		@SuppressWarnings({"unchecked"})
		Pair<Integer, Integer>[] events = new Pair[n];
		for (int i = 0; i < n; i++) {
			int start = in.readInt();
			int finish = in.readInt();
			events[i] = new Pair<Integer, Integer>(finish, start);
		}
		Arrays.sort(events, new Pair.Comparator<Integer, Integer>());
		int lastFinish = 0;
		int result = 0;
		for (Pair<Integer, Integer> event : events) {
			if (event.getSecond() > lastFinish) {
				result++;
				lastFinish = event.getFirst();
			}
		}
		out.println(result);
	}
}

