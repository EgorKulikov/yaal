package net.egork;

import net.egork.io.IOUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class MagicPairs {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		IOUtils.readIntArray(in, count);
		long answer = (long)count * (count - 1) / 2;
		out.printLine(answer);
    }
}
