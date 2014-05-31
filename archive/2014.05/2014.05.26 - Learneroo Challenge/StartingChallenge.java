package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class StartingChallenge {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] array = IOUtils.readIntArray(in, count);
		long answer = 0;
		for (int i = 0; i < count; i++) {
			if (i > 0 && array[i - 1] == 2 || i + 1 < count && array[i + 1] == 2)
				answer += array[i] * 2;
			else
				answer += array[i];
		}
		out.printLine(answer);
    }
}
