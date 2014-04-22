package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int size = in.readInt();
		int[] numbers = IOUtils.readIntArray(in, 1 << size);
		int[] next = new int[1 << size];
		long[] more = new long[size];
		long[] less = new long[size];
		boolean[] state = new boolean[size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < (1 << size); j += 1 << (i + 1)) {
				int middle = j + (1 << i);
				int end = middle + (1 << i);
				int at1 = j;
				int at2 = middle;
				int at = j;
				while (at1 < middle && at2 < end) {
					if (numbers[at1] <= numbers[at2]) {
						next[at++] = numbers[at1++];
						more[i] += at2 - middle;
					} else {
						next[at++] = numbers[at2++];
					}
				}
				while (at1 < middle) {
					next[at++] = numbers[at1++];
					more[i] += 1 << i;
				}
				while (at2 < end) {
					next[at++] = numbers[at2++];
				}
				at1 = j;
				at2 = middle;
				at = j;
				while (at1 < middle && at2 < end) {
					if (numbers[at1] < numbers[at2]) {
						next[at++] = numbers[at1++];
					} else {
						next[at++] = numbers[at2++];
						less[i] += at1 - j;
					}
				}
				while (at1 < middle) {
					next[at++] = numbers[at1++];
				}
				while (at2 < end) {
					next[at++] = numbers[at2++];
					less[i] += 1 << i;
				}
			}
			int[] temp = numbers;
			numbers = next;
			next = temp;
		}
		long answer = 0;
		for (long l : more)
			answer += l;
		int queryCount = in.readInt();
		for (int i = 0; i < queryCount; i++) {
			int query = in.readInt();
			for (int j = 0; j < query; j++) {
				if (state[j]) {
					answer += more[j];
					answer -= less[j];
				} else {
					answer += less[j];
					answer -= more[j];
				}
				state[j] ^= true;
			}
			out.printLine(answer);
		}
    }
}
