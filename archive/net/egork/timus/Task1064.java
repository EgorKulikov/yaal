package net.egork.timus;

import net.egork.collections.Pair;
import net.egork.utils.io.InputReader;
import net.egork.utils.Solver;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Task1064 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int selectedElement = in.readInt();
		int comparisonCount = in.readInt();
		List<Pair<Integer, Integer>> answer = new ArrayList<Pair<Integer, Integer>>();
		boolean inSegment = false;
		int start = -1;
		for (int n = selectedElement + 1; n <= 10000; n++) {
			boolean good = true;
			int p = 0;
			int q = n - 1;
			for (int i = 1; i < comparisonCount; i++) {
				int m = (p + q) / 2;
				if (m == selectedElement) {
					good = false;
					break;
				}
				if (m < selectedElement)
					p = m + 1;
				else
					q = m - 1;
			}
			if (good && (p + q) / 2 == selectedElement) {
				if (!inSegment) {
					inSegment = true;
					start = n;
				}
			} else {
				if (inSegment) {
					answer.add(new Pair<Integer, Integer>(start, n - 1));
					inSegment = false;
				}
			}
		}
		if (inSegment)
			answer.add(new Pair<Integer, Integer>(start, 10000));
		out.println(answer.size());
		for (Pair<Integer, Integer> segment : answer)
			out.println(segment.first() + " " + segment.second());
	}
}

