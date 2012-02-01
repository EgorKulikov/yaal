package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NavigableSet;
import java.util.TreeSet;

public class Numbers {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int from = in.readInt();
		int to = in.readInt();
		int count = in.readInt();
		int[] numbers = IOUtils.readIntArray(in, count);
		int[][] cost = IOUtils.readIntTable(in, count, 4);
		final int[] result = new int[100001];
		Arrays.fill(result, Integer.MAX_VALUE / 2);
		result[from] = 0;
		NavigableSet<Integer> queue = new TreeSet<Integer>(new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				int value = result[o1] - result[o2];
				if (value != 0)
					return value;
				return o1 - o2;
			}
		});
		queue.add(from);
		while (!queue.isEmpty()) {
			int current = queue.pollFirst();
			for (int i = 0; i < count; i++) {
				if (current + numbers[i] <= 100000)
					add(queue, result, current + numbers[i], result[current] + cost[i][0]);
				if (current - numbers[i] >= 0)
					add(queue, result, current - numbers[i], result[current] + cost[i][1]);
				if (current * numbers[i] <= 100000)
					add(queue, result, current * numbers[i], result[current] + cost[i][2]);
				add(queue, result, current / numbers[i], result[current] + cost[i][3]);
			}
		}
		out.printLine(result[to]);
	}

	private void add(NavigableSet<Integer> queue, int[] result, int number, int cost) {
		if (result[number] <= cost)
			return;
		queue.remove(number);
		result[number] = cost;
		queue.add(number);
	}
}
