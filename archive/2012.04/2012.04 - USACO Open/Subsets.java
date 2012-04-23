package net.egork;

import net.egork.collections.sequence.Array;
import net.egork.io.IOUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.io.StringBufferInputStream;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Subsets {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] milk = IOUtils.readIntArray(in, count);
		int total = 1 << count;
		final int[] sums = new int[total];
		for (int i = 1; i < total; i++)
			sums[i] = sums[i - Integer.highestOneBit(i)] + milk[Integer.bitCount(Integer.highestOneBit(i) - 1)];
		int[] sortedSums = sums.clone();
		Arrays.sort(sortedSums);
		int[] order = new int[total];
		for (int i = 0; i < order.length; i++)
			order[i] = i;
		Collections.sort(Array.wrap(order), new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				return IntegerUtils.longCompare(sums[o1], sums[o2]);
			}
		});
		int answer = 0;
		for (int i = 1; i < total; i++) {
			int value = sums[i];
			if ((value & 1) == 1)
				continue;
			value >>= 1;
			int index = Arrays.binarySearch(sortedSums, value);
			if (index < 0)
				continue;
			boolean found = false;
			for (int j = index; !found && j < total && sortedSums[j] == value; j++)
				found = (i & order[j]) == order[j];
			for (int j = index - 1; !found && j >= 0 && sortedSums[j] == value; j--)
				found = (i & order[j]) == order[j];
			if (found)
				answer++;
		}
		if (testNumber == 1)
			out.printLine(answer);
	}

	private void runMaxTest() {
		StringWriter sw = new StringWriter();
		OutputWriter out = new OutputWriter(sw);
		out.printLine(20);
		for (int i = 0; i < 20; i++)
			out.printLine(2);
		out.flush();
		solve(0, new InputReader(new StringBufferInputStream(sw.toString())), out);
	}
}
