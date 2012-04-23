package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class FitToPlay {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] goals = IOUtils.readIntArray(in, count);
		int minGoals = Integer.MAX_VALUE;
		int answer = 0;
		for (int i = 0; i < count; i++) {
			minGoals = Math.min(minGoals, goals[i]);
			answer = Math.max(answer, goals[i] - minGoals);
		}
		if (answer == 0)
			out.printLine("UNFIT");
		else
			out.printLine(answer);
	}
}
