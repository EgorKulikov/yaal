package on2012_12.on2012_12_19_Volume_4._1303___Minimal_Coverage;



import net.egork.collections.Pair;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Task1303 {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int limit = in.readInt();
		List<Pair<Integer, Integer>> list = new ArrayList<Pair<Integer, Integer>>();
		while (!in.isExhausted()) {
			Pair<Integer, Integer> pair = IOUtils.readIntPair(in);
			if (pair.first > pair.second)
				pair = Pair.makePair(pair.second, pair.first);
			list.add(pair);
		}
		Collections.sort(list);
		int current = 0;
		int max = 0;
		List<Pair<Integer, Integer>> answer = new ArrayList<Pair<Integer, Integer>>();
		Pair<Integer, Integer> maxAt = null;
		for (Pair<Integer, Integer> pair : list) {
			if (pair.first > current) {
				if (pair.first > max) {
					if (max >= limit)
						break;
					out.printLine("No solution");
					return;
				}
				answer.add(maxAt);
				current = max;
				if (current >= limit)
					break;
			}
			if (pair.second > max) {
				max = pair.second;
				maxAt = pair;
			}
		}
		if (max < limit) {
			out.printLine("No solution");
			return;
		}
		if (current < limit)
			answer.add(maxAt);
		out.printLine(answer.size());
		for (Pair<Integer, Integer> pair : answer) {
			out.printLine(pair.first, pair.second);
		}
	}
}
