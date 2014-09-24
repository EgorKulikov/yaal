package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class SansaAndXOR {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] array = IOUtils.readIntArray(in, count);
		if (count % 2 == 0) {
			out.printLine(0);
		} else {
			int answer = 0;
			for (int i = 0; i < count; i += 2) {
				answer ^= array[i];
			}
			out.printLine(answer);
		}
    }
}
