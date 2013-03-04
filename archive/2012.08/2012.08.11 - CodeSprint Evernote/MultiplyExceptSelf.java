package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class MultiplyExceptSelf {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		long[] numbers = IOUtils.readLongArray(in, count);
		int countZeroes = 0;
		for (long i : numbers) {
			if (i == 0)
				countZeroes++;
		}
		if (countZeroes > 1) {
			for (int i = 0; i < count; i++)
				out.printLine(0);
			return;
		}
		if (countZeroes == 1) {
			int index = -1;
			long product = 1;
			for (int i = 0; i < count; i++) {
				if (numbers[i] == 0)
					index = i;
				else
					product *= numbers[i];
			}
			for (int i = 0; i < index; i++)
				out.printLine(0);
			out.printLine(product);
			for (int i = index + 1; i < count; i++)
				out.printLine(0);
			return;
		}
		long product = 1;
		for (int i = 1; i < count; i++)
			product *= numbers[i];
		out.printLine(product);
		for (int i = 1; i < count; i++) {
			product /= numbers[i];
			product *= numbers[i - 1];
			out.printLine(product);
		}
	}
}
