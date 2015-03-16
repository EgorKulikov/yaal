package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int length = in.readInt();
		char[] sequence = IOUtils.readCharArray(in, length);
		int[] qty = new int[256];
		for (char c : sequence) {
			qty[c]++;
		}
		out.printLine(IntegerUtils.power(ArrayUtils.count(qty, ArrayUtils.maxElement(qty)), length, (long) (1e9 + 7)));
    }
}
