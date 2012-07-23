package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Comparator;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int place = in.readInt() - 1;
		final int[] points = new int[count];
		final int[] time = new int[count];
		IOUtils.readIntArrays(in, points, time);
		Comparator<Integer> comparator = new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				if (points[o1] != points[o2])
					return points[o2] - points[o1];
				return time[o1] - time[o2];
			}
		};
		Integer[] order = ArrayUtils.order(count, comparator);
		int answer = 0;
		for (int i = 0; i < count; i++) {
			if (comparator.compare(order[place], order[i]) == 0)
				answer++;
		}
		out.printLine(answer);
	}
}
