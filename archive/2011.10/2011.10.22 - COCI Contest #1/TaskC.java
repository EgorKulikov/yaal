package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;

public class TaskC {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int count = in.readInt();
		int[] names = IOUtils.readIntArray(in, count);
		long result = 0;
		for (int i = 0; i < 20; i++) {
			long current = 0;
			for (int j : names)
				current += j >> i & 1;
			long other = count - current;
			result += (current * other) << i;
		}
		out.println(result);
	}
}
