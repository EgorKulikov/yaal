package net.egork;

import net.egork.collections.Pair;
import net.egork.collections.map.Counter;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		Pair<Integer, Integer>[] pairs = IOUtils.readIntPairArray(in, count);
		Counter<Pair<Integer, Integer>> counter = new Counter<Pair<Integer, Integer>>();
		for (Pair<Integer, Integer> pair : pairs) {
			counter.add(pair);
		}
		for (Pair<Integer, Integer> pair : pairs) {
			if (!counter.get(pair).equals(counter.get(Pair.makePair(pair.second, pair.first)))) {
				out.printLine("NO");
				return;
			}
		}
		out.printLine("YES");
	}
}
