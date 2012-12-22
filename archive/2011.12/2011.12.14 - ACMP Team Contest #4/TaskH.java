package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskH {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		long vertexCount = in.readInt();
		int edgeCount = in.readInt();
		if (edgeCount <= (vertexCount - 2) * (vertexCount - 1) / 2)
			out.printLine("Yes");
		else
			out.printLine("No");
	}
}
