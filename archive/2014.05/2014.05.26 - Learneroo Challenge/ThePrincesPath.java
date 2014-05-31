package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class ThePrincesPath {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		long[][] power = IOUtils.readLongTable(in, rowCount, columnCount);
		long[] current = new long[rowCount];
		for (int i = 0; i < rowCount; i++)
			current[i] = power[i][0];
		for (int i = 1; i < columnCount; i++) {
			for (int j = rowCount - 1; j >= 0; j--) {
				current[j] += power[j][i];
				if (j != 0)
					current[j] = Math.max(current[j], current[j - 1] + power[j][i]);
				if (current[j] <= 0)
					current[j] = Long.MIN_VALUE / 2;
			}
		}
		if (ArrayUtils.maxElement(current) <= 0)
			out.printLine("NO");
		else
			out.printLine("YES");
    }
}
