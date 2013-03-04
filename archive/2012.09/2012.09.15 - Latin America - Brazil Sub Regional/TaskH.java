package net.egork;

import net.egork.collections.set.EHashSet;
import net.egork.graph.GraphUtils;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.*;

public class TaskH {
	long[] multipliers = new long[10000];
	long[] hashes = new long[10000];
	long[] answer = new long[10000];

	{
		Random random = new Random(239);
		for (int i = 0; i < 10000; i++)
			multipliers[i] = random.nextInt(10000000) * 2 + 3;
	}

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		if (in.isExhausted()) {
			throw new UnknownError();
		}
		int count = in.readInt();
		int[] firstFrom = new int[count - 1];
		int[] firstTo = new int[count - 1];
		int[] secondFrom = new int[count - 1];
		int[] secondTo = new int[count - 1];
		IOUtils.readIntArrays(in, firstFrom, firstTo);
		IOUtils.readIntArrays(in, secondFrom, secondTo);
		MiscUtils.decreaseByOne(firstFrom, firstTo, secondFrom, secondTo);
		Set<Long> firstEncodings = encode(firstFrom, firstTo);
		Set<Long> secondEncodings = encode(secondFrom, secondTo);
		if (firstEncodings.equals(secondEncodings)) {
			out.printLine("S");
		} else {
			out.printLine("N");
		}
	}

	private Set<Long> encode(int[] from, int[] to) {
		int[][] graph = GraphUtils.buildSimpleGraph(from.length + 1, from, to);
		int[] depth = new int[graph.length];
		Arrays.fill(depth, -1);
		int current = 0;
		for (int j = 0; j < 3; j++)
			current = opposite(graph, current);
		int[] firstDistance = distance(graph, current);
		current = opposite(graph, current);
		int diameter = firstDistance[current];
		int[] secondDistance = distance(graph, current);
		Set<Long> encodings = new EHashSet<Long>();
		for (int i = 0; i < graph.length; i++) {
			if (firstDistance[i] + secondDistance[i] == diameter && Math.abs(firstDistance[i] - secondDistance[i]) <= 1) {
				encodings.add(buildEncoding(i, -1, graph, 0));
			}
		}
		return encodings;
	}

	private int opposite(int[][] graph, int current) {
		int[] queue = new int[graph.length];
		boolean[] visited = new boolean[graph.length];
		queue[0] = current;
		visited[current] = true;
		int length = 1;
		for (int i = 0; i < graph.length; i++) {
			for (int k : graph[queue[i]]) {
				if (!visited[k]) {
					queue[length++] = k;
					visited[k] = true;
				}
			}
		}
		current = queue[graph.length - 1];
		return current;
	}

	private int[] distance(int[][] graph, int current) {
		int[] queue = new int[graph.length];
		boolean[] visited = new boolean[graph.length];
		int[] distance = new int[graph.length];
		queue[0] = current;
		visited[current] = true;
		int length = 1;
		for (int i = 0; i < graph.length; i++) {
			for (int k : graph[queue[i]]) {
				if (!visited[k]) {
					queue[length++] = k;
					distance[k] = distance[queue[i]] + 1;
					visited[k] = true;
				}
			}
		}
		return distance;
	}

	private long buildEncoding(int vertex, int last, int[][] graph, int depth) {
		for (int i : graph[vertex]) {
			if (i != last)
				answer[i] = buildEncoding(i, vertex, graph, depth + 1);
		}
		int index = 0;
		for (int i : graph[vertex]) {
			if (i != last)
				hashes[index++] = answer[i];
		}
		Arrays.sort(hashes, 0, index);
		long result = 1;
		for (int i = 0; i < index; i++) {
			result *= multipliers[depth];
			result += hashes[i];
		}
		return result;
	}

	private int calculateDepth(int vertex, int last, int[][] graph) {
		int result = 0;
		for (int i : graph[vertex]) {
			if (i != last) {
				result = Math.max(result, calculateDepth(i, vertex, graph));
			}
		}
		return result + 1;
	}
}
