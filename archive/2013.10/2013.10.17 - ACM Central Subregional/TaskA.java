package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int[] size = IOUtils.readIntArray(in, 3);
		out.printLine(ArrayUtils.maxElement(size));
    }
}
