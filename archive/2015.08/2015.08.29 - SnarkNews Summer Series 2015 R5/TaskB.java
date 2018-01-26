package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		long n = in.readLong();
		if (n >= 3 && n <= 6) {
			out.printLine(-1);
			return;
		}
		int answer = 0;
		for (long i = 4; 3 * i * i <= n; i++) {
			boolean good = true;
			long current = n;
			while (current > 0) {
				long digit = current % i;
				if (digit < 3 || digit > 6) {
					good = false;
					break;
				}
				current /= i;
			}
			if (good) {
				answer++;
			}
		}
		for (int i = 3; i <= 6; i++) {
			for (int j = 3; j <= 6; j++) {
				long base = (n - i) / j;
				if (base * j + i == n && base > i && base > j && 3 * base >= (n + 1) / base) {
					answer++;
				}
			}
		}
		out.printLine(answer);
	}
}
