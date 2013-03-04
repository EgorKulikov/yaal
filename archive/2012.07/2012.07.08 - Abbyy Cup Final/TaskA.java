package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Comparator;
import java.util.NavigableSet;
import java.util.TreeSet;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		final int[] current = new int[count + 1];
		int[] taskCount = new int[count];
		long[] x = new long[count];
		long[] y = new long[count];
		long[] m = new long[count];
		int totalTasks = 0;
		for (int i = 0; i < count; i++) {
			taskCount[i] = in.readInt();
			current[i] = in.readInt();
			x[i] = in.readInt();
			y[i] = in.readInt();
			m[i] = in.readInt();
			totalTasks += taskCount[i];
		}
		int[] pointer = new int[count];
		NavigableSet<Integer> set = new TreeSet<Integer>(new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				if (current[o1] != current[o2])
					return current[o1] - current[o2];
				return o2 - o1;
			}
		});
		int[] resourceNeed = new int[0];
		int[] scientistIndex = new int[0];
		if (totalTasks <= 200000) {
			resourceNeed = new int[totalTasks];
			scientistIndex = new int[totalTasks];
		}
		int bad = 0;
		for (int i = 0; i < count; i++) {
			long c = current[i];
			int curBad = 0;
			for (int j = 1; j < taskCount[i]; j++) {
				long cc = (c * x[i] + y[i]) % m[i];
				if (cc < c)
					curBad++;
				c = cc;
			}
			bad = Math.max(bad, curBad);
		}
		if (totalTasks > 200000) {
			out.printLine(bad);
			return;
		}
		for (int i = 0; i < count; i++)
			set.add(i);
		for (int i = 0; i < totalTasks; i++) {
			Integer index = set.tailSet(count, false).pollFirst();
			if (index == null)
				index = set.pollFirst();
			scientistIndex[i] = index + 1;
			resourceNeed[i] = current[index];
			current[count] = current[index];
			if (++pointer[index] != taskCount[index]) {
				current[index] = (int) ((current[index] * x[index] + y[index]) % m[index]);
				set.add(index);
			}
		}
		out.printLine(bad);
		for (int i = 0; i < totalTasks; i++)
			out.printLine(resourceNeed[i], scientistIndex[i]);
	}
}
