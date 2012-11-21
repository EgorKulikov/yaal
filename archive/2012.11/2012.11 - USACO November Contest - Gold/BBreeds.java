package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class BBreeds {
	private static final long MOD = 2012;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] pattern = in.readString().toCharArray();
		int[] answer = new int[pattern.length + 1];
		answer[0] = 1;
		int[] next = new int[answer.length];
		int totalBalance = 0;
		for (char c : pattern) {
			Arrays.fill(next, 0);
			for (int i = 0; i <= totalBalance; i++) {
				int j = totalBalance - i;
				if (c == '(') {
					next[i] += answer[i];
					if (next[i] >= MOD)
						next[i] -= MOD;
					next[i + 1] += answer[i];
					if (next[i + 1] >= MOD)
						next[i + 1] -= MOD;
				} else {
					if (i != 0) {
						next[i - 1] += answer[i];
						if (next[i - 1] >= MOD)
							next[i - 1] -= MOD;
					}
					if (j != 0) {
						next[i] += answer[i];
						if (next[i] >= MOD)
							next[i] -= MOD;
					}
				}
			}
			if (c == '(')
				totalBalance++;
			else
				totalBalance--;
			int[] temp = answer;
			answer = next;
			next = temp;
		}
		if (totalBalance != 0)
			out.printLine(0);
		else
			out.printLine(answer[0]);
	}
}
