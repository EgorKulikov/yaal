package net.egork;

import net.egork.utils.io.InputReader;
import java.io.PrintWriter;
import java.math.BigInteger;

public class TaskB {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int count = in.readInt();
		BigInteger result = BigInteger.ONE;
		for (int i = 0; i < count; i++)
			result = result.add(BigInteger.ONE).multiply(BigInteger.valueOf(2));
		out.println(result);
	}
}
