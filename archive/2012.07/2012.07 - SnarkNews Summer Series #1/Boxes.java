package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Boxes {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int[] quantity = IOUtils.readIntArray(in, count);
        long before = ArrayUtils.sumArray(quantity);
        for (int i = 1; i < count; i++)
            quantity[i] = Math.min(quantity[i], quantity[i - 1] + 1);
        for (int i = count - 2; i >= 0; i--)
            quantity[i] = Math.min(quantity[i], quantity[i + 1] + 1);
        long after = ArrayUtils.sumArray(quantity);
        long answer = before - after;
        out.printLine(answer);
	}
}
