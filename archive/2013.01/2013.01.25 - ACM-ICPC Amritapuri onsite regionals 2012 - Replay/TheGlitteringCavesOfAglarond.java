package net.egork;

import net.egork.collections.comparators.IntComparator;
import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TheGlitteringCavesOfAglarond {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		int moves = in.readInt();
		char[][] exhibit = IOUtils.readTable(in, rowCount, columnCount);
		IntList delta = new IntArrayList();
		int minDelta = Integer.MAX_VALUE;
		int current = 0;
		for (char[] row : exhibit) {
			int on = 0;
			for (char c : row) {
				if (c == '*')
					on++;
			}
			if (on * 2 < columnCount)
				delta.add(columnCount - on * 2);
			current += on;
			minDelta = Math.min(minDelta, Math.abs(columnCount - on * 2));
		}
		delta.inPlaceSort(IntComparator.REVERSE);
		for (int i = 0; i < delta.size() && i < moves; i++)
			current += delta.get(i);
		moves -= delta.size();
		if (moves > 0 && moves % 2 == 1)
			current -= minDelta;
		out.printLine(current);
	}
}
