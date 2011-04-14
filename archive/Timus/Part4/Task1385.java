package Timus.Part4;

import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;
import java.math.BigInteger;

public class Task1385 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int n = in.readInt();
		BigInteger total = BigInteger.TEN.pow(n).subtract(BigInteger.ONE);
		BigInteger less = BigInteger.TEN.pow(n - 1).subtract(BigInteger.ONE);
		BigInteger result = total.add(total.divide(BigInteger.valueOf(2))).add(total.divide(BigInteger.valueOf(5)));
		if (n >= 2)
			result = result.add(total.divide(BigInteger.valueOf(4)));
		if (n >= 3)
			result = result.add(total.divide(BigInteger.valueOf(8)));
		result = result.subtract(less).subtract(less).subtract(less);
		if (n >= 2)
			result = result.subtract(less);
		if (n >= 3)
			result = result.subtract(less);
		out.println(result);
	}
}

