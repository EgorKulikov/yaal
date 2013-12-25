package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] height = IOUtils.readIntArray(in, count);
		int[] cost = IOUtils.readIntArray(in, count);
		int head = 0;
		int end = 1;
		long[] constant = new long[count];
		long[] delta = new long[count];
		long[] until = new long[count];
		delta[0] = cost[0];
		until[0] = Long.MAX_VALUE;
		for (int i = 1; i < count; i++) {
			while (until[head] < height[i])
				head++;
			end++;
			constant[end] = constant[head] + delta[head] * height[i];
			delta[end] = cost[i];
			until[end] = Long.MAX_VALUE;
			while (true) {
				until[end - 1] = (constant[end] - constant[end - 1]) / (delta[end - 1] - delta[end]);
				if (end - 1 == head || until[end - 1] > until[end - 2])
					break;
				constant[end - 1] = constant[end];
				delta[end - 1] = delta[end];
				until[end - 1] = until[end];
				end--;
			}
		}
		out.printLine(constant[end]);
    }
}
