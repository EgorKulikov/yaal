package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.collections.sequence.Array;
import net.egork.collections.sequence.ListUtils;
import net.egork.numbers.Rational;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;
import java.util.Comparator;

public class TaskD {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		final Rational[] time = new Rational[count];
		final int[] volume = new int[count];
		for (int i = 0; i < count; i++) {
			volume[i] = in.readInt();
			int speed = in.readInt();
			time[i] = new Rational(volume[i], speed);
		}
		Integer[] order = ArrayUtils.generateOrder(count);
		Arrays.sort(order, new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				int value = time[o1].compareTo(time[o2]);
				if (value != 0)
					return value;
				value = volume[o1] - volume[o2];
				if (value != 0)
					return value;
				return o1 - o2;
			}
		});
		ListUtils.increment(Array.wrap(order));
		out.printLine(Array.wrap(order).toArray());
	}
}
