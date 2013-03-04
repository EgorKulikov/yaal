package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.collections.comparators.IntComparator;
import net.egork.collections.intcollection.IntArray;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int discountCount = in.readInt();
		int[] types = IOUtils.readIntArray(in, discountCount);
		int minDiscount = new IntArray(types).min();
		int goodCount = in.readInt();
		int[] prices = IOUtils.readIntArray(in, goodCount);
		ArrayUtils.sort(prices, IntComparator.REVERSE);
		int answer = 0;
		for (int i = 0; i < goodCount; i += minDiscount + 2) {
			for (int j = i; j < i + minDiscount && j < goodCount; j++)
				answer += prices[j];
		}
		out.printLine(answer);
	}
}
