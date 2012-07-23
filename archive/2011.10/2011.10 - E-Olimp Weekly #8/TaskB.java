package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int timePerHamburger = in.readInt();
		int timePerCheeseburger = in.readInt();
		int totalTime = in.readInt();
		int minTime = Math.min(timePerHamburger, timePerCheeseburger);
		int maxTime = Math.max(timePerHamburger, timePerCheeseburger);
		int limit = Math.min(minTime - 1, totalTime / maxTime);
		int sandwichCount = 0;
		int remaining = totalTime;
		for (int i = 0; i <= limit; i++) {
			int currentCount = i + (totalTime - maxTime * i) / minTime;
			int currentRemaining = totalTime - i * maxTime - (currentCount - i) * minTime;
			if (currentRemaining < remaining || currentRemaining == remaining && currentCount > sandwichCount) {
				sandwichCount = currentCount;
				remaining = currentRemaining;
			}
		}
		if (remaining == 0)
			out.printLine(sandwichCount);
		else
			out.printLine(sandwichCount, remaining);
	}
}
