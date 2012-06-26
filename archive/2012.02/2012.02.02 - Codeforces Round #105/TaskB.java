package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int princessSpeed = in.readInt();
		int dragonSpeed = in.readInt();
		int initialDelay = in.readInt();
		int delayPerTreasure = in.readInt();
		int distance = in.readInt();
		if (princessSpeed >= dragonSpeed) {
			out.printLine(0);
			return;
		}
		double delay = initialDelay;
		int answer = 0;
		while (true) {
			double willCatchAt = delay + delay * princessSpeed / (double)(dragonSpeed - princessSpeed);
			if (willCatchAt > (double)distance / princessSpeed - 1e-10)
				break;
			answer++;
			delay = 2 * willCatchAt - delay + delayPerTreasure;
		}
		out.printLine(answer);
	}
}
