package net.egork;

import net.egork.collections.comparators.IntComparator;
import net.egork.collections.heap.Heap;
import net.egork.graph.Graph;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskE {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int ordinaryRoadCount = in.readInt();
		int specialRoadCount = in.readInt();
		int ourStart = in.readInt() - 1;
		int villainStart = in.readInt() - 1;
		int finish = in.readInt() - 1;
		int[] ordinaryFrom = new int[ordinaryRoadCount];
		int[] ordinaryTo = new int[ordinaryRoadCount];
		int[] ordinaryDistance = new int[ordinaryRoadCount];
		IOUtils.readIntArrays(in, ordinaryFrom, ordinaryTo, ordinaryDistance);
		int[] specialFrom = new int[specialRoadCount];
		int[] specialTo = new int[specialRoadCount];
		int[] specialDistanceFrom = new int[specialRoadCount];
		int[] specialDistanceTo = new int[specialRoadCount];
		IOUtils.readIntArrays(in, specialFrom, specialTo, specialDistanceFrom, specialDistanceTo);
		MiscUtils.decreaseByOne(ordinaryFrom, ordinaryTo, specialFrom, specialTo);
		if (ourStart == villainStart) {
			out.printLine("DRAW");
			out.printLine(specialDistanceFrom);
			return;
		}
		Graph graph = new Graph(count);
		for (int i = 0; i < ordinaryRoadCount; i++)
			graph.addSimpleEdge(ordinaryFrom[i], ordinaryTo[i]);
		for (int i = 0; i < specialRoadCount; i++)
			graph.addSimpleEdge(specialFrom[i], specialTo[i]);
		boolean[] can = solve(count, ordinaryRoadCount, specialRoadCount, ourStart, villainStart, finish, 1, graph, ordinaryDistance, specialDistanceFrom, specialDistanceTo);
		if (can == null) {
			can = solve(count, ordinaryRoadCount, specialRoadCount, ourStart, villainStart, finish, 0, graph, ordinaryDistance, specialDistanceFrom, specialDistanceTo);
			if (can == null) {
				out.printLine("LOSE");
				return;
			}
			out.printLine("DRAW");
		} else
			out.printLine("WIN");
		int[] distance = new int[specialRoadCount];
		for (int i = 0; i < specialRoadCount; i++) {
			if (can[i])
				distance[i] = specialDistanceFrom[i];
			else
				distance[i] = specialDistanceTo[i];
		}
		out.printLine(distance);
    }

	private boolean[] solve(int count, int ordinaryRoadCount, int specialRoadCount, int ourStart, int villainStart, int finish, int gap, Graph graph, int[] ordinaryDistance, int[] specialDistanceFrom, int[] specialDistanceTo) {
		final long[] distance = new long[count];
		Arrays.fill(distance, Long.MAX_VALUE);
		Heap heap = new Heap(new IntComparator() {
			public int compare(int first, int second) {
				return Long.compare(distance[first], distance[second]);
			}
		}, count);
		int[] winner = new int[count];
		distance[ourStart] = gap;
		winner[ourStart] = 1;
		heap.add(ourStart);
		distance[villainStart] = 0;
		winner[villainStart] = -1;
		heap.add(villainStart);
		int[] last = new int[count];
		Arrays.fill(last, -1);
		while (!heap.isEmpty()) {
			int current = heap.poll();
			int[] specialDistance;
			if (winner[current] == 1)
				specialDistance = specialDistanceFrom;
			else
				specialDistance = specialDistanceTo;
			for (int i = graph.firstOutbound(current); i != -1; i = graph.nextOutbound(i)) {
				int destination = graph.destination(i);
				long total = distance[current];
				if (i < ordinaryRoadCount)
					total += ordinaryDistance[i];
				else
					total += specialDistance[i - ordinaryRoadCount];
				if (total < distance[destination] || total == distance[destination] && winner[current] == 1) {
					distance[destination] = total;
					if (winner[destination] == 0)
						heap.add(destination);
					else
						heap.shiftUp(heap.getIndex(destination));
					winner[destination] = winner[current];
					last[destination] = i;
				}
			}
		}
		if (winner[finish] != 1)
			return null;
		boolean[] result = new boolean[specialRoadCount];
		while (finish != ourStart) {
			if (last[finish] >= ordinaryRoadCount)
				result[last[finish] - ordinaryRoadCount] = true;
			finish = graph.source(last[finish]);
		}
		return result;
	}
}
