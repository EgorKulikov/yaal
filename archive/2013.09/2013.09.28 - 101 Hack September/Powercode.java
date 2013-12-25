package net.egork;

import net.egork.misc.ArrayUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.string.StringUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class Powercode {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		String[] codes = new String[count];
		for (int i = 0; i < count; i++) {
			do {
				codes[i] = in.readLine(false);
			} while (codes[i].length() == 0);
		}
		int keyCount = in.readInt();
		String[] keys = new String[keyCount];
		for (int i = 0; i < keyCount; i++) {
			do {
				keys[i] = in.readLine();
			} while (keys[i].length() == 0);
		}
		int[] values = new int[count];
		for (int i = 0; i < count; i++) {
			boolean[] toRemove = new boolean[codes[i].length()];
			for (int j = 0; j < keyCount; j++) {
				String all = keys[j] + codes[i];
				int[] z = StringUtils.zAlgorithm(all);
				int to = 0;
				for (int k = 0; k < codes[i].length(); k++) {
					if (z[k + keys[j].length()] >= keys[j].length())
						to = k + keys[j].length();
					if (k < to)
						toRemove[k] = true;
				}
			}
			values[i] = codes[i].length() - ArrayUtils.count(toRemove, true);
		}
		int gcd = 0;
		for (int i : values)
			gcd = IntegerUtils.gcd(gcd, i);
		if (gcd != 1) {
			out.printLine(-1);
			return;
		}
		while (values[0] == 0)
			values = Arrays.copyOfRange(values, 1, values.length);
		long[] at = new long[values[0]];
		Arrays.fill(at, Long.MAX_VALUE);
		at[0] = 0;
		for (int i = 1; i < values.length; i++) {
			if (values[i] == 0)
				continue;
			int shift = IntegerUtils.gcd(values[0], values[i]);
			for (int remainder = 0; remainder < shift; remainder++) {
				long current = Long.MAX_VALUE;
				if (remainder == 0)
					current = 0;
				else {
					int index = remainder;
					while (index < values[0]) {
						if (at[index] < current)
							current = at[index];
						index += shift;
					}
				}
				if (current != Long.MAX_VALUE) {
					int stepCount = values[0] / shift;
					for (int j = 0; j < stepCount; j++) {
						current += values[i];
						int index = (int) (current % values[0]);
						if (at[index] < current)
							current = at[index];
						at[index] = current;
					}
				}
			}
		}
		long max = 0;
		for (long i : at)
			max = Math.max(max, i);
		long answer = max - values[0];
		out.printLine(answer);
	}
}
