package net.egork;

import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskC {
	final static int N = 32;

	long[] result = new long[N + 1];

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		long modulo = in.readLong();
		int[] qty = new int[N + 1];
		long current = 0;
		int used = 0;
		for (int i = 1; i <= N; i++) {
			if (modulo - current + used <= 200) {
				qty[N] = (int) (modulo - current);
				current = modulo;
				break;
			}
			for (int j = 0; j + used <= 200; j++) {
				qty[i] = j;
				long value = solve(qty, modulo);
				if (value > modulo) {
					qty[i]--;
					break;
				} else {
					current = value;
				}
			}
			used += qty[i];
		}
		if (current != modulo) {
			throw new RuntimeException();
		}
		IntList answer = new IntArrayList();
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < qty[i]; j++) {
				answer.add(i);
			}
		}
		out.printLine(answer.size(), N);
		out.printLine(answer);
    }

	private long solve(int[] qty, long modulo) {
		Arrays.fill(result, 0);
		result[0] = 1;
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < qty[i]; j++) {
				for (int k = N - i; k >= 0; k--) {
					result[k + i] = Math.min(modulo + 1, result[k + i] + result[k]);
				}
			}
		}
		return result[N];
	}
}
