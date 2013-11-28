package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class SpecialDigit {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int number = in.readInt();
		int answer = 0;
		for (int i = 1; i * i <= number; i++) {
			if (number % i == 0) {
				if (check(i))
					answer++;
				if (i * i != number && check(number / i))
					answer++;
			}
		}
		out.printLine(answer);
    }

	private boolean check(int number) {
		while (number != 0) {
			int digit = number % 10;
			if (digit == 3 || digit == 5 || digit == 6)
				return true;
			number /= 10;
		}
		return false;
	}
}
