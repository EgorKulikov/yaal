package net.egork;

import net.egork.io.IOUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int number = in.readInt();
		if (number == 1) {
			out.printLine("YES");
			out.printLine(1);
			return;
		}
		if (!IntegerUtils.isPrime(number)) {
			out.printLine("NO");
			return;
		}
		long[] reverse = IntegerUtils.generateReverse(number, number);
		out.printLine("YES");
		out.printLine(1);
		for (int i = 1; i < number - 1; i++) {
			out.printLine(1 + reverse[i]);
		}
		out.printLine(number);
    }
}
