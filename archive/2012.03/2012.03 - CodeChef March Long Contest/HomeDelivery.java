package net.egork;

import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.RecursiveIndependentSetSystem;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class HomeDelivery {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int vertexCount = in.readInt();
		int edgeCount = in.readInt();
		IndependentSetSystem setSystem = new RecursiveIndependentSetSystem(vertexCount);
		for (int i = 0; i < edgeCount; i++) {
			int from = in.readInt();
			int to = in.readInt();
			setSystem.join(from, to);
		}
		int queryCount = in.readInt();
		for (int i = 0; i < queryCount; i++) {
			int from = in.readInt();
			int to = in.readInt();
			out.printLine(setSystem.get(from) == setSystem.get(to) ? "YO" : "NO");
		}
	}
}
