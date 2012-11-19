package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.collections.comparators.IntComparator;
import net.egork.collections.sequence.Array;
import net.egork.graph.GraphUtils;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int edgeCount = in.readInt();
		int headCount = in.readInt();
		int tailCount = in.readInt();
		int[] from = new int[edgeCount];
		int[] to = new int[edgeCount];
		IOUtils.readIntArrays(in, from, to);
		MiscUtils.decreaseByOne(from, to);
		int[][] graph = GraphUtils.buildSimpleGraph(count, from, to);
		for (int[] vertex : graph)
			ArrayUtils.sort(vertex, IntComparator.DEFAULT);
		for (int i = 0; i < count; i++) {
			for (int j : graph[i]) {
				if (i < j)
					break;
				if (Math.max(graph[i].length, graph[j].length) <= Math.max(headCount, tailCount) || Math.min(graph[i].length, graph[j].length) <= Math.min(headCount, tailCount))
					continue;
				int total = graph[i].length - 1 + graph[j].length - 1;
				int[] sample = (graph[i].length < graph[j].length) ? graph[i] : graph[j];
				int[] target = (graph[i].length < graph[j].length) ? graph[j] : graph[i];
				for (int k : sample) {
					if (Arrays.binarySearch(target, k) >= 0)
						total--;
				}
				if (total < headCount + tailCount)
					continue;
				if (graph[i].length > graph[j].length ^ headCount > tailCount) {
					int temp = i;
					i = j;
					j = temp;
				}
				out.printLine("YES");
				out.printLine(i + 1, j + 1);
				boolean[] used = new boolean[count];
				int[] head = new int[headCount];
				int l = 0;
				for (int k : graph[i]) {
					if (k != j && Arrays.binarySearch(graph[j], k) < 0) {
						head[l++] = k + 1;
						used[k] = true;
					}
					if (l == headCount)
						break;
				}
				if (l != headCount) {
					for (int k : graph[i]) {
						if (k != j && !used[k]) {
							head[l++] = k + 1;
							used[k] = true;
						}
						if (l == headCount)
							break;
					}
				}
				int[] tail = new int[tailCount];
				l = 0;
				for (int k : graph[j]) {
					if (k != i && !used[k])
						tail[l++] = k + 1;
					if (l == tailCount)
						break;
				}
				out.printLine(Array.wrap(head).toArray());
				out.printLine(Array.wrap(tail).toArray());
				return;
			}
		}
		out.printLine("NO");
	}
}
