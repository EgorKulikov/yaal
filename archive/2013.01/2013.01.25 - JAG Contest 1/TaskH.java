package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.RecursiveIndependentSetSystem;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskH {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int edgeCount = in.readInt();
		int[] from = new int[edgeCount];
		int[] to = new int[edgeCount];
		int[] value = new int[edgeCount];
		IOUtils.readIntArrays(in, from, to, value);
		MiscUtils.decreaseByOne(from, to);
		IndependentSetSystem setSystem = new RecursiveIndependentSetSystem(count);
		int remaining = count >> 1;
		int[] order = ArrayUtils.order(value);
		for (int i : order) {
			if (setSystem.join(from[i], to[i])) {
				remaining--;
				if (remaining == 0) {
					out.printLine(value[i]);
					return;
				}
			}
		}
	}
}
