package net.egork;

import net.egork.collections.Pair;
import net.egork.collections.set.TreapSet;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.NavigableSet;

public class Median {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int minMedian = in.readInt();
		int[] heights = IOUtils.readIntArray(in, count);
		int[] delta = new int[2 * count + 1];
		int curDelta = count;
		for (int i = 0; i < count; i++) {
			if (heights[i] >= minMedian)
				curDelta++;
			else
				curDelta--;
			delta[curDelta]++;
		}
		NavigableSet<Pair<Integer, Integer>> removed = new TreapSet<Pair<Integer, Integer>>();
		for (int i = 2 * count - 1; i >= 0; i--)
			delta[i] += delta[i + 1];
		long answer = 0;
		curDelta = count;
		for (int i = 0; i < count; i++) {
			answer += delta[curDelta] - removed.tailSet(Pair.makePair(curDelta, -1)).size();
			if (heights[i] >= minMedian)
				curDelta++;
			else
				curDelta--;
			removed.add(Pair.makePair(curDelta, i));
		}
		out.printLine(answer);
	}
}
