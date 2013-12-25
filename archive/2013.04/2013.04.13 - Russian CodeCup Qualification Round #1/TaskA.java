package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int[] width = new int[3];
		int[] height = new int[3];
		IOUtils.readIntArrays(in, width, height);
		for (int i = 0; i < 3; i++) {
			if (width[i] > height[i]) {
				int temp = width[i];
				width[i] = height[i];
				height[i] = temp;
			}
		}
		int[] order = ArrayUtils.order(width);
		ArrayUtils.reverse(order);
		int current = 0;
		int answer = 0;
		for (int i : order) {
			if (height[i] > current) {
				answer += (height[i] - current) * width[i];
				current = height[i];
			}
		}
		if (answer == 0)
			throw new UnknownError();
		out.printLine(answer);
	}
}
