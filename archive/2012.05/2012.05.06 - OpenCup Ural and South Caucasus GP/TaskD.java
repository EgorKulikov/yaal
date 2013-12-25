package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TaskD {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int planetCount = in.readInt();
		int flightCount = in.readInt();
		int[] from = new int[flightCount];
		int[] to = new int[flightCount];
		int[] fromTime = new int[flightCount];
		int[] toTime = new int[flightCount];
		for (int i = 0; i < flightCount; i++) {
			from[i] = in.readInt() - 1;
			to[i] = in.readInt() - 1;
			fromTime[i] = in.readInt();
			toTime[i] = in.readInt();
		}
		int start = in.readInt() - 1;
		int finish = in.readInt() - 1;
		int startTime = in.readInt();
		int finishTime = in.readInt();
		int[] timeCount = new int[planetCount];
		for (int i : from)
			timeCount[i]++;
		for (int i : to)
			timeCount[i]++;
		timeCount[start]++;
		timeCount[finish]++;
		int[][] times = new int[planetCount][];
		for (int i = 0; i < planetCount; i++)
			times[i] = new int[timeCount[i]];
		for (int i = 0; i < flightCount; i++) {
			times[from[i]][--timeCount[from[i]]] = fromTime[i];
			times[to[i]][--timeCount[to[i]]] = toTime[i];
		}
		times[start][--timeCount[start]] = startTime;
		times[finish][--timeCount[finish]] = finishTime;
		for (int i = 0; i < planetCount; i++)
			Arrays.sort(times[i]);
		int fakeFlightsCount = 0;
		for (int i = 0; i < planetCount; i++)
			fakeFlightsCount += Math.max(0, times[i].length - 1);
		int[] newFrom = new int[flightCount + fakeFlightsCount];
		int[] newTo = new int[flightCount + fakeFlightsCount];
		int[][] id = new int[planetCount][];
		for (int i = 0; i < planetCount; i++)
			id[i] = new int[times[i].length];
		int index = 0;
		for (int i = 0; i < planetCount; i++) {
			for (int j = 0; j < id[i].length; j++)
				id[i][j] = index++;
		}
		int vertexCount = index;
		for (int i = 0; i < flightCount; i++) {
			newFrom[i] = id[from[i]][Arrays.binarySearch(times[from[i]], fromTime[i])];
			newTo[i] = id[to[i]][Arrays.binarySearch(times[to[i]], toTime[i])];
		}
		index = flightCount;
		for (int i = 0; i < planetCount; i++) {
			for (int j = 1; j < times[i].length; j++) {
				newFrom[index] = id[i][j - 1];
				newTo[index++] = id[i][j];
			}
		}
		int[] degree = new int[vertexCount];
		for (int i : newFrom)
			degree[i]++;
		int[][] graph = new int[vertexCount][];
		for (int i = 0; i < vertexCount; i++)
			graph[i] = new int[degree[i]];
		for (int i1 = 0, newFromLength = newFrom.length; i1 < newFromLength; i1++) {
			int i = newFrom[i1];
			graph[i][--degree[i]] = i1;
		}
		int[] queue = new int[vertexCount];
		int size = 1;
		queue[0] = id[start][Arrays.binarySearch(times[start], startTime)];
		int[] last = new int[vertexCount];
		boolean[] visited = new boolean[vertexCount];
		visited[queue[0]] = true;
		for (int i = 0; i < size; i++) {
			int current = queue[i];
			for (int j : graph[current]) {
				int next = newTo[j];
				if (!visited[next]) {
					last[next] = j;
					visited[next] = true;
					queue[size++] = next;
				}
			}
		}
		int current = id[finish][Arrays.binarySearch(times[finish], finishTime)];
		if (!visited[current]) {
			out.printLine("Impossible");
			return;
		}
		List<Integer> edges = new ArrayList<Integer>();
		while (current != queue[0]) {
			int edge = last[current];
			if (edge < flightCount)
				edges.add(edge + 1);
			current = newFrom[edge];
		}
		Collections.reverse(edges);
		out.printLine(edges.size());
		out.printLine(edges.toArray());
	}
}
