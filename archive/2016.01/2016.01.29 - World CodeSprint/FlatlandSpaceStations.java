package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class FlatlandSpaceStations {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int m = in.readInt();
		int[] c = IOUtils.readIntArray(in, m);
		Arrays.sort(c);
		int answer = Math.max(c[0], n - 1 - c[m - 1]);
		for (int i = 1; i < m; i++) {
			answer = Math.max(answer, (c[i] - c[i - 1]) / 2);
		}
		out.printLine(answer);
	}
}
