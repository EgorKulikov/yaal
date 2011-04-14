package Timus.Part1;

import net.egork.numbers.IntegerUtils;
import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;

public class Task1055 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int n = in.readInt();
		int m = in.readInt();
		if (n < m) {
			out.println(0);
			return;
		}
		int[] primes = IntegerUtils.generatePrimes(n + 1);
		int result = 0;
		for (int p : primes) {
			long power = IntegerUtils.powerInFactorial(n, p) - IntegerUtils.powerInFactorial(m, p) -
				IntegerUtils.powerInFactorial(n - m, p);
			if (power != 0)
				result++;
		}
		out.println(result);
	}
}

