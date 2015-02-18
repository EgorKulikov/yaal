package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] array = IOUtils.readIntArray(in, count);
		out.printLine(count - 1);
		for (int i = 0; i < count - 1; i++) {
			int at = ArrayUtils.minPosition(array, i, count);
			int temp = array[i];
			array[i] = array[at];
			array[at] = temp;
			out.printLine(i, at);
		}
    }
}
