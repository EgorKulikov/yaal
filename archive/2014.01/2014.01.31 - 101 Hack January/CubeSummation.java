package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class CubeSummation {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		in.readInt();
		int queryCount = in.readInt();
		int[] x = new int[queryCount];
		int[] y = new int[queryCount];
		int[] z = new int[queryCount];
		int[] delta = new int[queryCount];
		for (int i = 0; i < queryCount; i++) {
			String type = in.readString();
			if ("UPDATE".equals(type)) {
				x[i] = in.readInt();
				y[i] = in.readInt();
				z[i] = in.readInt();
				delta[i] = in.readInt();
				for (int j = 0; j < i; j++) {
					if (x[j] == x[i] && y[j] == y[i] && z[j] == z[i])
						delta[j] = 0;
				}
			} else {
				long answer = 0;
				int x0 = in.readInt();
				int y0 = in.readInt();
				int z0 = in.readInt();
				int x1 = in.readInt();
				int y1 = in.readInt();
				int z1 = in.readInt();
				for (int j = 0; j < i; j++) {
					if (x[j] >= x0 && x[j] <= x1 && y[j] >= y0 && y[j] <= y1 && z[j] >= z0 && z[j] <= z1)
						answer += delta[j];
				}
				out.printLine(answer);
			}
		}
    }
}
