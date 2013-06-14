package net.egork;

import net.egork.graph.GraphUtils;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskE {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int edgeCount = in.readInt();
		int[] from = new int[edgeCount];
		int[] to = new int[edgeCount];
		IOUtils.readIntArrays(in, from, to);
		MiscUtils.decreaseByOne(from, to);
		int[][] graph = GraphUtils.buildSimpleOrientedGraph(count, from, to);
		int[][] reverse = GraphUtils.buildSimpleOrientedGraph(count, to, from);
		boolean[] interesting = new boolean[count];
		interesting[0] = true;
		for (int i = 0; i < count; i++) {
			if (interesting[i]) {
				for (int j : graph[i])
					interesting[j] = true;
			}
		}
		boolean[] reverseInteresting = new boolean[count];
		reverseInteresting[count - 1] = true;
		for (int i = count - 1; i >= 0; i--) {
			if (reverseInteresting[i]) {
				for (int j : reverse[i])
					reverseInteresting[j] = true;
			}
		}
		for (int i = 0; i < count; i++)
			interesting[i] &= reverseInteresting[i];
		int[] min = new int[count];
		int[] max = new int[count];
		Arrays.fill(min, Integer.MIN_VALUE);
		Arrays.fill(max, Integer.MAX_VALUE);
		min[0] = max[0] = 0;
		while (true) {
			boolean updated = false;
			for (int i = 0; i < count; i++) {
				if (!interesting[i])
					continue;
				for (int j : graph[i]) {
					if (!interesting[j])
						continue;
					if (min[j] < min[i] + 1) {
						min[j] = min[i] + 1;
						updated = true;
					}
					if (max[j] > max[i] + 2) {
						max[j] = max[i] + 2;
						updated = true;
					}
				}
			}
			for (int i = count - 1; i >= 0; i--) {
				if (!interesting[i])
					continue;
				for (int j : reverse[i]) {
					if (!interesting[j])
						continue;
					if (min[j] < min[i] - 2) {
						min[j] = min[i] - 2;
						updated = true;
					}
					if (max[j] > max[i] - 1) {
						max[j] = max[i] - 1;
						updated = true;
					}
				}
			}
			for (int i = 0; i < count; i++) {
				if (interesting[i] && min[i] > max[i]) {
					out.printLine("No");
					return;
				}
			}
			if (!updated)
				break;
		}
		out.printLine("Yes");
		for (int i = 0; i < edgeCount; i++) {
			if (!interesting[from[i]] || !interesting[to[i]])
				out.printLine(1);
			else
				out.printLine(min[to[i]] - min[from[i]]);
		}
	}
}
