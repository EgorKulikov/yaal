package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		in.readInt();
		int[] array = IOUtils.readIntArray(in, 10);
		Arrays.sort(array);
		out.printLine(testNumber, array[7]);
	}
}
