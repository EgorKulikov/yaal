package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class AGameOfKnights {
	int[][][] grandi = new int[6][301][301];

	{
		for (int i = 1; i <= 5; i++) {
			for (int j = 0; j <= 300; j++) {
				for (int k = 0; k <= 300; k++) {
					long was = 0;
					for (int l = 1; l <= i; l++) {
						for (int m = 0; m <= l; m++) {
							int nj = j - 2 * (l - m) - m;
							int nk = k - (l - m) - 2 * m;
							if (nj >= 0 && nk >= 0) {
								was |= 1L << grandi[i][nj][nk];
							}
						}
					}
					grandi[i][j][k] = Long.bitCount(Long.lowestOneBit(~was) - 1);
				}
			}
		}
	}

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int moves = in.readInt();
		int perKnight = in.readInt();
		int[] answer = new int[6];
		for (int i = 0; i < count; i++) {
			int x = in.readInt();
			int y = in.readInt();
			for (int j = 0; j < 6; j++) {
				answer[j] += grandi[perKnight][x][y] >> j & 1;
			}
		}
		boolean second = true;
		for (int i : answer) {
			if (i % (moves + 1) != 0) {
				second = false;
				break;
			}
		}
		out.printLine(second ? "Second" : "First", "player wins");
    }
}
