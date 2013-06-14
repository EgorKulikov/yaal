package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Comparator;
import java.util.NavigableSet;
import java.util.TreeSet;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] a = IOUtils.readIntArray(in, count);
		int[] b = IOUtils.readIntArray(in, count);
		MiscUtils.decreaseByOne(a, b);
		int[] pA = position(a);
		int[] pB = position(b);
		final int[] diff = new int[count + 1];
		for (int i = 0; i < count; i++)
			diff[i] = pA[i] - pB[i];
		NavigableSet<Integer> set = new TreeSet<Integer>(new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				int value = diff[o1] - diff[o2];
				if (value != 0)
					return value;
				return o1 - o2;
			}
		});
		for (int i = 0; i < count; i++)
			set.add(i);
		for (int i = 0; i < count; i++) {
			int answer = Integer.MAX_VALUE;
			NavigableSet<Integer> head = set.headSet(count, true);
			if (!head.isEmpty())
				answer = Math.min(answer, diff[count] - diff[head.last()]);
			NavigableSet<Integer> tail = set.tailSet(count, true);
			if (!tail.isEmpty())
				answer = Math.min(answer, diff[tail.first()] - diff[count]);
			out.printLine(answer);
			diff[count]--;
			set.remove(b[i]);
			diff[b[i]] -= count;
			set.add(b[i]);
		}
	}

	private int[] position(int[] array) {
		int[] result = new int[array.length];
		for (int i = 0; i < array.length; i++)
			result[array[i]] = i;
		return result;
	}
}
