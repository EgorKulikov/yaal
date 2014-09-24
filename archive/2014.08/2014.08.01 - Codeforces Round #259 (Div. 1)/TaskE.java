package net.egork;

import net.egork.collections.comparators.IntComparator;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskE {
	static final int BUBEN = 200;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		long[] base = new long[count];
		long[] max = new long[count];
		long[] rate = new long[count];
		IOUtils.readLongArrays(in, base, max, rate);
		int parts = (count + BUBEN - 1) / BUBEN;
		long[] lastPurge = new long[parts];
		Arrays.fill(lastPurge, -1);
		long[][] k = new long[parts][BUBEN + 1];
		long[][] b = new long[parts][BUBEN + 1];
		long[][] at = new long[parts][BUBEN + 1];
		int[] qty = new int[parts];
		int[] order = ArrayUtils.createOrder(BUBEN);
		long[] endsIn = new long[count];
		for (int i = 0; i < count; i++) {
			if (rate[i] == 0) {
				endsIn[i] = Integer.MAX_VALUE;
			} else {
				endsIn[i] = (max[i] + rate[i] - 1) / rate[i];
			}
		}
		for (int i = 0; i < parts; i++) {
			final int shift = i * BUBEN;
			if (shift + BUBEN > count) {
				order = ArrayUtils.createOrder(count - shift);
			}
			ArrayUtils.sort(order, new IntComparator() {
				@Override
				public int compare(int first, int second) {
					return Long.compare(endsIn[first + shift], endsIn[second + shift]);
				}
			});
			long sumRate = 0;
			for (int j = shift; j < count && j < shift + BUBEN; j++) {
				sumRate += rate[j];
			}
			long sumBase = 0;
			k[i][0] = sumRate;
			for (int j : order) {
				sumRate -= rate[j + shift];
				sumBase += max[j + shift];
				if (endsIn[j + shift] != at[i][qty[i]]) {
					qty[i]++;
				}
				k[i][qty[i]] = sumRate;
				b[i][qty[i]] = sumBase;
				at[i][qty[i]] = endsIn[j + shift];
			}
			qty[i]++;
		}
		int queryCount = in.readInt();
		for (int i = 0; i < queryCount; i++) {
			long time = in.readLong();
			int from = in.readInt() - 1;
			int to = in.readInt() - 1;
			int fromPart = from / BUBEN;
			int toPart = to / BUBEN;
			long result = 0;
			if (lastPurge[fromPart] != -1) {
				fixPart(base, rate, lastPurge, fromPart);
			}
			if (fromPart == toPart) {
				for (int j = from; j <= to; j++) {
					result += Math.min(base[j] + rate[j] * time, max[j]);
					base[j] = -rate[j] * time;
				}
			} else {
				for (int j = from; j < (fromPart + 1) * BUBEN; j++) {
					result += Math.min(base[j] + rate[j] * time, max[j]);
					base[j] = -rate[j] * time;
				}
				if (lastPurge[toPart] != -1) {
					fixPart(base, rate, lastPurge, toPart);
				}
				for (int j = toPart * BUBEN; j <= to; j++) {
					result += Math.min(base[j] + rate[j] * time, max[j]);
					base[j] = -rate[j] * time;
				}
				for (int j = fromPart + 1; j < toPart; j++) {
					if (lastPurge[j] == -1) {
						for (int l = j * BUBEN; l < (j + 1) * BUBEN; l++) {
							result += Math.min(base[l] + rate[l] * time, max[l]);
						}
					} else {
						int position = Arrays.binarySearch(at[j], 0, qty[j], time - lastPurge[j]);
						if (position < 0) {
							position = -position - 2;
						}
						result += k[j][position] * (time - lastPurge[j]) + b[j][position];
					}
					lastPurge[j] = time;
				}
			}
			out.printLine(result);
		}
	}

	protected void fixPart(long[] base, long[] rate, long[] lastPurge, int part) {
		for (int j = part * BUBEN; j < (part + 1) * BUBEN; j++) {
			base[j] = -rate[j] * lastPurge[part];
		}
		lastPurge[part] = -1;
	}
}
