package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;
import java.util.HashSet;

public class StringDivision {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		String s = in.readString();
		if (s.length() >= 10) {
			out.printLine("YES");
			return;
		}
		for (int i = 1; i < s.length(); i++) {
			String a = s.substring(0, i);
			for (int j = i + 1; j < s.length(); j++) {
				String b = s.substring(i, j);
				for (int k = j + 1; k < s.length(); k++) {
					String c = s.substring(j, k);
					String d = s.substring(k);
					if (new HashSet<>(Arrays.asList(a, b, c, d)).size() == 4) {
						out.printLine("YES");
						return;
					}
				}
			}
		}
		out.printLine("NO");
	}
}
