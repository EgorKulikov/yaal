package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;
import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;

public class TaskL {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		String s = in.readString();
		String t = in.readString();
		int n = s.length();
		int m = t.length();
		boolean[][] d = new boolean[n + 1][m + 1];
		d[0][0] = true;
		for (int i = 1; i <= n; i++) {
			boolean gg = false;
			for (int j = 1; j <= m; j++) {
				if (s.charAt(i - 1) != t.charAt(j - 1)) {
					d[i][j] = d[i][j - 1];
				} else {
					d[i][j] = d[i - 1][j - 1] || gg;
					if (d[i - 1][j - 1] && j < m - 1 && t.charAt(j) != t.charAt(j - 1)) {
						gg = true;
					}
				}
			}
		}
		out.printLine(d[n][m] ? "Yes" : "No");
	}
}
