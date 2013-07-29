package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.math.BigInteger;

public class LyraAndTheAlethiometer {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] s = in.readString().toCharArray();
		BigInteger answer = BigInteger.ZERO;
		for (int i = 0; i < s.length; i++) {
			BigInteger result = BigInteger.ZERO;
			boolean letter = false;
			for (int j = i; j < s.length; j++) {
				if (Character.isLetter(s[j])) {
					if (letter)
						break;
					else {
						letter = true;
						result = result.multiply(BigInteger.TEN).add(BigInteger.valueOf(9));
					}
				} else {
					result = result.multiply(BigInteger.TEN).add(BigInteger.valueOf(s[j] - '0'));
				}
			}
			answer = answer.max(result);
		}
		out.printLine(answer);
    }
}
