package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.math.BigInteger;

public class CharuAndCoinDistribution {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		BigInteger answer = BigInteger.valueOf(n + 1).multiply(BigInteger.valueOf(n + 2)).multiply(BigInteger.valueOf(n + 3)).divide(BigInteger.valueOf(6)).mod(BigInteger.valueOf(1000000007));
		out.printLine(answer);
//		out.print(go(3, 4 * n) + " ");
    }

	private int go(int step, int remaining) {
		if (step == 0)
			return 1;
		int answer = 0;
		for (int i = 0; i <= remaining; i += (1 << step))
			answer += go(step - 1, remaining - i);
		return answer;
	}
}
