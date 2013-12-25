package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Stack;

public class TaskD {
	static final long[][] DEFAULT = new long[3][3];

	static {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++)
				DEFAULT[i][j] = i != j || i == 0 ? 1 : 0;
		}
	}

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] s = in.readString().toCharArray();
		int[] close = new int[s.length];
		Stack<Integer> stack = new Stack<Integer>();
		for (int i = 0; i < s.length; i++) {
			if (s[i] == '(')
				stack.add(i);
			else
				close[stack.pop()] = i;
		}
		out.printLine(go(close, 0, s.length - 1)[0][0]);
	}

	private long[][] go(int[] close, int from, int to) {
		if (from > to)
			return DEFAULT;
		long[][] result = new long[3][3];
		long[][] firstResult = go(close, from + 1, close[from] - 1);
		long[][] secondResult = go(close, close[from] + 1, to);
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				for (int k = 1; k < 3; k++) {
					if (k != i || k == 0)
						result[i][j] += firstResult[k][0] * secondResult[0][j];
					result[i][j] += firstResult[0][k] * secondResult[k][j];
				}
				result[i][j] %= (long)1e9 + 7;
			}
		}
		return result;
	}
}
