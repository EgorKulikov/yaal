package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskJ {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		if (in.isExhausted())
			throw new UnknownError();
		int count = in.readInt();
		int recharge = in.readInt();
		long answer = 0;
		while (count >= recharge) {
			int current = count / recharge;
			answer += current * recharge;
			count -= (recharge - 1) * current;
		}
		answer += count;
		out.printLine(answer);
    }
}
