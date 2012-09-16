package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
	long[] count = new long[50];
	long[] lastOne = new long[50];

	{
		count[0] = 1;
		lastOne[0] = 1;
		for (int i = 1; i < 50; i++) {
			count[i] = count[i - 1] * 8 + lastOne[i - 1];
			lastOne[i] = lastOne[i - 1] + 7 * count[i - 1];
			if (count[i] < 0 || Long.MAX_VALUE / 8 < count[i - 1])
				count[i] = lastOne[i] = Long.MAX_VALUE;
		}
	}

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		if (in.isExhausted())
			throw new UnknownError();
		long number = in.readLong() - 1;
		int digitCount = 1;
		while (true) {
			long current = 7 * count[digitCount - 1] + lastOne[digitCount - 1];
			if (number < current)
				break;
			number -= current;
			digitCount++;
		}
		boolean isLastOne = false;
		for (int i = digitCount - 1; i >= 0; i--) {
			for (int j = (i == digitCount - 1) ? 1 : 0; j < 10; j++) {
				if (j == 4 || isLastOne && j == 3)
					continue;
				long current;
				if (j == 1)
					current = lastOne[i];
				else
					current = count[i];
				if (number < current) {
					out.print(j);
					isLastOne = j == 1;
					break;
				}
				number -= current;
			}
		}
		out.printLine();
	}
}
