package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskK {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		long[][] coord = new long[n][3];
		for (int i = 0; i < n; ++i)
			for (int j = 0; j < 3; ++j)
				coord[i][j] = in.readInt();
		if (n <= 3) {
			out.printLine("TAK");
		} else {
			for (int i = 1; i < n; ++i)
				for (int j = 0; j < 3; ++j)
					coord[i][j] -= coord[0][j];
			long[] prod = new long[3];
			for (int j2 = 2; j2 < n; ++j2) {
				boolean ok = false;
				for (int i = 0; i < 3; ++i) {
					int i1 = (i + 1) % 3;
					int i2 = (i + 2) % 3;
					prod[i] = coord[1][i1] * coord[j2][i2] - coord[j2][i1] * coord[1][i2];
					if (prod[i] != 0)
						ok = true;
				}
				if (ok) break;
			}
			boolean ok = true;
			for (int i = 1; i < n; ++i) {
				if (coord[i][0] * prod[0] + coord[i][1] * prod[1] + coord[i][2] * prod[2] != 0)
					ok = false;
			}
			out.printLine(ok ? "TAK" : "NIE");
		}
	}
}
