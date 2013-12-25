package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class ManyChefs {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] s = in.readString().toCharArray();
		int count = s.length;
		if (count < 4) {
			for (int i = 0; i < count; i++) {
				if (s[i] == '?')
					s[i] = 'A';
			}
			out.printLine(s);
			return;
		}
		int[] last = new int[count + 1];
		int[] answer = new int[count + 1];
		last[0] = last[1] = last[2] = last[3] = -1;
		for (int i = 3; i < count; i++) {
			last[i + 1] = last[i];
			answer[i + 1] = answer[i];
			if (good(s[i - 3], 'C') && good(s[i - 2], 'H') && good(s[i - 1], 'E') && good(s[i], 'F')) {
				if (answer[i - 3] + 1 >= answer[i + 1]) {
					answer[i + 1] = answer[i - 3] + 1;
					last[i + 1] = i - 3;
				}
			}
		}
		int current = last[count];
		while (current != -1) {
			s[current] = 'C';
			s[current + 1] = 'H';
			s[current + 2] = 'E';
			s[current + 3] = 'F';
			current = last[current];
		}
		for (int i = 0; i < count; i++) {
			if (s[i] == '?')
				s[i] = 'A';
		}
		out.printLine(s);
    }

	private boolean good(char s, char c) {
		return s == c || s == '?';
	}
}
