package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int[] primes = IntegerUtils.generatePrimes(58);
		int[] mask = new int[58];
		for (int i = 1; i < 58; i++) {
			int current = 0;
			for (int j = 0; j < 16; j++) {
				if (i % primes[j] == 0) {
					current += 1 << j;
				}
			}
			mask[i] = current;
		}
		int count = in.readInt();
		int[] base = IOUtils.readIntArray(in, count);
		int[][] answer = new int[count + 1][1 << 16];
		int[][] last = new int[count + 1][1 << 16];
		ArrayUtils.fill(answer, Integer.MAX_VALUE);
		answer[0][0] = 0;
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < (1 << 16); j++) {
				if (answer[i][j] == Integer.MAX_VALUE) {
					continue;
				}
				for (int k = 1; k < 58; k++) {
					if ((j & mask[k]) == 0) {
						if (answer[i + 1][j + mask[k]] > answer[i][j] + Math.abs(k - base[i])) {
							answer[i + 1][j + mask[k]] = answer[i][j] + Math.abs(k - base[i]);
							last[i + 1][j + mask[k]] = k;
						}
					}
				}
			}
		}
		int at = ArrayUtils.minPosition(answer[count]);
		int[] result = new int[count];
		for (int i = count; i > 0; i--) {
			result[i - 1] = last[i][at];
			at -= mask[last[i][at]];
		}
		out.printLine(result);
    }
}
