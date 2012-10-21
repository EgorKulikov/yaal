package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int start = in.readInt();
		int numYears = in.readInt();
		int[] maxSquare = new int[start + numYears];
		for (int i = 1; i * i < maxSquare.length; i++) {
			int delta = i * i;
			for (int j = delta, k = 1; j < maxSquare.length; j += delta, k++)
				maxSquare[j] = k;
		}
		long answer = 0;
		for (int i = start; i < maxSquare.length; i++)
			answer += maxSquare[i];
		out.printLine(answer);
	}
}
