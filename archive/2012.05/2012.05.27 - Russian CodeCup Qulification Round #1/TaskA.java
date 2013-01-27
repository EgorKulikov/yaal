package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int[] sides = IOUtils.readIntArray(in, 12);
		Arrays.sort(sides);
		if (sides[11] == 0)
			throw new UnknownError();
		if (sides[0] == sides[3] && sides[4] == sides[7] && sides[8] == sides[11])
			out.printLine("yes");
		else
			out.printLine("no");
	}
}
