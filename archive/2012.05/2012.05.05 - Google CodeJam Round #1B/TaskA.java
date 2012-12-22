package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.collections.sequence.Array;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] scores = IOUtils.readIntArray(in, count);
		long sumScores = ArrayUtils.sumArray(scores);
		double[] answer = new double[count];
		for (int i = 0; i < count; i++) {
			double left = 0;
			double right = 1;
			for (int j = 0; j < 100; j++) {
				double middle = (left + right) / 2;
				double curSum = middle;
				double curScore = scores[i] + sumScores * middle;
				for (int k = 0; k < count; k++) {
					if (i == k)
						continue;
					if (scores[k] < curScore)
						curSum += (curScore - scores[k]) / sumScores;
				}
				if (curSum > 1)
					right = middle;
				else
					left = middle;
			}
			answer[i] = (left + right) * 50;
		}
		out.print("Case #" + testNumber + ": ");
		out.printLine(Array.wrap(answer).toArray());
	}
}
