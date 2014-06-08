package net.egork;

import net.egork.string.StringUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class PalindromeIndex {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		String s = in.readString();
		for (int i = 0, j = s.length() - 1; i < j; i++,j--) {
			if (s.charAt(i) != s.charAt(j)) {
				String candidate = s.substring(0, i) + s.substring(i + 1);
				if (candidate.equals(StringUtils.reverse(candidate))) {
					out.printLine(i);
					return;
				}
				out.printLine(j);
				return;
			}
		}
		out.printLine(-1);
    }
}
