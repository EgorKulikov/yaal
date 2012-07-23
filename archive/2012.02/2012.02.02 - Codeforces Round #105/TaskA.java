package net.egork;

import net.egork.io.IOUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int[] divisors = IOUtils.readIntArray(in, 4);
		int count = in.readInt();
		int answer = 0;
		for (int i = 1; i < 16; i++) {
			int divisor = 1;
			for (int j = 0; j < 4; j++) {
				if ((i >> j & 1) == 1)
					divisor = (int) IntegerUtils.lcm(divisor, divisors[j]);
			}
			if (Integer.bitCount(i) % 2 == 1)
				answer += count / divisor;
			else
				answer -= count / divisor;
		}
		out.printLine(answer);
	}
}
