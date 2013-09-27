package net.egork;

import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskH {
	int[][][] answer;
	int limit;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		limit = in.readInt();
		answer = new int[count + 1][count + 1][count + 1];
		ArrayUtils.fill(answer, -1);
		out.printLine(go(count, 0, 0));
    }

	int go(int total, int firstTaken, int secondTaken) {
		if (answer[total][firstTaken][secondTaken] != -1)
			return answer[total][firstTaken][secondTaken];
		if (total == 0)
			return answer[total][firstTaken][secondTaken] = 0;
		if (firstTaken + secondTaken == total)
			return answer[total][firstTaken][secondTaken] = go(firstTaken, 0, 0);
		answer[total][firstTaken][secondTaken] = 0;
		for (int i = Math.min(limit, total - firstTaken - secondTaken); i > 0; i--)
			answer[total][firstTaken][secondTaken] = Math.max(answer[total][firstTaken][secondTaken], total - go(total, secondTaken, firstTaken + i));
		return answer[total][firstTaken][secondTaken];
	}
}
