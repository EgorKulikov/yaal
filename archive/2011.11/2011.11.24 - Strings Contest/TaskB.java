package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
	private int[][] answer;
	private char[] sequence;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		sequence = in.readString().toCharArray();
		answer = new int[sequence.length][sequence.length];
		ArrayUtils.fill(answer, -1);
		out.printLine(go(0, sequence.length - 1));
	}

	private int go(int from, int to) {
		if (from > to)
			return 0;
		if (answer[from][to] != -1)
			return answer[from][to];
		char closing;
		switch (sequence[from]) {
			case '{':
				closing = '}';
				break;
			case '[':
				closing = ']';
				break;
			case '(':
				closing = ')';
				break;
			default:
				return answer[from][to] = 1 + go(from + 1, to);
		}
		answer[from][to] = 1 + go(from + 1, to);
		for (int i = from + 1; i <= to; i++) {
			if (sequence[i] == closing)
				answer[from][to] = Math.min(answer[from][to], go(from + 1, i - 1) + go(i + 1, to));
		}
		return answer[from][to];
	}
}
