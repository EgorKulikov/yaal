package net.egork;

import net.egork.collections.CollectionUtils;
import net.egork.collections.sequence.Array;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class BirthdayGift {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int money = in.readInt();
		int boothCount = in.readInt();
		int roadCount = in.readInt();
		int[][] toll = IOUtils.readIntTable(in, roadCount, boothCount);
		int[][] shift = IOUtils.readIntTable(in, roadCount, roadCount);
		for (int i = 0; i < roadCount; i++) {
			for (int j = 0; j < roadCount; j++) {
				for (int k = 0; k < roadCount; k++)
					shift[j][k] = Math.min(shift[j][k], shift[j][i] + shift[i][k]);
			}
		}
		int[] result = new int[roadCount];
		for (int i = 0; i < boothCount; i++) {
			for (int j = 0; j < roadCount; j++)
				result[j] += toll[j][i];
			for (int j = 0; j < roadCount; j++) {
				for (int k = 0; k < roadCount; k++)
					result[j] = Math.min(result[j], result[k] + shift[k][j]);
			}
		}
		money -= CollectionUtils.minElement(Array.wrap(result));
		if (money >= 0)
			out.printLine(money);
		else
			out.printLine(-1);
	}
}
