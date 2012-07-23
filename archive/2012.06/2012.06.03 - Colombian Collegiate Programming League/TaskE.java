package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int a = in.readInt();
		int b = in.readInt();
		if (a == 0 && b == 0)
			throw new UnknownError();
		int difference = Math.abs(a - b);
		int answer = 0;
		for (int i = 1; i * i <= difference; i++) {
			if (difference % i == 0) {
				if (difference != i * i)
					answer += 2;
				else
					answer++;
			}
		}
		out.printLine(answer);
	}
}
