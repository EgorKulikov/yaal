package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Parentheses {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] s = in.readString().toCharArray();
		boolean mode = false;
		for (int i = 0; i < s.length; i++) {
			if (s[i] == '(')
				mode = true;
			if (s[i] == ')' && mode) {
				mode = false;
				s[i] = '*';
			}
			if (mode)
				s[i] = '*';
		}
		for (int i = s.length - 1; i >= 0; i--) {
			if (s[i] == ')')
				mode = true;
			if (s[i] == '*')
				mode = false;
			if (mode)
				s[i] = '*';
		}
		out.printLine(s);
    }
}
