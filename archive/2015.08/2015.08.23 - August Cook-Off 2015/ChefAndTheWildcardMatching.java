package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class ChefAndTheWildcardMatching {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] s1 = in.readString().toCharArray();
		char[] s2 = in.readString().toCharArray();
		boolean match = true;
		for (int i = 0; i < s1.length; i++) {
			if (s1[i] != s2[i] && s1[i] != '?' && s2[i] != '?') {
				match = false;
				break;
			}
		}
		out.printLine(match ? "Yes" : "No");
	}
}
