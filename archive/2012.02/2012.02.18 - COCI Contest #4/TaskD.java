package net.egork;

import net.egork.collections.sequence.Array;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskD {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] slavkoFence = IOUtils.readIntArray(in, count);
		int[] heights = IOUtils.readIntArray(in, count);
		Arrays.sort(heights);
		int start = 0;
		int finish = count - 1;
		int[] answer = new int[count];
		for (int i = 1; i < count - 1; i++) {
			if ((i == 0 || slavkoFence[i] > slavkoFence[i - 1]) && (i == count - 1 || slavkoFence[i] > slavkoFence[i + 1]))
				answer[i] = heights[finish--];
			else if ((i == 0 || slavkoFence[i] < slavkoFence[i - 1]) && (i == count - 1 || slavkoFence[i] < slavkoFence[i + 1]))
				answer[i] = heights[start++];
		}
		for (int i = 0; i < count; i += count - 1) {
			if ((i == 0 || slavkoFence[i] > slavkoFence[i - 1]) && (i == count - 1 || slavkoFence[i] > slavkoFence[i + 1]))
				answer[i] = heights[finish--];
			else if ((i == 0 || slavkoFence[i] < slavkoFence[i - 1]) && (i == count - 1 || slavkoFence[i] < slavkoFence[i + 1]))
				answer[i] = heights[start++];
		}
		for (int i = 1; i < count - 1; i++) {
			if (slavkoFence[i] > slavkoFence[i - 1] && slavkoFence[i] < slavkoFence[i + 1])
				answer[i] = heights[start++];
			else if (slavkoFence[i] < slavkoFence[i - 1] && slavkoFence[i] > slavkoFence[i + 1])
				answer[i] = heights[finish--];
		}
		long niceness = 0;
		for (int i = 1; i < count; i++)
			niceness += Math.abs(answer[i] - answer[i - 1]);
		out.printLine(niceness);
		out.printLine(Array.wrap(answer).toArray());
	}
}
