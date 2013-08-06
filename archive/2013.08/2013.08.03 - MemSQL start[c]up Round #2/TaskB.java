package net.egork;

import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskB {
	int[][] answer;
	int[][] letter;
	int[][] next;
	int[][] last;
	char[] s;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		s = in.readString().toCharArray();
		final int[] qty = new int[26];
		for (int i = 0; i < s.length; i++) {
			s[i] -= 'a';
			qty[s[i]]++;
		}
		for (int i = 0; i < 26; i++) {
			if (qty[i] >= 100) {
				for (int j = 0; j < 100; j++)
					out.print((char)(i + 'a'));
				out.printLine();
				return;
			}
		}
		next = new int[s.length][26];
		last = new int[s.length][26];
		Arrays.fill(next[0], -1);
		next[0][s[0]] = 0;
		for (int i = 1; i < s.length; i++) {
			System.arraycopy(next[i - 1], 0, next[i], 0, 26);
			next[i][s[i]] = i;
		}
		Arrays.fill(last[s.length - 1], s.length);
		last[s.length - 1][s[s.length - 1]] = s.length - 1;
		for (int i = s.length - 2; i >= 0; i--) {
			System.arraycopy(last[i + 1], 0, last[i], 0, 26);
			last[i][s[i]] = i;
		}
		answer = new int[s.length][s.length];
		letter = new int[s.length][s.length];
		ArrayUtils.fill(answer, -1);
		int length = calculate(0, s.length - 1);
		int left = 0;
		int right = s.length - 1;
		char[] answer = new char[length];
		int leftPos = 0;
		int rightPos = length - 1;
		while (leftPos <= rightPos) {
			answer[leftPos++] = answer[rightPos--] = (char) ('a' + letter[left][right]);
			int newLeft = last[left][letter[left][right]] + 1;
			right = next[right][letter[left][right]] - 1;
			left = newLeft;
		}
		out.printLine(answer);
    }

	private int calculate(int from, int to) {
		if (answer[from][to] != -1)
			return answer[from][to];
		if (from == to) {
			letter[from][to] = s[from];
			return answer[from][to] = 1;
		}
		answer[from][to] = 0;
		for (int i = 0; i < 26; i++) {
			int newFrom = last[from][i];
			int newTo = next[to][i];
			if (newFrom == newTo) {
				if (answer[from][to] == 0) {
					answer[from][to] = 1;
					letter[from][to] = i;
				}
			} else if (newFrom < newTo) {
				int result = 2 + calculate(newFrom + 1, newTo - 1);
				if (result > answer[from][to]) {
					answer[from][to] = result;
					letter[from][to] = i;
				}
			}
			if (answer[from][to] >= 100)
				break;
		}
		return Math.min(answer[from][to], 100);
	}
}
