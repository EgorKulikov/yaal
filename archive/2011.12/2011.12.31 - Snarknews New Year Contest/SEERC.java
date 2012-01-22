package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class SEERC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int sideCount = in.readInt();
		int time = in.readInt();
		int capacity = in.readInt();
		int answer = Math.max(sideCount, (count * sideCount + capacity - 1) / capacity) * time;
		out.printLine(answer);
	}
}
