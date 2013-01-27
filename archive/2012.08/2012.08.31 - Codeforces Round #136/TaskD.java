package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
	private static final long MOD = (long) (1e9 + 7);

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		long width = in.readInt();
		long evenWidth = (width + 2) / 2;
		long oddWidth = (width + 1) / 2;
		long height = in.readInt();
		long evenHeight = (height + 2) / 2;
		long oddHeight = (height + 1) / 2;
		long total = 0;
		int[][] gcd = new int[(int) (width + 1)][(int) (height + 1)];
		for (int i = 0; i <= width; i++)
			gcd[i][0] = i + 1;
		for (int i = 0; i <= height; i++)
			gcd[0][i] = i + 1;
		for (int i = 1; i <= width; i++) {
			for (int j = 1; j <= height; j++) {
				if (i >= j)
					gcd[i][j] = gcd[i - j][j];
				else
					gcd[i][j] = gcd[i][j - i];
			}
		}
		for (int i = 0; i <= width; i++) {
			long remainingWidth = width - i + 1;
			for (int j = 0; j <= height; j++) {
				long remainingHeight = height - j + 1;
				long current;
				if ((i & 1) == 0) {
					if ((j & 1) == 0)
						current = (width + 1) * (height + 1) * remainingWidth * remainingHeight;
					else
						current = evenWidth * (height + 1) * ((remainingWidth + 1) >> 1) * remainingHeight + oddWidth * (height + 1) * (remainingWidth >> 1) * remainingHeight;
				} else {
					if ((j & 1) == 0)
						current = evenHeight * (width + 1) * ((remainingHeight + 1) >> 1) * remainingWidth + oddHeight * (width + 1) * (remainingHeight >> 1) * remainingWidth;
					else {
						current = (((height + 1) * (width + 1) + 1) >> 1) * ((remainingHeight * remainingWidth + 1) >> 1) +
							(((height + 1) * (width + 1)) >> 1) * ((remainingHeight * remainingWidth) >> 1);
					}
				}
				if (i != 0)
					current <<= 1;
				if (j != 0)
					current <<= 1;
				long currentMinus = 0;
				if (gcd[i][j] > 2)
					currentMinus += 6 * (gcd[i][j] - 2) * (width - i + 1) * (height - j + 1);
				if (gcd[i][j] > 1)
					currentMinus += 6 * (width - i + 1) * (height - j + 1);
				if (i != 0 && j != 0)
					currentMinus <<= 1;
				if (i == 0 && j == 0)
					currentMinus += (width + 1) * (height + 1);
				current -= currentMinus;
				total += current;
			}
			total %= MOD;
		}
		if (total < 0)
			total += MOD;
		out.printLine(total);
	}
}
