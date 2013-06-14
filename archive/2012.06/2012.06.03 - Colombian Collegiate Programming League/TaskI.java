package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskI {
//	boolean[][] answer = new boolean[1001][];

	{
/*		for (int i = 0; i <= 1000; i++)
			answer[i] = new boolean[i + 1];
		answer[0][0] = true;
		for (int i = 1; i <= 1000; i++) {
			for (int j = 1; j <= i; j++) {
				answer[i][j] = true;
				for (int k = 1; k <= j; k++) {
					if (answer[i - k][Math.min(i - k, 2 * k)]) {
						answer[i][j] = false;
						break;
					}
				}
			}
		}
		StringBuilder result = new StringBuilder();
		for (int i = 2; i <= 1000; i++) {
			boolean current = false;
			for (int j = 1; j < i; j++) {
				if (answer[i - j][Math.min(i - j, 2 * j)]) {
					current = true;
					break;
				}
			}
			if (current)
				result.append("A");
			else
				result.append("R");
		}
		System.err.println(result);*/
	}

	String answer = "RRARAARAAAARAAAAAAARAAAAAAAAAAAARAAAAAAAAAAAAAAAAAAAARAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAARAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAARAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAARAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAARAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAARAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAARAAAAAAAAAAAAA";

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int index = in.readInt();
		if (index == 0)
			throw new UnknownError();
		if (answer.charAt(index - 2) == 'R')
			out.printLine("Roberto");
		else
			out.printLine("Alicia");
	}
}
