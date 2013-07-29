package net.egork;

import net.egork.collections.comparators.IntComparator;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Task {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		if (count == 0)
			throw new UnknownError();
		int[] numbers = IOUtils.readIntArray(in, count);
		ArrayUtils.sort(numbers, IntComparator.DEFAULT);
		double answer;
		if ((count & 1) == 0)
			answer = (numbers[count >> 1] + numbers[(count >> 1) - 1]) / 2d;
		else
			answer = numbers[count >> 1];
		out.printFormat("%.1f\n", answer);
    }
}
