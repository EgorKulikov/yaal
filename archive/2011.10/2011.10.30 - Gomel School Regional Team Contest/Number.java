package net.egork;

import net.egork.utils.io.InputReader;
import java.io.PrintWriter;

public class Number {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		String s = in.readString();
		if (s.charAt(0) == '0') {
			out.println(0);
			return;
		}
		int result = 0;
		for (int i = 1; i < (s.length() + 1) / 2; i++) {
			if (s.charAt(i) != '0')
				result++;
		}
		if (s.length() % 2 == 0) {
			boolean bad = false;
			for (int i = 0; i < s.length() / 2; i++) {
				if (s.charAt(i) > s.charAt(s.length() / 2 + i)) {
					bad = true;
					break;
				}
				if (s.charAt(i) < s.charAt(s.length() / 2 + i))
					break;
			}
			if (!bad)
				result++;
		}
		out.println(result);
	}
}
