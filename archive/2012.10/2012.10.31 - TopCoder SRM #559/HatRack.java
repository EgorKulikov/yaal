package net.egork;

import net.egork.graph.GraphUtils;
import net.egork.misc.MiscUtils;

public class HatRack {
	int[] size;
	private int[][] graph;

	public long countWays(int[] knob1, int[] knob2) {
		int count = knob1.length + 1;
		MiscUtils.decreaseByOne(knob1, knob2);
		graph = GraphUtils.buildSimpleGraph(count, knob1, knob2);
		long answer = 0;
		for (int i = 0; i < count; i++) {
			size = new int[count];
			answer += go(i, -1);
		}
		return answer;
	}

	private long go(int vertex, int last) {
		size[vertex] = 1;
		int childrenCount = 0;
		long answer = 1;
		int firstSize = -1;
		int secondSize = -1;
		for (int i : graph[vertex]) {
			if (i != last) {
				childrenCount++;
				if (childrenCount > 2)
					return 0;
				answer *= go(i, vertex);
				size[vertex] += size[i];
				if (firstSize == -1)
					firstSize = size[i];
				else
					secondSize = size[i];
			}
		}
		if (childrenCount == 0)
			return answer;
		if (childrenCount == 1) {
			if (firstSize != 1)
				return 0;
			return answer;
		}
		if (firstSize < secondSize) {
			int temp = firstSize;
			firstSize = secondSize;
			secondSize = temp;
		}
		firstSize++;
		secondSize++;
		if ((firstSize & (firstSize - 1)) == 0) {
			if (secondSize == firstSize)
				return 2 * answer;
			if (secondSize >= (firstSize >> 1))
				return answer;
		}
		if ((secondSize & (secondSize - 1)) == 0) {
			if (firstSize < 2 * secondSize)
				return answer;
		}
		return 0;
	}
}
