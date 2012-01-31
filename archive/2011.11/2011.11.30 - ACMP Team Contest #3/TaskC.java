package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		long[] start = IOUtils.readLongArray(in, 3);
		Arrays.sort(start);
		if (start[0] == 0 && start[1] == 0 && start[2] != 1 || start[0] % 2 == start[1] % 2 && start[0] % 2 == start[2] % 2)
			out.printLine("No");
		else
			out.printLine("Yes");
	}
}
