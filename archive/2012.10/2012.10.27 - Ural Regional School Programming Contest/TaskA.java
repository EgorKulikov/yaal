package net.egork;

import net.egork.io.IOUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.Arrays;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int[] sides = IOUtils.readIntArray(in, 4);
		Arrays.sort(sides);
		if (sides[0] == sides[1] && sides[2] == sides[3])
			out.printLine("YES");
		else
			out.printLine("NO");

	}
}
