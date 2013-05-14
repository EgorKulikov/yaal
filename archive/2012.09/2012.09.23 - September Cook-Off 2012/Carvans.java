package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Carvans {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] speeds = IOUtils.readIntArray(in, count);
		int min = Integer.MAX_VALUE;
		int answer = 0;
		for (int i : speeds) {
			if (i <= min) {
				answer++;
				min = i;
			}
		}
		out.printLine(answer);
	}
}
