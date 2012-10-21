package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.math.BigInteger;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int max = in.readInt() + 1;
		BigInteger[] result = new BigInteger[count + 1];
		BigInteger sum = BigInteger.ONE;
		result[0] = BigInteger.ONE;
		for (int i = 1; i <= count; i++) {
			result[i] = sum;
			sum = sum.add(result[i]);
			if (i >= max)
				sum = sum.subtract(result[i - max]);
		}
		out.printLine(sum);
	}
}
