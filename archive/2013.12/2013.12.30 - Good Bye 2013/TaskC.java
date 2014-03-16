package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] rating = IOUtils.readIntArray(in, count);
		int[] order = ArrayUtils.order(rating);
		int current = 0;
		for (int i : order) {
			current++;
			current = Math.max(current, rating[i]);
			rating[i] = current;
		}
		out.printLine(rating);
    }
}
