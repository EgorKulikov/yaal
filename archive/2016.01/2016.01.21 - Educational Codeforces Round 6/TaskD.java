package net.egork;

import net.egork.generated.collections.pair.IntIntPair;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TaskD {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int[] a = IOUtils.readIntArray(in, n);
		int m = in.readInt();
		int[] b = IOUtils.readIntArray(in, m);
		long sa = ArrayUtils.sumArray(a);
		long sb = ArrayUtils.sumArray(b);
		long answer = Math.abs(sa - sb);
		List<IntIntPair> operations = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				long nsa = sa - a[i] + b[j];
				long nsb = sb - b[j] + a[i];
				long candidate = Math.abs(nsa - nsb);
				if (candidate < answer) {
					answer = candidate;
					operations = Collections.singletonList(new IntIntPair(i + 1, j + 1));
				}
			}
		}
		List<Couple> left = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < i; j++) {
				left.add(new Couple(a[i] + a[j], i + 1, j + 1));
			}
		}
		List<Couple> right = new ArrayList<>();
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < i; j++) {
				right.add(new Couple(b[i] + b[j], i + 1, j + 1));
			}
		}
		Collections.sort(left);
		Collections.sort(right);
		int at = 0;
		for (Couple fromA : left) {
			while (at < right.size()) {
				Couple fromB = right.get(at);
				long delta = sa - 2 * fromA.value - sb + 2 * fromB.value;
				if (Math.abs(delta) < answer) {
					answer = Math.abs(delta);
					operations = Arrays.asList(new IntIntPair(fromA.first, fromB.first), new IntIntPair(fromA.second,
							fromB.second));
				}
				if (delta >= 0) {
					break;
				}
				at++;
			}
			if (at == right.size()) {
				break;
			}
		}
		out.printLine(answer);
		out.printLine(operations.size());
		for (IntIntPair pair : operations) {
			out.printLine(pair.first, pair.second);
		}
	}

	static class Couple implements Comparable<Couple> {
		long value;
		int first;
		int second;

		public Couple(long value, int first, int second) {
			this.value = value;
			this.first = first;
			this.second = second;
		}

		@Override
		public int compareTo(Couple o) {
			return Long.compare(value, o.value);
		}
	}
}
