package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.math.BigInteger;
import java.util.Arrays;

public class Catalonian {
	BigInteger[][] answer = new BigInteger[2][61];
	BigInteger[][] answerChildren = new BigInteger[2][61];

	public Catalonian() {
		for (BigInteger[] x : answerChildren) Arrays.fill(x, BigInteger.ZERO);
		answerChildren[0][0] = BigInteger.ONE;
		answerChildren[1][0] = BigInteger.ONE;
		for (int n = 1; n <= 60; ++n) {
			if (n == 1) {
				answer[1][n] = BigInteger.ONE;
			} else {
				answer[1][n] = answerChildren[1][n - 1];
			}
			for (int existing = 60; existing >= 0; --existing) {
				BigInteger prod = BigInteger.ONE;
				for (int times = 1; existing + times * n <= 60; ++times) {
					prod = prod.multiply(answer[1][n].add(BigInteger.valueOf(times - 1))).divide(BigInteger.valueOf(times));
					answerChildren[0][existing + times * n] = answerChildren[0][existing + times * n].add(answerChildren[0][existing].multiply(prod));
				}
			}
			answer[0][n] = answerChildren[0][n];
			for (int existing = 60; existing >= 0; --existing) {
				BigInteger prod = BigInteger.ONE;
				for (int times = 1; existing + times * n <= 60; ++times) {
					prod = prod.multiply(answer[0][n].add(BigInteger.valueOf(times - 1))).divide(BigInteger.valueOf(times));
					answerChildren[1][existing + times * n] = answerChildren[1][existing + times * n].add(answerChildren[1][existing].multiply(prod));
				}
			}
		}
	}

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		if (n == 0) throw new UnknownError();
		out.printLine(answer[0][n]);
    }
}
