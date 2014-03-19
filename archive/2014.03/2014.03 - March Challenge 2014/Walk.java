package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Walk {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] attractiveness = IOUtils.readIntArray(in, count);
		int answer = 0;
		for (int i = 0; i < count; i++)
			answer = Math.max(answer, attractiveness[i] + i);
		out.printLine(answer);
	}
}
