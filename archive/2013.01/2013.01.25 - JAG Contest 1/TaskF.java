package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.collections.comparators.IntComparator;
import net.egork.collections.heap.Heap;
import net.egork.graph.GraphUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskF {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int edgeCount = in.readInt();
		int start1 = in.readInt() - 1;
		int start2 = in.readInt() - 1;
		int target = in.readInt() - 1;
		int[] from = new int[edgeCount];
		int[] to = new int[edgeCount];
		int[] length = new int[edgeCount];
		for (int i = 0; i < edgeCount; i++) {
			from[i] = in.readInt() - 1;
			to[i] = in.readInt() - 1;
			String curLength = in.readString();
			if (curLength.charAt(0) == 'x')
				length[i] = -1;
			else
				length[i] = Integer.parseInt(curLength);
		}
		int[][] graph = GraphUtils.buildGraph(count, from, to);
		final long[][] distance = new long[101][count];
		ArrayUtils.fill(distance, Long.MAX_VALUE / 2);
		distance[0][target] = 0;
		boolean[] processed = new boolean[count];
		Heap heap = new Heap(count, count);
		for (int i = 0; i <= 100; i++) {
			heap.clear();
			final int magic = i;
			heap.setComparator(new IntComparator() {
				public int compare(int first, int second) {
					return IntegerUtils.longCompare(distance[magic][first], distance[magic][second]);
				}
			});
			Arrays.fill(processed, false);
			for (int j = 0; j < count; j++) {
				if (distance[i][j] != Long.MAX_VALUE / 2) {
					heap.add(j);
					processed[j] = true;
				}
			}
			while (!heap.isEmpty()) {
				int current = heap.poll();
				for (int j : graph[current]) {
					if (length[j] == -1)
						continue;
					int other = GraphUtils.otherVertex(current, from[j], to[j]);
					long value = distance[i][current] + length[j];
					if (value < distance[i][other]) {
						distance[i][other] = value;
						if (processed[other])
							heap.shiftUp(heap.getIndex(other));
						else {
							heap.add(other);
							processed[other] = true;
						}
					}
				}
			}
			if (i != 100) {
				for (int j = 0; j < count; j++) {
					if (distance[i][j] != Long.MAX_VALUE / 2) {
						for (int k : graph[j]) {
							if (length[k] == -1) {
								int other = GraphUtils.otherVertex(j, from[k], to[k]);
								distance[i + 1][other] = Math.min(distance[i + 1][other], distance[i][j]);
							}
						}
					}
				}
			}
		}
		int[] firstMagic = new int[101];
		long[] firstDelta = new long[101];
		int firstCount = 0;
		long max = Long.MAX_VALUE / 2;
		for (int i = 0; i <= 100; i++) {
			if (distance[i][start1] < max) {
				firstMagic[firstCount] = i;
				firstDelta[firstCount++] = distance[i][start1];
				max = distance[i][start1];
			}
		}
		int[] secondMagic = new int[101];
		long[] secondDelta = new long[101];
		int secondCount = 0;
		max = Long.MAX_VALUE / 2;
		for (int i = 0; i <= 100; i++) {
			if (distance[i][start2] < max) {
				secondMagic[secondCount] = i;
				secondDelta[secondCount++] = distance[i][start2];
				max = distance[i][start2];
			}
		}
		long answer = Long.MAX_VALUE;
		long[] secondMin = new long[secondCount];
		long[] secondMax = new long[secondCount];
		for (int j = 0; j < secondCount; j++) {
			secondMin[j] = 0;
			secondMax[j] = Long.MAX_VALUE;
			for (int k = 0; k < j; k++)
				secondMax[j] = Math.min(secondMax[j], (secondDelta[k] - secondDelta[j]) / (secondMagic[j] - secondMagic[k]));
			for (int k = j + 1; k < secondCount; k++)
				secondMin[j] = Math.max(secondMin[j], (secondDelta[j] - secondDelta[k]) / (secondMagic[k] - secondMagic[j]) + 1);
		}
		for (int i = 0; i < firstCount; i++) {
			long firstMin = 0;
			long firstMax = Long.MAX_VALUE;
			for (int j = 0; j < i; j++)
				firstMax = Math.min(firstMax, (firstDelta[j] - firstDelta[i]) / (firstMagic[i] - firstMagic[j]));
			for (int j = i + 1; j < firstCount; j++)
				firstMin = Math.max(firstMin, (firstDelta[i] - firstDelta[j]) / (firstMagic[j] - firstMagic[i]) + 1);
			if (firstMin > firstMax)
				continue;
			for (int j = 0; j < secondCount; j++) {
				if (secondMin[j] > secondMax[j])
					continue;
				if (firstMin > secondMax[j] || firstMax < secondMin[j])
					continue;
				long min = Math.max(firstMin, secondMin[j]);
				max = Math.min(firstMax, secondMax[j]);
				long cand1 = min * firstMagic[i] + firstDelta[i] - min * secondMagic[j] - secondDelta[j];
				long cand2;
				if (max == Long.MAX_VALUE) {
					if (firstMagic[i] > secondMagic[i])
						cand2 = Long.MAX_VALUE;
					else
						cand2 = -Long.MAX_VALUE;
				} else
					cand2 = max * firstMagic[i] + firstDelta[i] - max * secondMagic[j] - secondDelta[j];
				answer = Math.min(answer, Math.abs(cand1));
				answer = Math.min(answer, Math.abs(cand2));
				if (cand1 < 0 ^ cand2 < 0) {
					long delta = firstMagic[i] - secondMagic[j];
					if (delta == 0)
						continue;
					long remainder = Math.abs(cand1) % Math.abs(delta);
					answer = Math.min(answer, remainder);
					answer = Math.min(answer, Math.abs(delta) - remainder);
				}
			}
		}
		out.printLine(answer);
    }
}
