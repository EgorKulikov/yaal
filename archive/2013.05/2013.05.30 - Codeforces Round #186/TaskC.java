package net.egork;

import net.egork.collections.comparators.IntComparator;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] numbers = IOUtils.readIntArray(in, count);
		ArrayUtils.sort(numbers, IntComparator.REVERSE);
		long[] sum = new long[count + 1];
		for (int i = 0; i < count; i++)
			sum[i + 1] = sum[i] + numbers[i];
		long answer = 0;
		for (int size = 1; size <= count; size *= 4)
			answer += sum[size];
		out.printLine(answer);
    }
}
