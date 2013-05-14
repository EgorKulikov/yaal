package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskQ {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		long[] x = new long[4];
		long[] y = new long[4];
		IOUtils.readLongArrays(in, x, y);
		long[] edges = new long[6];
		int index = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = i + 1; j < 4; j++)
				edges[index++] = (x[i] - x[j]) * (x[i] - x[j]) + (y[i] - y[j]) * (y[i] - y[j]);
		}
		Arrays.sort(edges);
		if (edges[0] == edges[3] && edges[4] == 2 * edges[3] && edges[4] == edges[5])
			out.printLine("YES");
		else
			out.printLine("NO");
	}
}
