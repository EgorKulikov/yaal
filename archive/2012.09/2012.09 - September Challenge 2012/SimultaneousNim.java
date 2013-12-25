package net.egork;

import net.egork.collections.sequence.Array;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class SimultaneousNim {
	int sum = 0;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		sum += count;
		long[] numbers = IOUtils.readLongArray(in, count);
		int maxBit = 0;
		for (long i : numbers)
			maxBit = Math.max(Long.bitCount(Long.highestOneBit(i) - 1) + 1, maxBit);
		int[] answer = solve(count, numbers, maxBit);
		out.printLine(Array.wrap(answer).toArray());
	}

	private int[] solve(int count, long[] numbers, int maxBit) {
		int[] answer = new int[count];
		int remaining = count;
		int[] bad = new int[maxBit];
		long[] masks = new long[count];
		long[] values = new long[count];
		int[] indices = new int[count];
		boolean[] stillGood = new boolean[count];
		int color = 1;
		while (remaining > 0) {
			int index = 0;
			for (int i = 0; i < count; i++) {
				if (answer[i] == 0) {
					masks[index] = 0;
					stillGood[index] = true;
					values[index] = numbers[i];
					indices[index++] = i;
				}
			}
			for (int i = 0; i < maxBit; i++) {
				int thisBad = -1;
				bad[i] = -1;
				long testBit = 1L << i;
				for (int j = 0; j < remaining; j++) {
					if (stillGood[j] && (values[j] & testBit) != 0) {
						thisBad = j;
						bad[i] = indices[j];
						break;
					}
				}
				if (thisBad == -1)
					continue;
				masks[thisBad] ^= 1L << i;
				stillGood[thisBad] = false;
				for (int j = 0; j < remaining; j++) {
					if (stillGood[j] && (values[j] & testBit) != 0) {
						masks[j] ^= masks[thisBad];
						values[j] ^= values[thisBad];
					}
				}
			}
			int best = -1;
			for (int i = 0; i < remaining; i++) {
				if (stillGood[i] && (best == -1 || Long.bitCount(masks[i]) < Long.bitCount(masks[best])))
					best = i;
			}
			int bits = Long.bitCount(masks[best]) + 1;
			int best2 = -1;
			for (int i = best; i <= best; i++) {
				for (int j = 0; j < remaining; j++) {
					if (j == best)
						continue;
					if (stillGood[j] && bits > Long.bitCount(masks[i] ^ masks[j]) + 2) {
						best = i;
						best2 = j;
						bits = Long.bitCount(masks[i] ^ masks[j]) + 2;
					}
				}
			}
			if (sum < 3000 && remaining < 600 || remaining < 300) {
				for (int i = 0; i < remaining; i++) {
					if (!stillGood[i])
						continue;
					for (int j = i + 1; j < remaining; j++) {
						if (stillGood[j] && bits > Long.bitCount(masks[i] ^ masks[j]) + 2) {
							best = i;
							best2 = j;
							bits = Long.bitCount(masks[i] ^ masks[j]) + 2;
						}
					}
				}
			} else {
				for (int i = 0; i < remaining; i++) {
					if (!stillGood[i])
						continue;
					for (int j = i + 1; j < remaining; j += 12) {
						if (stillGood[j] && bits > Long.bitCount(masks[i] ^ masks[j]) + 2) {
							best = i;
							best2 = j;
							bits = Long.bitCount(masks[i] ^ masks[j]) + 2;
						}
					}
				}
			}
			if (best2 == -1) {
				for (int i = 0; i < maxBit; i++) {
					if ((masks[best] >> i & 1) != 0)
						answer[bad[i]] = color;
				}
				answer[indices[best]] = color;
			} else {
				for (int i = 0; i < maxBit; i++) {
					if (((masks[best] ^ masks[best2]) >> i & 1) != 0)
						answer[bad[i]] = color;
				}
				answer[indices[best]] = color;
				answer[indices[best2]] = color;
			}
			remaining -= bits;
			color++;
		}
		return answer;
	}
}
