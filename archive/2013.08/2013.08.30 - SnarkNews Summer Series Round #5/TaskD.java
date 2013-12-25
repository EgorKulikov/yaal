package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] heights = IOUtils.readIntArray(in, count);
		out.printLine(go(heights, 0, count));
    }

	private int go(int[] heights, int from, int to) {
		if (from >= to)
			return 0;
		int at = ArrayUtils.minPosition(heights, from, to);
		int answer = (to - from) * heights[at];
		answer = Math.max(answer, go(heights, from, at));
		answer = Math.max(answer, go(heights, at + 1, to));
		return answer;
	}
}
