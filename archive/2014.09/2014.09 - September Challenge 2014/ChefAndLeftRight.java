package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class ChefAndLeftRight {
	private static final long MOD = (long) (1e9 + 7);

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] path = in.readString().toCharArray();
		long oddIndex = 1;
		long evenIndex = 2;
		long shift = 0;
		int size = 1;
		boolean isOdd = true;
		for (char c : path) {
			if (c == 'r') {
				shift++;
			}
			shift *= 2;
			shift %= MOD;
			if (isOdd) {
				oddIndex += size * 2;
				oddIndex %= MOD;
			} else {
				evenIndex += size * 2;
				evenIndex %= MOD;
			}
			size *= 2;
			size %= MOD;
			isOdd = !isOdd;
		}
		long answer;
		if (isOdd) {
			answer = oddIndex + shift;
		} else {
			answer = evenIndex + shift;
		}
		answer %= MOD;
		out.printLine(answer);
    }
}
