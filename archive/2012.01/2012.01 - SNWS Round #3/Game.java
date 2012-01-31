package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Game {
	private static final boolean[][][][] win = new boolean[31][31][31][31];

	static {
		for (int a = 0; a <= 30; a++) {
			for (int b = 0; b <= 30; b++) {
				for (int c = 0; c <= 30; c++) {
					for (int d = 0; d <= 30; d++) {
						if (a >= 2 && b >= 1 && d >= 2 && !win[a - 2][b - 1][c][d - 2])
							win[a][b][c][d] = true;
						if (a >= 1 && b >= 1 && c >= 1 && d >= 1 && !win[a - 1][b - 1][c - 1][d - 1])
							win[a][b][c][d] = true;
						if (c >= 2 && d >= 1 && !win[a][b][c - 2][d - 1])
							win[a][b][c][d] = true;
						if (b >= 3 && !win[a][b - 3][c][d])
							win[a][b][c][d] = true;
						if (a >= 1 && d >= 1 && !win[a - 1][b][c][d - 1])
							win[a][b][c][d] = true;
					}
				}
			}
		}
	}

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int a = in.readInt();
		int b = in.readInt();
		int c = in.readInt();
		int d = in.readInt();
		if (win[a][b][c][d])
			out.printLine("First");
		else
			out.printLine("Second");
	}
}
