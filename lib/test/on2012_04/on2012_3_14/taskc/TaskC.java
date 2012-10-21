package on2012_04.on2012_3_14.taskc;



import net.egork.collections.sequence.Array;
import net.egork.collections.sequence.ListUtils;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] figureCount = new int[count];
		int[] score = new int[count];
		IOUtils.readIntArrays(in, figureCount, score);
		int maxMultiplier = in.readInt();
		long[] thresholds = IOUtils.readLongArray(in, maxMultiplier);
		for (int i = maxMultiplier - 1; i > 0; i--)
			thresholds[i] -= thresholds[i - 1];
		thresholds = Arrays.copyOf(thresholds, maxMultiplier + 1);
		thresholds[maxMultiplier] = Long.MAX_VALUE;
		Integer[] order = ListUtils.order(Array.wrap(score));
		long answer = 0;
		int curMultiplier = 0;
		for (int i : order) {
			while (figureCount[i] != 0) {
				long cur = Math.min(figureCount[i], thresholds[curMultiplier]);
				answer += cur * (curMultiplier + 1) * score[i];
				figureCount[i] -= cur;
				thresholds[curMultiplier] -= cur;
				if (thresholds[curMultiplier] == 0)
					curMultiplier++;
			}
		}
		out.printLine(answer);
	}
}
