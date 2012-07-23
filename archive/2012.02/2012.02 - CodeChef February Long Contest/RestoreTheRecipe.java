package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.collections.sequence.Array;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Comparator;

public class RestoreTheRecipe {
	int[] index;
	long[] delta;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int restrictionsCount = in.readInt();
		final int[] from = new int[restrictionsCount];
		final int[] to = new int[restrictionsCount];
		int[] valueInitial = new int[restrictionsCount];
		IOUtils.readIntArrays(in, from, to, valueInitial);
		long[] value = new long[restrictionsCount];
		for (int i = 0; i < restrictionsCount; i++)
			value[i] = valueInitial[i];
		Integer[] order = ArrayUtils.order(restrictionsCount, new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				if (from[o1] != from[o2])
					return from[o2] - from[o1];
				return to[o2] - to[o1];
			}
		});
		index = new int[count + 1];
		delta = new long[count + 1];
		for (int i = 0; i <= count; i++)
			index[i] = i;
		for (int i : order) {
			get(to[i]);
			value[i] -= delta[to[i]];
			to[i] = index[to[i]];
			if (to[i] == from[i] - 1) {
				if (value[i] != 0) {
					out.printLine(-1);
					return;
				}
			} else {
				index[to[i]] = from[i] - 1;
				delta[to[i]] = value[i];
			}
		}
		long[] answer = new long[count];
		long[] sum = new long[count + 1];
		for (int i = 1; i <= count; i++) {
			if (index[i] != i)
				answer[i - 1] = delta[i] - sum[i - 1] + sum[index[i]];
			if (Math.abs(answer[i - 1]) > (long)1e14)
				throw new RuntimeException();
			sum[i] = sum[i - 1] + answer[i - 1];
		}
		out.printLine(Array.wrap(answer).toArray());
	}

	private void get(int i) {
		int from = i;
		long total = 0;
		while (i != index[i]) {
			total += delta[i];
			i = index[i];
		}
		while (from != index[from]) {
			int next = index[from];
			index[from] = i;
			long nextTotal = total - delta[from];
			delta[from] = total;
			from = next;
			total = nextTotal;
		}
	}
}
