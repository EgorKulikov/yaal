package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
	private double[][] answer;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int whiteCount = in.readInt();
		int blackCount = in.readInt();
		answer = new double[whiteCount + 1][blackCount + 1];
		ArrayUtils.fill(answer, -1);
		out.printLine(go(whiteCount, blackCount));
	}

	private double go(int whiteCount, int blackCount) {
		if (answer[whiteCount][blackCount] != -1)
			return answer[whiteCount][blackCount];
		answer[whiteCount][blackCount] = 0;
		int remainingBlack = blackCount;
		double remainingProbability = 1;
		if (whiteCount == 0)
			return 0;
		answer[whiteCount][blackCount] += (double)whiteCount / (whiteCount + blackCount);
		if (remainingBlack == 0)
			return answer[whiteCount][blackCount];
		remainingProbability *= (double)remainingBlack / (whiteCount + remainingBlack);
		remainingBlack--;
		if (remainingBlack == 0)
			return answer[whiteCount][blackCount];
		remainingProbability *= (double)remainingBlack / (whiteCount + remainingBlack);
		remainingBlack--;
		if (remainingBlack == 0)
			return answer[whiteCount][blackCount] += remainingProbability;
		return answer[whiteCount][blackCount] += remainingProbability * ((double)whiteCount / (whiteCount + remainingBlack) * go(whiteCount - 1, remainingBlack) +
			(double)remainingBlack / (whiteCount + remainingBlack) * go(whiteCount, remainingBlack - 1));
	}
}
