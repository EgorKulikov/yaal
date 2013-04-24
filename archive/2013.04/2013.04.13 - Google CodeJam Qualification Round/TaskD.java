package net.egork;

import net.egork.graph.Edge;
import net.egork.graph.Graph;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int keyCount = in.readInt();
		int count = in.readInt();
		int[] initial = new int[200];
		for (int i = 0; i < keyCount; i++)
			initial[in.readInt() - 1]++;
		int[] toOpen = new int[count];
		int[][] keys = new int[count][];
		for (int i = 0; i < count; i++) {
			toOpen[i] = in.readInt() - 1;
			int size = in.readInt();
			keys[i] = new int[size];
			for (int j = 0; j < size; j++)
				keys[i][j] = in.readInt() - 1;
		}
		int[] current = new int[200];
		boolean[] can = new boolean[1 << count];
		boolean[] good = new boolean[1 << count];
		can[0] = true;
		Graph<Integer> graph = new Graph<Integer>();
		for (int i = 0; i < (1 << count); i++) {
			if (!can[i])
				continue;
			System.arraycopy(initial, 0, current, 0, 200);
			for (int j = 0; j < count; j++) {
				if ((i >> j & 1) == 1) {
					current[toOpen[j]]--;
					for (int k : keys[j])
						current[k]++;
				}
			}
			for (int j = 0; j < count; j++) {
				if ((i >> j & 1) == 1 || current[toOpen[j]] == 0)
					continue;
				graph.addSimpleEdge(i, i + (1 << j));
				can[i + (1 << j)] = true;
			}
		}
		if (!can[(1 << count) - 1]) {
			out.printLine("Case #" + testNumber + ": IMPOSSIBLE");
			return;
		}
		good[(1 << count) - 1] = true;
		for (int i = (1 << count) - 1; i >= 0; i--) {
			if (!good[i])
				continue;
			for (Edge<Integer> edge : graph.getInbound(i))
				good[edge.getSource()] = true;
		}
		out.print("Case #" + testNumber + ":");
		int state = 0;
		for (int i = 0; i < count; i++) {
			int to = Integer.MAX_VALUE;
			for (Edge<Integer> edge : graph.getOutbound(state)) {
				if (can[edge.getDestination()] && good[edge.getDestination()])
					to = Math.min(to, edge.getDestination());
			}
			out.print("", Integer.bitCount(to - state - 1) + 1);
			state = to;
		}
		out.printLine();
    }
}
