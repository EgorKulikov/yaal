package net.egork;

import net.egork.collections.comparators.IntComparator;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		int[][] table = IOUtils.readIntTable(in, rowCount, columnCount);
		int[] values = new int[rowCount * columnCount];
		for (int i = 0; i < rowCount; i++)
			System.arraycopy(table[i], 0, values, i * columnCount, columnCount);
		ArrayUtils.sort(values, IntComparator.DEFAULT);
		int left = 0;
		int right = rowCount * columnCount - 1;
		int[][] masks = new int[rowCount][(columnCount + 31) >> 5];
		while (left < right) {
			int mid = (left + right + 1) >> 1;
			int middle = values[mid];
			ArrayUtils.fill(masks, 0);
			for (int i = 0; i < rowCount; i++) {
				for (int j = 0; j < columnCount; j++) {
					if (table[i][j] >= middle)
						masks[i][j >> 5] += 1 << (j & 31);
				}
			}
			boolean can = false;
			for (int i = 0; i < rowCount && !can; i++) {
				for (int j = i + 1; j < rowCount && !can; j++) {
					int qty = 0;
					for (int k = 0; k < masks[i].length && qty < 2; k++) {
						int current = masks[i][k] & masks[j][k];
						if (current != 0) {
							qty++;
							if ((current & (current - 1)) != 0)
								qty++;
						}
					}
					if (qty >= 2)
						can = true;
				}
			}
			if (can)
				left = mid;
			else
				right = mid - 1;
		}
		out.printLine(values[left]);
    }
}
