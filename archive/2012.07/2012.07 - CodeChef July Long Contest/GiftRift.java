package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class GiftRift {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
        int rowCount = in.readInt();
        int columnCount = in.readInt();
        int[][] prices = IOUtils.readIntTable(in, rowCount, columnCount);
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                int min = Integer.MAX_VALUE;
                for (int k = 0; k < columnCount; k++)
                    min = Math.min(min, prices[i][k]);
                int max = Integer.MIN_VALUE;
                for (int k = 0; k < rowCount; k++)
                    max = Math.max(max, prices[k][j]);
                if (min == max) {
                    out.printLine(min);
                    return;
                }
            }
        }
        out.printLine("GUESS");
	}
}
