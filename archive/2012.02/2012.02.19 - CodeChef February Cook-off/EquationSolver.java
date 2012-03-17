package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class EquationSolver {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int a = in.readInt();
		int b = in.readInt();
		int c = in.readInt();
		long answer = 0;
		for (int i = 1; i * i <= a; i++) {
			if (a % i == 0) {
				answer += check(a / i, b, c, i);
				if (a != i * i)
					answer += check(i, b, c, a / i);
			}
		}
		if (a == 0) {
			if (c == 0) {
				if (b == 0)
					out.printLine(0);
				else
					out.printLine(-1);
				return;
			}
			for (int i = 1; i * i <= c; i++) {
				if (c % i == 0) {
					answer += check(0, b, c, b + i);
					if (c != i * i)
						answer += check(0, b, c, b + c / i);
				}
			}
		}
		out.printLine(answer);
	}

	private long check(int a0, int b, int c, int g) {
		if (g <= b || (a0 + c) % (g - b) != 0)
			return 0;
		int product = (a0 + c) / (g - b);
		int countPrimes = 0;
		for (int i = 2; i * i <= product; i++) {
			if (product % i == 0) {
				while (product % i == 0)
					product /= i;
				countPrimes++;
			}
		}
		if (product != 1)
			countPrimes++;
		return 1 << countPrimes;
	}
}
