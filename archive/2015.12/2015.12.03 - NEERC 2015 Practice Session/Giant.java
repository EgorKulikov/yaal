package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class Giant {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rh = in.readInt();
		int rv = in.readInt();
		int sh = in.readInt();
		int sv = in.readInt();
		int n = in.readInt();
		int answer = Integer.MAX_VALUE;
		for (int i = 0; i < n; i++) {
			int crh = in.readInt();
			int crv = in.readInt();
			int csh = in.readInt();
			int csv = in.readInt();
			int p = in.readInt();
			for (int j = 0; j < 2; j++) {
				int horizontal = Math.max((rh - 1) / crh + 1, (sh - 1) / csh + 1);
				int vertical = Math.max((rv - 1) / crv + 1, (sv - 1) / csv + 1);
				answer = Math.min(answer, p * horizontal * vertical);
				int temp = crh;
				crh = crv;
				crv = temp;
				temp = csh;
				csh = csv;
				csv = temp;
			}
		}
		out.printLine(answer);
	}
}
