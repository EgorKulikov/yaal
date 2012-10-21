package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class DivisiblePairs {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int limit = in.readInt();
		int sum = in.readInt();
		long smallCount = limit / sum;
		long bigCount = smallCount + 1;
		long bigPerMod = limit % sum;
		long smallPerMod = sum - bigPerMod;
		long numberCount = limit + 1;
		long answer = (numberCount / sum) * (smallCount * smallPerMod + bigCount * bigPerMod);
		numberCount %= sum;
		answer += Math.min(numberCount, smallPerMod) * smallCount;
		numberCount -= Math.min(numberCount, smallPerMod);
		answer += numberCount * bigCount;
		answer -= smallCount;
		if (sum % 2 == 0)
			answer -= limit / (sum / 2);
		else
			answer -= limit / sum;
		answer /= 2;
		out.printLine(answer);
	}
}
