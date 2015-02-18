package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		String[][] names = IOUtils.readStringTable(in, count, 2);
		int[] order = IOUtils.readIntArray(in, count);
		MiscUtils.decreaseByOne(order);
		boolean[] can = ArrayUtils.createArray(2, true);
		boolean[] next = new boolean[2];
		for (int i = 1; i < count; i++) {
			for (int j = 0; j < 2; j++) {
				next[j] = false;
				for (int k = 0; k < 2; k++) {
					if (can[k] && names[order[i - 1]][k].compareTo(names[order[i]][j]) < 0) {
						next[j] = true;
						break;
					}
				}
			}
			boolean[] temp = can;
			can = next;
			next = temp;
		}
		out.printLine(ArrayUtils.count(can, true) > 0 ? "YES" : "NO");
    }
}
