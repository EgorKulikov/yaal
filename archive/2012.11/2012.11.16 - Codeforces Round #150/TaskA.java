package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] array = IOUtils.readIntArray(in, count);
		int[][] nextBit = new int[count][20];
		Arrays.fill(nextBit[count - 1], count);
		for (int i = count - 1; i >= 0; i--) {
			if (i != count - 1)
				System.arraycopy(nextBit[i + 1], 0, nextBit[i], 0, 20);
			for (int j = 0; j < 20; j++) {
				if ((array[i] >> j & 1) == 1)
					nextBit[i][j] = i;
			}
		}
		boolean[] present = new boolean[1 << 20];
		for (int i = 0; i < count; i++) {
			present[array[i]] = true;
			int current = array[i];
			int to = i + 1;
			while (to != count) {
				int nextTo = count;
				for (int j = 0; j < 20; j++) {
					if ((current >> j & 1) == 0)
						nextTo = Math.min(nextTo, nextBit[to][j]);
				}
				if (nextTo != count) {
					current |= array[nextTo];
					to = nextTo + 1;
					present[current] = true;
				} else
					break;
			}
		}
		int answer = 0;
		for (boolean b : present) {
			if (b)
				answer++;
		}
		out.printLine(answer);
	}
}
