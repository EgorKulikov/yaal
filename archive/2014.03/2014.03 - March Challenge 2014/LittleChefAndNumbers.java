package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class LittleChefAndNumbers {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] numbers = IOUtils.readIntArray(in, count);
		int twos = 0;
		long more = 0;
		for (int i : numbers) {
			if (i > 2)
				more++;
			else if (i == 2)
				twos++;
		}
		long answer = more * twos + more * (more - 1) / 2;
		out.printLine(answer);
    }
}
