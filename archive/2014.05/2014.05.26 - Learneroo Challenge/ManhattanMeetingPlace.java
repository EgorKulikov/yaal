package net.egork;

import net.egork.io.IOUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.Arrays;

public class ManhattanMeetingPlace {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt() / 2;
		int[] x = new int[count];
		int[] y = new int[count];
		IOUtils.readIntArrays(in, x, y);
		Arrays.sort(x);
		Arrays.sort(y);
		out.printLine(x[count >> 1], y[count >> 1]);
    }
}
