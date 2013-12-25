package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int edgeCount = in.readInt();
		int relaxCount = in.readInt();
		int[] relaxBy = IOUtils.readIntArray(in, relaxCount);
		MiscUtils.decreaseByOne(relaxBy);
		boolean[] isRelaxed = new boolean[count];
		for (int i : relaxBy)
			isRelaxed[i] = true;
		if (count == relaxCount || edgeCount > count * (count - 1) / 2 - (relaxCount - 1)) {
			out.printLine(-1);
			return;
		}
		int noRelaxSample = -1;
		int firstRelaxSample = -1;
		int secondRelaxSample = -1;
		for (int i = 0; i < count; i++) {
			if (isRelaxed[i]) {
				if (firstRelaxSample == -1)
					firstRelaxSample = i;
				else
					secondRelaxSample = i;
			} else
				noRelaxSample = i;
		}
		boolean[][] edges = new boolean[count][count];
		edges[firstRelaxSample][secondRelaxSample] = edges[secondRelaxSample][firstRelaxSample] = true;
		addEdge(firstRelaxSample, noRelaxSample, out, edges);
		edgeCount--;
		addEdge(secondRelaxSample, noRelaxSample, out, edges);
		edgeCount--;
		for (int i = 0; i < count; i++) {
			if (i != firstRelaxSample && i != secondRelaxSample && i != noRelaxSample) {
				addEdge(secondRelaxSample, i, out, edges);
				edgeCount--;
			}
		}
		for (int i = 0; i < count && edgeCount > 0; i++) {
			for (int j = i + 1; j < count && edgeCount > 0; j++) {
				if ((i != firstRelaxSample || !isRelaxed[j]) && (j != firstRelaxSample || !isRelaxed[i])) {
					if (addEdge(i, j, out, edges))
						edgeCount--;
				}
			}
		}
    }

	private boolean addEdge(int i, int j, OutputWriter out, boolean[][] edges) {
		if (edges[i][j])
			return false;
		edges[i][j] = edges[j][i] = true;
		out.printLine(i + 1, j + 1);
		return true;
	}
}
