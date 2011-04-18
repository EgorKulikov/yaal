package Timus.Part3;

import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;
import java.math.BigInteger;

public class Task1222 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {		int n = in.readInt();
		BigInteger result;
		if (n == 0)
			result = BigInteger.ZERO;
		else if (n % 3 == 0)
			result = BigInteger.valueOf(3).pow(n / 3);
		else if (n == 1)
			result = BigInteger.ONE;
		else if (n % 3 == 1)
			result = BigInteger.valueOf(3).pow(n / 3 - 1).multiply(BigInteger.valueOf(4));
		else
			result = BigInteger.valueOf(3).pow(n / 3).multiply(BigInteger.valueOf(2));
		out.println(result);
	}
}

