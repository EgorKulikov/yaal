package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class ChefAndSubarray {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] array = IOUtils.readIntArray(in, count);
		int answer = 0;
		int start = 0;
		for (int i = 0; i < count; i++) {
			if (array[i] == 0)
				start = i + 1;
			answer = Math.max(answer, i + 1 - start);
		}
		out.printLine(answer);
	}
}
