package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class BuildingConstruction {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] heights = IOUtils.readIntArray(in, count);
		int[] cost = IOUtils.readIntArray(in, count);
		long answer = 0;
		long delta = 0;
		long[] deltaChange = new long[10001];
		for (int i = 0; i < count; i++) {
			answer += (heights[i] + 1) * cost[i];
			delta -= cost[i];
			deltaChange[heights[i]] += 2 * cost[i];
		}
		long current = answer;
		for (int i = 0; i <= 10000; i++) {
			current += delta;
			delta += deltaChange[i];
			answer = Math.min(answer, current);
		}
		out.printLine(answer);
	}
}
