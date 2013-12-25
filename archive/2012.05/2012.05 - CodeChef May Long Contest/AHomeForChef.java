package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class AHomeForChef {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] x = new int[count];
		int[] y = new int[count];
		IOUtils.readIntArrays(in, x, y);
		long answer = count(x) * count(y);
		out.printLine(answer);
	}

	private long count(int[] array) {
		if (array.length % 2 == 1)
			return 1;
		Arrays.sort(array);
		return array[array.length / 2] - array[array.length / 2 - 1] + 1;
	}
}
