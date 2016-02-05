package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int m = in.readInt();
		int k = in.readInt();
		int[] a = new int[k];
		int[] p = new int[k];
		IOUtils.readIntArrays(in, a, p);
		ArrayUtils.orderBy(a, p);
		long step = 1;
		for (int i : p) {
			if (step >= m) {
				out.printLine(1);
				return;
			}
			long t = (step + i - 1) / step;
			step *= t;
		}
		out.printLine((m + step - 1) / step);
	}
}
