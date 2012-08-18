package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TopFour {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] array = IOUtils.readIntArray(in, count);
		int[] result = new int[4];
		Arrays.fill(result, Integer.MIN_VALUE);
		for (int i : array) {
			for (int j = 3; j >= 0; j--) {
				if (i > result[j]) {
					if (j != 3)
						result[j + 1] = result[j];
					result[j] = i;
				} else
					break;
			}
		}
		for (int i : result)
			out.printLine(i);
	}
}
