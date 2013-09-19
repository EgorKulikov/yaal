package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class ChefAndIntegers {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] array = IOUtils.readIntArray(in, count);
		int cost = in.readInt();
		Arrays.sort(array);
		long answer = 0;
		for (int i = 0; i < count && i < cost; i++) {
			if (array[i] < 0)
				answer -= array[i];
		}
		out.printLine(answer);
    }
}
