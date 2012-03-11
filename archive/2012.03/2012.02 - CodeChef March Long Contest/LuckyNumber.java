package net.egork;

import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.math.BigInteger;

public class LuckyNumber {
	private static final int MOD = 1000000007;
	static final long[] lucky = IntegerUtils.generateHappy(3);
	static final long[][] c = IntegerUtils.generateBinomialCoefficients(1001, MOD);
	static final long[] pow2 = IntegerUtils.generatePowers(2, 1001, MOD);
	static final long[] pow8 = IntegerUtils.generatePowers(8, 1001, MOD);
	static final int[] isLucky = new int[10];

	static {
		isLucky[4] = 1;
		isLucky[7] = 1;
	}

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		BigInteger from = in.readBigInteger();
		BigInteger to = in.readBigInteger();
		to = to.add(BigInteger.ONE);
		int answer = go(to.toString().toCharArray()) - go(from.toString().toCharArray());
		if (answer < 0)
			answer += MOD;
		out.printLine(answer);
	}

	private int go(char[] number) {
		long answer = 0;
		for (long count : lucky) {
			int good = (int) count;
			int bad = number.length - good;
			for (int i = 0; i < number.length && good >= 0 && bad >= 0; i++) {
				for (int j = 0; j < number[i] - '0'; j++) {
					int curGood = good - isLucky[j];
					int curBad = bad - 1 + isLucky[j];
					if (curGood >= 0 && curBad >= 0)
						answer += c[curGood + curBad][curGood] * pow2[curGood] % MOD * pow8[curBad] % MOD;
				}
				good -= isLucky[number[i] - '0'];
				bad -= 1 - isLucky[number[i] - '0'];
			}
		}
		return (int) (answer % MOD);
	}
}
