package net.egork;

import net.egork.io.IOUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.Arrays;

public class MalcolmAndCards {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		long[] values = IOUtils.readLongArray(in, count);
		Arrays.sort(values);
		long sum = 0;
		long per = 1;
		long max = 0;
		long qty = 1;
		for (long value : values) {
			if (value > sum) {
				per = qty * per;
				qty = 2;
				max = value;
			} else {
				qty += value / max;
			}
			sum += value;
		}
		out.printLine(qty * per);
	}
}
