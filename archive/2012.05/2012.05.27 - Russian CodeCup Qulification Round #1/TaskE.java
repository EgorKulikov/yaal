package net.egork;

import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
	boolean[] isPrime = IntegerUtils.generatePrimalityTable(10000000);

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int index = in.readInt();
		for (int i = 2; ; i++) {
			if (!isPrime[i])
				continue;
			if (isPrime[2 * i - 1]) {
				index--;
				if (index == 0) {
					out.printLine(i, i, 2 * i - 1);
					return;
				}
			}
			if (isPrime[2 * i + 1]) {
				index--;
				if (index == 0) {
					out.printLine(i, i, 2 * i + 1);
					return;
				}
			}
		}
	}
}
