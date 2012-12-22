package net.egork;

import net.egork.collections.Pair;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.NavigableSet;
import java.util.TreeSet;

public class TaskD {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		in.readInt();
		int count = in.readInt();
		NavigableSet<Pair<Integer, Integer>> left = new TreeSet<Pair<Integer, Integer>>();
		NavigableSet<Pair<Integer, Integer>> right = new TreeSet<Pair<Integer, Integer>>();
		out.print(testNumber, (count + 1) / 2);
		for (int i = 0; i < count; i++) {
			int number = in.readInt();
			right.add(Pair.makePair(number, i));
			if (!left.isEmpty() && right.first().first < left.last().first) {
				right.add(left.pollLast());
				left.add(right.pollFirst());
			}
			if (right.size() > left.size() + 1)
				left.add(right.pollFirst());
			if ((i & 1) == 0) {
				if (i % 20 == 0)
					out.print("\n" + right.first().first);
				else
					out.print("", right.first().first);
			}
		}
		out.printLine();
	}
}
