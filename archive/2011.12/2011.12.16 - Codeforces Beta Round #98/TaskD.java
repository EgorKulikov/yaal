package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskD {
	private char[] s;
	private int[][] changes;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		s = in.readString().toCharArray();
		int allowed = in.readInt();
		changes = new int[s.length][s.length];
		ArrayUtils.fill(changes, -1);
		for (int i = 0; i < s.length; i++) {
			for (int j = i; j < s.length; j++)
				countChanges(i, j);
		}
		int[][] answer = new int[s.length + 1][allowed + 1];
		int[][] last = new int[s.length + 1][allowed + 1];
		for (int i = 1; i <= s.length; i++) {
			answer[i][0] = Integer.MAX_VALUE / 2;
			for (int j = 1; j <= allowed; j++) {
				answer[i][j] = Integer.MAX_VALUE / 2;
				for (int k = 0; k < i; k++) {
					int curValue = answer[k][j - 1] + changes[k][i - 1];
					if (answer[i][j] > curValue) {
						answer[i][j] = curValue;
						last[i][j] = k;
					}
				}
			}
		}
		int count = 0;
		for (int i = 1; i <= allowed; i++) {
			if (answer[s.length][i] < answer[s.length][count])
				count = i;
		}
		int position = s.length;
		String asString = new String(s);
		List<char[]> subStrings = new ArrayList<char[]>();
		for (int i = count; i > 0; i--) {
			int start = last[position][i];
			subStrings.add(asString.substring(start, position).toCharArray());
			position = start;
		}
		Collections.reverse(subStrings);
		out.printLine(answer[s.length][count]);
		boolean first = true;
		for (char[] word : subStrings) {
			if (first)
				first = false;
			else
				out.print('+');
			for (int i = 0; i < word.length / 2; i++)
				word[i] = word[word.length - i - 1];
			out.print(new String(word));
		}
		out.printLine();
	}

	private int countChanges(int from, int to) {
		if (from > to)
			return 0;
		if (changes[from][to] != -1)
			return changes[from][to];
		return changes[from][to] = countChanges(from + 1, to - 1) + (s[from] == s[to] ? 0 : 1);
	}
}
