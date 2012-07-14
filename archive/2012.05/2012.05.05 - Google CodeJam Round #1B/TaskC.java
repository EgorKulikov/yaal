package net.egork;

import net.egork.collections.sequence.Array;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		System.err.println(testNumber);
		int count = in.readInt();
		long[] numbers = IOUtils.readLongArray(in, count);
		out.printLine("Case #" + testNumber + ":");
		Map<Long, int[]> sums = new HashMap<Long, int[]>();
		Random rand = new Random(239);
		long[][] allSums = new long[25][1 << 20];
		for (int i = 0; i < 25; i++) {
			for (int j = 0; j < (1 << 20); j++) {
				for (int k = 0; k < 20; k++) {
					if ((j >> k & 1) == 1)
						allSums[i][j] += numbers[20 * i + k];
				}
			}
		}
		System.err.println("Preprocessing ended");
		for (int i = 0; ; i++) {
			if (i == 1000000) {
				System.err.println("Next million");
				i = 0;
			}
			long sum = 0;
			int[] mask = new int[25];
			for (int j = 0; j < 25; j++)
				mask[j] = rand.nextInt(1 << 20);
			for (int k = 0; k < 25; k++) {
				sum += allSums[k][mask[k]];
			}
			if (sums.containsKey(sum)) {
				int[] otherMask = sums.get(sum);
				long[] firstArray = getArray(mask, numbers);
				long[] secondArray = getArray(otherMask, numbers);
				out.printLine(Array.wrap(firstArray).toArray());
				out.printLine(Array.wrap(secondArray).toArray());
				return;
			}
			sums.put(sum, mask);
		}
	}

	private long[] getArray(int[] mask, long[] numbers) {
		int count = 0;
		for (int i : mask)
			count += Integer.bitCount(i);
		long[] result = new long[count];
		int index = 0;
		for (int i = 0; i < numbers.length; i++) {
			if ((mask[i / 20] >> (i % 20) & 1) == 1)
				result[index++] = numbers[i];
		}
		return result;
	}
}
