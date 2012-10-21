package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] sequence = in.readString().toCharArray();
		int answer = 0;
		int from = 0;
		int to = 0;
		int[] count = new int[sequence.length + 1];
		for (int i = 1; i <= sequence.length; i++)
			count[i] = count[i - 1] + (sequence[i - 1] == '[' ? 1 : 0);
		int[] position = new int[sequence.length + 1];
		char[] symbol = new char[sequence.length + 1];
		int depth = 0;
		Arrays.fill(position, -1);
		int validSince = 0;
		for (int i = 0; i < sequence.length; i++) {
			if (sequence[i] == '(' || sequence[i] == '[') {
				if (position[depth] < validSince)
					position[depth] = i;
				symbol[depth] = sequence[i];
				depth++;
			} else {
				if (depth == 0 || symbol[depth - 1] != open(sequence[i])) {
					validSince = i + 1;
					depth = 0;
					continue;
				}
				position[depth] = -1;
				int start = position[--depth];
				int current = count[i] - count[start];
				if (current > answer) {
					from = start;
					to = i + 1;
					answer = current;
				}
			}
		}
		out.printLine(answer);
		out.printLine(new String(sequence).substring(from, to));
	}

	private char open(char c) {
		if (c == ')')
			return '(';
		return '[';
	}
}
