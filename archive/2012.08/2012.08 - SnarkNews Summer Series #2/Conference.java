package net.egork;

import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.ListIndependentSetSystem;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Conference {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int edgeCount = in.readInt();
        int queryCount = in.readInt();
        IndependentSetSystem setSystem = new ListIndependentSetSystem(count);
        for (int i = 0; i < edgeCount; i++) {
            int first = in.readInt() - 1;
            int second = in.readInt() - 1;
            setSystem.join(first, second);
        }
        for (int i = 0; i < queryCount; i++) {
            int first = in.readInt() - 1;
            int second = in.readInt() - 1;
            out.printLine(setSystem.get(first) == setSystem.get(second) ? "Yes" : "No");
        }
	}
}
