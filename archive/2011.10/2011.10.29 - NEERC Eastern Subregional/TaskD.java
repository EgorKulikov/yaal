package net.egork;

import net.egork.graph.GraphUtils;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;

public class TaskD {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int edgeCount = in.readInt();
		int vertexCount = in.readInt();
		int[] first = new int[edgeCount];
		int[] second = new int[edgeCount];
		IOUtils.readIntArrays(in, first, second);
		MiscUtils.decreaseByOne(first, second);
		int[][] graph = GraphUtils.buildSimpleGraph(vertexCount, first, second);
		int[] queue = new int[vertexCount];
		int maxDelta = -1;
		int[] answer = null;
		for (int i = 0; i < vertexCount; i++) {
			boolean good = true;
			boolean[] assigned = new boolean[vertexCount];
			int[] current = new int[vertexCount];
			current[i] = 1;
			assigned[i]= true;
			queue[0] = i;
			int size = 1;
			for (int j = 0; j < size && good; j++) {
				int pilot = queue[j];
				for (int k : graph[pilot]) {
					if (assigned[k] && current[k] == current[pilot]) {
						good = false;
						break;
					} else if (!assigned[k]) {
						assigned[k] = true;
						current[k] = current[pilot] + 1;
						queue[size++] = k;
					}
				}
			}
			if (!good) {
				out.println(-1);
				return;
			}
			for (int l = 0; l < vertexCount; l++) {
				if (assigned[l])
					continue;
				current[l] = 50;
				assigned[l]= true;
				queue[0] = l;
				size = 1;
				for (int j = 0; j < size && good; j++) {
					int pilot = queue[j];
					for (int k : graph[pilot]) {
						if (assigned[k] && current[k] == current[pilot]) {
							good = false;
							break;
						} else if (!assigned[k]) {
							assigned[k] = true;
							current[k] = current[pilot] - 1;
							queue[size++] = k;
						}
					}
				}
				if (!good) {
					out.println(-1);
					return;
				}
			}
			int currentDelta = 0;
			for (int j = 0; j < vertexCount; j++) {
				for (int k = 0; k < vertexCount; k++)
					currentDelta = Math.max(currentDelta, current[j] - current[k]);
			}
			if (maxDelta < currentDelta) {
				maxDelta = currentDelta;
				answer = current;
			}
		}
		out.println(maxDelta);
		IOUtils.printArray(answer, out);
	}
}
