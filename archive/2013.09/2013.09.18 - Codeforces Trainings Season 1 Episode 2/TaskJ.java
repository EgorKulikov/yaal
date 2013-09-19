package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskJ {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		if (in.peek() == -1)
			throw new UnknownError();
		char[] first = in.readLine(false).toCharArray();
		char[] second = in.readLine(false).toCharArray();
		int[] qtyFirst = count(first);
		int[] qtySecond = count(second);
		StringBuilder answer = new StringBuilder();
		for (int i = 0; i < 26; i++) {
			for (int j = 0; j < Math.min(qtyFirst[i], qtySecond[i]); j++)
				answer.append((char)('a' + i));
		}
		out.printLine(answer);
    }

	private int[] count(char[] s) {
		int[] result = new int[26];
		for (char c : s)
			result[c - 'a']++;
		return result;
	}
}
