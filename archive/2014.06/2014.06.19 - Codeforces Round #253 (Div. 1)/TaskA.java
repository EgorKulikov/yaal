package net.egork;

import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
	static final String COLORS = "RGBYW";

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		boolean[][] present = new boolean[5][5];
		for (int i = 0; i < count; i++) {
			int color = COLORS.indexOf(in.readCharacter());
			int number = in.readCharacter() - '1';
			present[color][number] = true;
		}
		int[][] qty = new int[6][6];
		int answer = 10;
		for (int i = 0; i < 32; i++) {
			for (int j = 0; j < 32; j++) {
				ArrayUtils.fill(qty, 0);
				for (int k = 0; k < 5; k++) {
					for (int l = 0; l < 5; l++) {
						if (present[k][l]) {
							int first;
							if ((i >> k & 1) == 0)
								first = 5;
							else
								first = k;
							int second;
							if ((j >> l & 1) == 0)
								second = 5;
							else
								second = l;
							qty[first][second]++;
						}
					}
				}
				boolean good = true;
				for (int k = 0; k < 6; k++) {
					for (int l = 0; l < 6; l++) {
						if (qty[k][l] > 1) {
							good = false;
						}
					}
				}
				if (good) {
					answer = Math.min(answer, Integer.bitCount(i) + Integer.bitCount(j));
				}
			}
		}
		out.printLine(answer);
	}
}
