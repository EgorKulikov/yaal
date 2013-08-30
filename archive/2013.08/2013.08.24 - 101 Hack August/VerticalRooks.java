package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class VerticalRooks {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] first = IOUtils.readIntArray(in, count);
		int[] second = IOUtils.readIntArray(in, count);
		int answer = 0;
		for (int i = 0; i < count; i++)
			answer ^= Math.abs(first[i] - second[i]) - 1;
		if (answer == 0)
			out.printLine("player-1");
		else
			out.printLine("player-2");
    }
}
