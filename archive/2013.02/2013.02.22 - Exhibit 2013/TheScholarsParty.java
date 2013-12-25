package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.collections.comparators.IntComparator;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TheScholarsParty {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		final int[] years = new int[count];
		final int[] papers = new int[count];
		IOUtils.readIntArrays(in, years, papers);
		int[] order = ArrayUtils.createOrder(count);
		ArrayUtils.sort(order, new IntComparator() {
			public int compare(int first, int second) {
				if (years[first] != years[second])
					return years[first] - years[second];
				return papers[second] - papers[first];
			}
		});
		int[] leastPapers = new int[count];
		int total = 0;
		for (int i : order) {
			int position = Arrays.binarySearch(leastPapers, 0, total, papers[i]);
			if (position >= 0)
				continue;
			position = -position - 1;
			leastPapers[position] = papers[i];
			if (position == total)
				total++;
		}
		out.printLine(total);
    }
}
