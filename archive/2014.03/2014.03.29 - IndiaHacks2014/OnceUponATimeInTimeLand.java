package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class OnceUponATimeInTimeLand {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int gap = in.readInt();
		int[] array = IOUtils.readIntArray(in, count);
		long[] answer = new long[count];
		answer[0] = Math.max(array[0], 0);
		for (int i = 1; i <= gap; i++)
			answer[i] = Math.max(array[i], answer[i - 1]);
		for (int i = gap + 1; i < count; i++)
			answer[i] = Math.max(array[i] + answer[i - gap - 1], answer[i - 1]);
		out.printLine(answer[count - 1]);
    }
}
