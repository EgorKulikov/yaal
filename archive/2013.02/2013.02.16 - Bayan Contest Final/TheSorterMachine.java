package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TheSorterMachine {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		System.err.println(testNumber);
		int size = in.readInt();
		int swaps = in.readInt();
		int[] from =  new int[swaps];
		int[] to = new int[swaps];
		IOUtils.readIntArrays(in, from, to);
		for (int i = 0; i < swaps; i++) {
			if (from[i] > to[i]) {
				int temp = from[i];
				from[i] = to[i];
				to[i] = temp;
			}
		}
		MiscUtils.decreaseByOne(from, to);
		int last = Math.min(21, size);
		boolean[] good = new boolean[1 << last];
		boolean hasBad = false;
		int delta = size - last;
		for (int i = 0; i < swaps; i++) {
			from[i] -= delta;
			to[i] -= delta;
		}
		for (int i = 0; i < good.length; i++) {
			int state = i;
			for (int j = 0; j < swaps; j++) {
				if (from[j] < 0)
					continue;
				if ((state >> from[j] & 1) == 0 && (state >> to[j] & 1) == 1)
					state += (1 << from[j]) - (1 << to[j]);
			}
			good[i] = state == (1 << Integer.bitCount(i)) - 1;
			if (!good[i])
				hasBad = true;
		}
		if (!hasBad) {
			out.printLine("YES");
			return;
		}
		out.printLine("NO");
		int[] answer = new int[size];
		for (int i = 0; i < delta; i++)
			answer[i] = i + 1;
		int used = 0;
		for (int i = 0; i < last; i++) {
			for (int j = 0; j < last; j++) {
				if ((used >> j & 1) == 1)
					continue;
				answer[i + delta] = j + delta + 1;
				boolean found = false;
				for (int k = 0; k < good.length; k++) {
					if (good[k])
						continue;
					int bitsSet = Integer.bitCount(k);
					boolean valid = true;
					if (j < bitsSet && (k >> i & 1) == 0 || j >= bitsSet && (k >> i & 1) == 1)
						valid = false;
					if (valid) {
						found = true;
						break;
					}
				}
				if (found) {
					used += 1 << j;
					for (int k = 0; k < good.length; k++) {
						if (good[k])
							continue;
						int bitsSet = Integer.bitCount(k);
						if (j < bitsSet && (k >> i & 1) == 0 || j >= bitsSet && (k >> i & 1) == 1)
							good[k] = true;
					}
					break;
				}
			}
		}
		out.printLine(answer);
    }
}
