package net.egork;

import net.egork.graph.GraphUtils;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TheLoyaltyOfTheOrcs {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] from = new int[count - 1];
		int[] to = new int[count - 1];
		IOUtils.readIntArrays(in, from, to);
		int deadCount = in.readInt();
		int[] dead = IOUtils.readIntArray(in, deadCount);
		MiscUtils.decreaseByOne(from, to, dead);
		int[][] graph = GraphUtils.buildSimpleGraph(count, from, to);
		boolean[] isDead = new boolean[count];
		for (int i : dead)
			isDead[i] = true;
		double answer = count;
		int[] queue = new int[count];
		int[] parent = new int[count];
		int[] deadParents = new int[count];
		int size = 1;
		for (int i = 0; i < count; i++) {
			int vertex = queue[i];
			answer -= 1d / (1 + deadParents[vertex]);
			if (isDead[vertex])
				deadParents[vertex]++;
			for (int j : graph[vertex]) {
				if (j != parent[vertex]) {
					parent[j] = vertex;
					deadParents[j] = deadParents[vertex];
					queue[size++] = j;
				}
			}
		}
		out.printLine(answer);
	}
}
