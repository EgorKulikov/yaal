package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
        int vertexCount = in.readInt();
        int edgeCount = in.readInt();
        int[] degree = new int[vertexCount];
        for (int i = 0; i < 2 * edgeCount; i++)
            degree[in.readInt() - 1]++;
        for (int i : degree) {
            if (i != 3) {
                out.printLine("Falsification");
                return;
            }
        }
        out.printLine("Glupov");
	}
}
