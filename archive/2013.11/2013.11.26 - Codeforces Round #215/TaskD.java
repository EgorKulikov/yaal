package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int limit = in.readInt();
		int count = in.readInt();
		int delta = in.readInt();
		int[] at = new int[limit];
		for (int i = 0; i < count; i++) {
			int size = in.readInt();
			for (int j = 0; j < size; j++)
				at[in.readInt() - 1] = i;
		}
		int[] last = new int[count];
		Arrays.fill(last, -1);
		boolean[] bad = new boolean[1 << count];
		for (int i = 0; i < limit; i++) {
			last[at[i]] = i;
			if (i >= delta - 1) {
				int mask = 0;
				for (int j = 0; j < count; j++) {
					if (i - last[j] < delta)
						mask += 1 << j;
				}
				bad[mask] = true;
			}
		}
		int answer = count;
		int[] bitCount = new int[1 << count];
		for (int i = 1; i < (1 << count); i++) {
			bitCount[i] = bitCount[i >> 1] + (i & 1);
			if (bad[i]) {
				for (int j = 0; j < count; j++)
					bad[i | (1 << j)] = true;
			} else
				answer = Math.min(answer, count - bitCount[i]);
		}
		out.printLine(answer);
    }
}
