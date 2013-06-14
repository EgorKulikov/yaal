package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class XORSum {
	static final long MOD = 1000000009;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] numbers = IOUtils.readIntArray(in, count);
		long answer = 0;
		for (int i = 29; i >= 0; i--) {
			long resultEven = 1;
			long resultOdd = 0;
			long resultEvenSkipped = 0;
			long resultOddSkipped = 0;
			long result = 1;
			int mask = (1 << i) - 1;
			int oddness = 0;
			for (int j : numbers) {
				if ((j >> i & 1) == 1) {
					oddness ^= 1;
					long multiplier = (j & mask) + 1;
					long nextResultEven = resultOdd * multiplier % MOD;
					long nextResultOdd = resultEven * multiplier % MOD;
					long nextResultEvenSkipped = (resultEven + (resultEvenSkipped << i) + resultOddSkipped * multiplier) % MOD;
					long nextResultOddSkipped = (resultOdd + (resultOddSkipped << i) + resultEvenSkipped * multiplier) % MOD;
					resultEven = nextResultEven;
					resultOdd = nextResultOdd;
					resultEvenSkipped = nextResultEvenSkipped;
					resultOddSkipped = nextResultOddSkipped;
				} else {
					result *= (j & mask) + 1;
					result %= MOD;
				}
			}
			if (oddness == 0)
				answer += resultEvenSkipped * result % MOD;
			else
				answer += resultOddSkipped * result % MOD;
		}
		answer++;
		answer %= MOD;
		out.printLine(answer);
    }
}
