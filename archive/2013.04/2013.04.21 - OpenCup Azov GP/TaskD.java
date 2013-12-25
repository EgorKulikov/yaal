package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		long[] numbers = IOUtils.readLongArray(in, count);
		Arrays.sort(numbers);
		long sum = 0;
		for (long i : numbers)
			sum += i;
		if (sum % 2 == 1)
			out.printLine(1);
		else {
			for (int i = 1; i < count; i++) {
				if (numbers[i] - numbers[i - 1] == 1 && numbers[i - 1] % 2 == 1) {
					out.printLine(1);
					return;
				}
			}
			out.printLine(2);
		}
    }
}
