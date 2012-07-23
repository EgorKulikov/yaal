package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class LuckyCount {
	final long[] answer = new long[100001];

	{
		long[] count = new long[100001];
		int shift = 50000;
		count[shift] = 1;
		for (int i = 1; i <= 100000; i++) {
			int number = i;
			while (number != 0) {
				int digit = number % 10;
				if (digit == 4)
					shift++;
				else if (digit == 7)
					shift--;
				number /= 10;
			}
			answer[i] = answer[i - 1] + count[shift];
			count[shift]++;
		}
	}

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		out.printLine(answer[n]);
	}
}
