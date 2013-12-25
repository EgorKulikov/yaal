package net.egork;

import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.RecursiveIndependentSetSystem;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class JackGoesToRapture {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int edgeCount = in.readInt();
		int[] from = new int[edgeCount];
		int[] to = new int[edgeCount];
		int[] cost = new int[edgeCount];
		IOUtils.readIntArrays(in, from, to, cost);
		MiscUtils.decreaseByOne(from, to);
		ArrayUtils.orderBy(cost, from, to);
		IndependentSetSystem setSystem = new RecursiveIndependentSetSystem(count);
		for (int i = 0; i < edgeCount; i++) {
			setSystem.join(from[i], to[i]);
			if (setSystem.get(0) == setSystem.get(count - 1)) {
				out.printLine(cost[i]);
				return;
			}
		}
		out.printLine("NO PATH EXISTS");
    }
}
