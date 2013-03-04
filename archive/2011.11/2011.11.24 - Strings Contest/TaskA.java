package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] polynom = in.readString().toCharArray();
		int x = in.readInt();
		long[] powers = new long[5];
		powers[0] = 1;
		for (int i = 1; i < 5; i++)
			powers[i] = powers[i - 1] * x;
		long sign = 1;
		long coefficient = 0;
		int power = 0;
		boolean powerMode = false;
		long answer = 0;
		for (char c : polynom) {
			if (c == '+' || c == '-') {
				answer += sign * coefficient * powers[power];
				powerMode = false;
				coefficient = 0;
				power = 0;
				if (c == '+')
					sign = 1;
				else
					sign = -1;
			} else if (Character.isDigit(c)) {
				if (powerMode)
					power = power * 10 + c - '0';
				else
					coefficient = coefficient * 10 + c - '0';
			} else if (c == 'x') {
				if (coefficient == 0)
					coefficient = 1;
				power = 1;
				powerMode = true;
			} else if (c == '^')
				power = 0;
		}
		answer += sign * coefficient * powers[power];
		out.printLine(answer);
	}
}
