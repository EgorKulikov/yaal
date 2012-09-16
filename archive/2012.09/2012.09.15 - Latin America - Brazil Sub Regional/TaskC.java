package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		if (in.isExhausted())
			throw new UnknownError();
		int count = in.readInt();
		long[] numbers = IOUtils.readLongArray(in, count);
		long[] current = numbers.clone();
		long[] next = new long[count];
		for (int i = 1; i < count; i++) {
			for (int j = 0; j < count - i; j++)
				next[j] = Math.max(numbers[j] - current[j + 1], numbers[j + i] - current[j]);
			long[] temp = current;
			current = next;
			next = temp;
		}
		long sum = 0;
		for (long i : numbers)
			sum += i;
		out.printLine((sum + current[0]) / 2);
	}
}
