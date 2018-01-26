package net.egork;

import net.egork.io.IOUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int h = in.readInt();
		int w = in.readInt();
		char[][] table = IOUtils.readTable(in, h, w);
		int[][] qtyV = new int[h + 1][w + 1];
		int[][] qtyH = new int[h + 1][w + 1];
		for (int i = h - 1; i >= 0; i--) {
			for (int j = w - 1; j >= 0; j--) {
				qtyV[i][j] = qtyV[i + 1][j] + qtyV[i][j + 1] - qtyV[i + 1][j + 1];
				qtyH[i][j] = qtyH[i + 1][j] + qtyH[i][j + 1] - qtyH[i + 1][j + 1];
				if (i != h - 1 && table[i][j] == '.' && table[i + 1][j] == '.') {
					qtyV[i][j]++;
				}
				if (j != w - 1 && table[i][j] == '.' && table[i][j + 1] == '.') {
					qtyH[i][j]++;
				}
			}
		}
		int q = in.readInt();
		for (int i = 0; i < q; i++) {
			int r1 = in.readInt() - 1;
			int c1 = in.readInt() - 1;
			int r2 = in.readInt() - 1;
			int c2 = in.readInt() - 1;
			int answer = qtyV[r1][c1] - qtyV[r1][c2 + 1] - qtyV[r2][c1] + qtyV[r2][c2 + 1] +
				qtyH[r1][c1] - qtyH[r1][c2] - qtyH[r2 + 1][c1] + qtyH[r2 + 1][c2];
			out.printLine(answer);
		}
	}
}
