package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Nicknames {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		String[] names = IOUtils.readStringArray(in, 3);
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (i == j)
					continue;
				if (names[i].startsWith(names[j]) || names[i].endsWith(names[j])) {
					out.printLine("No");
					return;
				}
			}
		}
		out.printLine("Yes");
	}
}
