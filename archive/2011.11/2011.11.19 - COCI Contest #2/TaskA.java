package net.egork;

import net.egork.collections.sequence.Array;
import net.egork.collections.sequence.ListUtils;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int[] points = IOUtils.readIntArray(in, 8);
		Integer[] order = ListUtils.order(Array.wrap(points));
		Arrays.sort(order, 3, 8);
		int sum = 0;
		for (int i = 3; i < 8; i++)
			sum += points[order[i]];
		out.printLine(sum);
		for (int i = 0; i < 8; i++)
			order[i]++;
		out.printLine(Array.wrap(order).subList(3, 8).toArray());
	}
}
