package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Exponent {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		if (count == 0)
			throw new UnknownError();
		int[] base = new int[count];
		int[] exponent = new int[count];
		IOUtils.readIntArrays(in, base, exponent);
		int index = -1;
		double value = Double.NEGATIVE_INFINITY;
		for (int i = 0; i < count; i++) {
			double current = Math.log(base[i]) * exponent[i];
			if (current > value) {
				value = current;
				index = i;
			}
		}
		out.printLine(base[index], exponent[index]);
    }
}
