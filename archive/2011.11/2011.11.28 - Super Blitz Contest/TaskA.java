package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int[] size = IOUtils.readIntArray(in, 3);
		int[] door = IOUtils.readIntArray(in, 2);
		Arrays.sort(size);
		Arrays.sort(door);
		if (size[0] > door[0] || size[1] > door[1])
			out.printLine("NO");
		else
			out.printLine("YES");
	}
}
