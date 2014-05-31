package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class SherlockAndCost {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] array = IOUtils.readIntArray(in, count);
		int one = 0;
		int max = 0;
		for (int i = 1; i < count; i++) {
			int newOne = Math.max(one, max + Math.abs(array[i - 1] - 1));
			int newMax = Math.max(one + Math.abs(array[i] - 1), max + Math.abs(array[i] - array[i - 1]));
			one = newOne;
			max = newMax;
		}
		out.printLine(Math.max(max, one));
    }
}
