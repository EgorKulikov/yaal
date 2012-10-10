package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.math.BigInteger;

public class TaskE {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int size = in.readInt();
		BigInteger result = BigInteger.ONE;
		for (int i = 2; i <= size * size; i++)
			result = result.multiply(BigInteger.valueOf(i));
		for (int i = 2; i < 2 * size; i++) {
			for (int j = 0; j < size - Math.abs(size - i); j++)
				result = result.divide(BigInteger.valueOf(i));
		}
		out.printLine(result);
	}
}
