package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Equal {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		if (count == 0) {
			out.printLine(0);
			return;
		}
		int[] distributed = IOUtils.readIntArray(in, count);
		int min = ArrayUtils.minElement(distributed);
		for (int i = 0; i < count; i++)
			distributed[i] -= min;
		int answer = Integer.MAX_VALUE;
		for (int i = 0; i < 10; i++) {
			int current = 0;
			for (int j : distributed) {
				current += j / 5;
				j %= 5;
				current += j / 2;
				j %= 2;
				current += j;
			}
			answer = Math.min(answer, current);
			for (int j = 0; j < count; j++)
				distributed[j]++;
		}
		out.printLine(answer);
    }
}
