package net.egork.timus;

import net.egork.collections.Pair;
import net.egork.utils.io.inputreader.InputReader;
import net.egork.utils.solver.Solver;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Task1112 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int segmentCount = in.readInt();
		@SuppressWarnings({"unchecked"})
		Pair<Integer, Integer>[] segments = new Pair[segmentCount];
		for (int i = 0; i < segmentCount; i++) {
			int left = in.readInt();
			int right = in.readInt();
			if (left < right)
				segments[i] = new Pair<Integer, Integer>(right, left);
			else
				segments[i] = new Pair<Integer, Integer>(left, right);
		}
		Arrays.sort(segments, new Pair.Comparator<Integer, Integer>());
		List<Pair<Integer, Integer>> remaining = new ArrayList<Pair<Integer, Integer>>();
		int right = -999;
		for (Pair<Integer, Integer> segment : segments) {
			if (segment.second() >= right) {
				remaining.add(segment);
				right = segment.first();
			}
		}
		out.println(remaining.size());
		for (Pair<Integer, Integer> segment : remaining) {
			out.println(segment.second() + " " + segment.first());
		}
	}
}

