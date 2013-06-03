package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int teamOrder = in.readInt();
		long prizeCount = in.readLong();
		long teamCount = 1L << teamOrder;
		long guarantee = go(teamCount, prizeCount);
		long can = -1;
		for (int i = 0; i <= teamOrder; i++) {
			if ((1L << i) * prizeCount >= teamCount) {
				can = teamCount - (1L << i);
				break;
			}
		}
		out.printLine("Case #" + testNumber + ":", guarantee, can);
    }

	private long go(long teamCount, long prizeCount) {
		if (prizeCount == teamCount)
			return teamCount - 1;
		if (prizeCount * 2 <= teamCount)
			return 0;
		return 2 * go(teamCount >> 1, prizeCount - (teamCount >> 1)) + 2;
	}
}
