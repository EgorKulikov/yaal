package net.egork;

import net.egork.collections.sequence.Array;
import net.egork.collections.sequence.ListUtils;
import net.egork.collections.set.MultiSet;
import net.egork.io.IOUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Map;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		in.readInt();
		int count = in.readInt();
		int[] array = IOUtils.readIntArray(in, count);
		long[] partialSums = new long[count];
		partialSums[0] = array[0];
		for (int i = 1; i < count; i++)
			partialSums[i] = array[i] + partialSums[i - 1];
		MultiSet<Long> set = new MultiSet<Long>();
		for (long value : partialSums)
			set.add(IntegerUtils.gcd(value, partialSums[count - 1]));
		long[] values = new long[set.entryCount()];
		int[] exists = new int[set.entryCount()];
		int index = 0;
		for (Map.Entry<Long, Integer> entry : set.getUnderlying().entrySet()) {
			values[index] = entry.getKey();
			exists[index++] = entry.getValue();
		}
		Integer[] order = ListUtils.order(Array.wrap(values));
		for (int i : order) {
			for (int j : order) {
				if (i != j && values[j] % values[i] == 0)
					exists[i] += exists[j];
			}
			if (exists[i] == partialSums[count - 1] / values[i]) {
				out.printLine(testNumber, values[i]);
				return;
			}
		}
		out.printLine(testNumber, partialSums[count - 1]);
	}
}
