package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.math.BigInteger;

public class TaskE {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int maxLength = in.readInt();
		int alphabetSize = in.readInt();
		BigInteger variants = BigInteger.ONE;
		BigInteger answer = BigInteger.ZERO;
		BigInteger size = BigInteger.valueOf(alphabetSize);
		BigInteger delta = size.pow(maxLength / 2);
		if (maxLength % 2 == 0)
			variants = variants.add(delta);
		for (int i = maxLength / 2 + 1; i <= maxLength; i++) {
			delta = delta.multiply(size);
			answer = answer.add(delta);
		}
		out.printLine(answer);
		out.printLine(variants);
	}
}
