package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class SumOfValues {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		long boundary = in.readLong();
		long step = in.readLong();
		long[] values = IOUtils.readLongArray(in, count);
		if (count <= 2) {
			out.printLine(1);
			return;
		}
		Arrays.sort(values);
		long[] steps = new long[count];
		for (int i = 0; i < count; i++)
			steps[i] = (boundary - values[i]) / step + 1;
		long other = 0;
		for (int i = 2; i < count; i++)
			other += steps[i];
		long max = steps[0];
		if (other < steps[1])
			max -= steps[1] - other;
		else
			max -= (steps[1] + other) & 1;
		long min;
		if (steps[0] > other + steps[1])
			min = steps[0] - other - steps[1];
		else
			min = (steps[0] + other + steps[1]) & 1;
		long answer = (max - min) / 2 + 1;
		out.printLine(answer % ((long)(1e9 + 7)));
    }
}
