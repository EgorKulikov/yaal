package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskJ {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int required = in.readInt();
		int firstTime = in.readInt();
		int secondTime = in.readInt();
		boolean firstFinished = false;
		boolean secondFinished = false;
		int answer = 0;
		for (int i = 0; ; i++) {
			if (i / firstTime + i / secondTime >= required) {
				if (!firstFinished && i % firstTime == 0) {
					firstFinished = true;
					answer += i / firstTime;
				}
				if (!secondFinished && i % secondTime == 0) {
					secondFinished = true;
					answer += i / secondTime;
				}
				if (firstFinished && secondFinished) {
					out.printLine(answer, i);
					return;
				}
			}
		}
	}
}
