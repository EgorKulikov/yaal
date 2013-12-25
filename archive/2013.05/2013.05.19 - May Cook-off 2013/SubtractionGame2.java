package net.egork;

import net.egork.io.IOUtils;
import net.egork.numbers.MultiplicativeFunction;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class SubtractionGame2 {
	long[] mu = MultiplicativeFunction.MOBIUS.calculateUpTo(10001);

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] numbers = IOUtils.readIntArray(in, count);
		long answer = (1L << count) - 1;
		for (int i = 2; i <= 10000; i++) {
			if (mu[i] == 0)
				continue;
			int curCount = 0;
			for (int j : numbers) {
				if (j % i == 0)
					curCount++;
			}
			answer += mu[i] * ((1L << curCount) - 1);
		}
		out.printLine(answer);
    }
}
