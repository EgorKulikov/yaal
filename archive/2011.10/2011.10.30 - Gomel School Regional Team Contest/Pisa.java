package net.egork;

import net.egork.utils.io.InputReader;
import java.io.PrintWriter;

public class Pisa {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int size = in.readInt();
		long[][] answer = new long[size + 1][2];
		answer[1][0] = 1;
		answer[1][1] = 1;
		for (int i = 2; i <= size; i++) {
			answer[i][0] = answer[i - 1][1] + 2;
			answer[i][1] = answer[i - 1][0] + 1 + answer[i - 1][1];
		}
		out.println(answer[size][0]);
	}
}
