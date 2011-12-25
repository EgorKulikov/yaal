package net.egork;

import net.egork.collections.Pair;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class TaskE {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		long number = in.readLong();
		List<Pair<Long, Long>> answer = new ArrayList<Pair<Long, Long>>();
		for (long i = 2; i * i <= number; i++) {
			if (number % (i * i) == 0) {
				long x = i - 1;
				long y = x * (number / (i * i));
				answer.add(Pair.makePair(x, y));
			}
		}
		if (answer.isEmpty())
			out.printLine("NO SOLUTION");
		else {
			for (Pair<Long, Long> pair : answer) {
				out.printLine(pair.first, pair.second);
			}
		}
	}
}
