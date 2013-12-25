package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int goodCount = in.readInt();
		int badCount = in.readInt();
		int[] good = IOUtils.readIntArray(in, goodCount);
		int[] bad = IOUtils.readIntArray(in, badCount);
		int answer = Math.max(ArrayUtils.maxElement(good), 2 * ArrayUtils.minElement(good));
		if (answer < ArrayUtils.minElement(bad))
			out.printLine(answer);
		else
			out.printLine(-1);
    }
}
