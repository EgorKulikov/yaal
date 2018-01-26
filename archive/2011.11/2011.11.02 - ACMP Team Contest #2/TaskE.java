package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskE {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		long total = in.readInt();
		long mathPassed = in.readInt();
		long physicsPassed = in.readInt();
		long nothingPassed = in.readInt();
		long bothPassed = mathPassed + physicsPassed + nothingPassed - total;
		long mathOnlyPassed = mathPassed - bothPassed;
		long physicsOnlyPassed = physicsPassed - bothPassed;
		out.printLine(bothPassed, mathOnlyPassed, physicsOnlyPassed);
	}
}
