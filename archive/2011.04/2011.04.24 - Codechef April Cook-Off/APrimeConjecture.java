import net.egork.numbers.IntegerUtils;
import net.egork.utils.Exit;
import net.egork.utils.Solver;

import java.io.PrintWriter;

public class APrimeConjecture implements Solver {
	private int[] primes;
	private int[] square = new int[1000000];
	private int[] cube = new int[1000000];

	public APrimeConjecture() {
		primes = IntegerUtils.generatePrimes(1000000);
		for (int p : primes) {
			if (p * p * p >= 1000000)
				break;
			for (int q : primes) {
				int index = p * p * p + q * q;
				if (index >= 1000000)
					break;
				square[index] = q;
				cube[index] = p;
			}
		}
	}

	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int number = in.readInt();
		if (number == 0) {
			Exit.exit(in, out);
			return;
		}
		for (int p : primes) {
			if (p > number)
				break;
			if (square[number - p] != 0) {
				out.println(p + " " + square[number - p] + " " + cube[number - p]);
				return;
			}
		}
		out.println("0 0 0");
	}
}

