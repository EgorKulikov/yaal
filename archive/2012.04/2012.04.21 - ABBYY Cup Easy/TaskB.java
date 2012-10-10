package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int result = 0;
		while (n != 1) {
			result += n;
			int nextN = 1;
			for (int i = 2; i * i <= n; i++) {
				if (n % i == 0) {
					nextN = n / i;
					break;
				}
			}
			n = nextN;
		}
		result++;
		out.printLine(result);
	}
}
