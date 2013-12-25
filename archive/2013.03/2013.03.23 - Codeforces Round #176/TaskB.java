package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] at = new int[2 * count - 1];
		Arrays.fill(at, -1);
		for (int i = 0; i < count; i++)
			at[i] = i + 1;
		for (int i = 2; i <= count; i++) {
			int current = i - 2;
			int end = current + count;
			int value = at[current];
			for (int j = current + i; j < end; j += i) {
				int next = at[j];
				at[j] = value;
				value = next;
			}
			at[end] = value;
		}
		out.printLine(Arrays.copyOfRange(at, count - 1, 2 * count - 1));
    }
}
