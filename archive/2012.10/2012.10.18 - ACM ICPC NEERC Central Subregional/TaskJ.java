package net.egork;

import net.egork.collections.Pair;
import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.RecursiveIndependentSetSystem;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Edge;
import net.egork.graph.Graph;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;
import java.util.Stack;

public class TaskJ {
	boolean[] isBridge;
	boolean[] visited;
	BidirectionalGraph<Integer> graph;
	int[] timeIn;
	int[] fUp;
	int timer;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int edgeCount = in.readInt();
		int[] from = new int[edgeCount];
		int[] to = new int[edgeCount];
		IOUtils.readIntArrays(in, from, to);
		MiscUtils.decreaseByOne(from, to);
		graph = new BidirectionalGraph<Integer>(count, edgeCount);
		for (int i = 0; i < count; i++)
			graph.addVertex(i);
		for (int i = 0; i < edgeCount; i++)
			graph.addSimpleEdge(from[i], to[i]);
		isBridge = new boolean[2 * edgeCount];
		visited = new boolean[count];
		timeIn = new int[count];
		fUp = new int[count];
		Stack<Callback> callbacks = new Stack<Callback>();
		callbacks.add(new DirectCall(0, -1));
		while (!callbacks.isEmpty()) {
			Callback callback = callbacks.pop();
			Pair<Callback, Callback> pair = callback.callback();
			if (pair != null) {
				callbacks.add(pair.second);
				callbacks.add(pair.first);
			}
		}
		IndependentSetSystem setSystem = new RecursiveIndependentSetSystem(count);
		for (int i = 0; i < 2 * edgeCount; i++) {
			if (!isBridge[i])
				setSystem.join(graph.from[i], graph.to[i]);
		}
		Graph<Integer> condensed = new Graph<Integer>();
		for (int i = 0; i < 2 * edgeCount; i++) {
			if (isBridge[i])
				condensed.addSimpleEdge(setSystem.get(graph.from[i]), setSystem.get(graph.to[i]));
		}
		int start = -1;
		for (int i = 0; i < count; i++) {
			if (setSystem.get(i) == i) {
				start = i;
				break;
			}
		}
		int[] queue = new int[count];
		int[] distance = new int[count];
		int end = start;
		for (int i = 0; i < 3; i++) {
			start = end;
			queue[0] = start;
			int length = 1;
			Arrays.fill(distance, -1);
			distance[start] = 0;
			for (int j = 0; j < length; j++) {
				for (Edge<Integer> edge : condensed.getOutbound(queue[j])) {
					int next = edge.getDestination();
					if (distance[next] == -1) {
						distance[next] = distance[queue[j]] + 1;
						queue[length++] = next;
					}
				}
			}
			int max = -1;
			for (int j = 0; j < count; j++) {
				if (distance[j] > max) {
					max = distance[j];
					end = j;
				}
			}
		}
		if (start == end) {
			start = 0;
			end = 1;
		}
		out.printLine(start + 1, end + 1);
	}

	private void findBridges(int current, int last) {
		visited[current] = true;
		timeIn[current] = fUp[current] = timer++;
		int edgeID = graph.firstOutbound[current];
		while (edgeID != -1) {
			int to = graph.to[edgeID];
			if (to == last) {
				edgeID = graph.nextOutbound[edgeID];
				continue;
			}
			if (visited[to])
				fUp[current] = Math.min(fUp[current], timeIn[to]);
			else {
				findBridges(to, current);
				fUp[current] = Math.min(fUp[current], fUp[to]);
				if (fUp[to] > timeIn[current]) {
					isBridge[edgeID] = true;
					isBridge[graph.transposedEdge[edgeID]] = true;
				}
			}
			edgeID = graph.nextOutbound[edgeID];
		}
	}

	interface Callback {
		Pair<Callback, Callback> callback();
	}

	class DirectCall implements Callback {
		int current;
		int last;

		DirectCall(int current, int last) {
			this.current = current;
			this.last = last;
		}

		public Pair<Callback, Callback> callback() {
			visited[current] = true;
			timeIn[current] = fUp[current] = timer++;
			int edgeID = graph.firstOutbound[current];
			while (edgeID != -1) {
				int to = graph.to[edgeID];
				if (to == last) {
					edgeID = graph.nextOutbound[edgeID];
					continue;
				}
				if (visited[to])
					fUp[current] = Math.min(fUp[current], timeIn[to]);
				else
					return Pair.makePair((Callback)new DirectCall(to, current), (Callback)new TailCall(current, last, edgeID));
				edgeID = graph.nextOutbound[edgeID];
			}
			return null;
		}
	}

	class TailCall implements Callback {
		int current;
		int last;
		int edgeID;

		TailCall(int current, int last, int edgeID) {
			this.current = current;
			this.last = last;
			this.edgeID = edgeID;
		}

		public Pair<Callback, Callback> callback() {
			int to = graph.to[edgeID];
			fUp[current] = Math.min(fUp[current], fUp[to]);
			if (fUp[to] > timeIn[current]) {
				isBridge[edgeID] = true;
				isBridge[graph.transposedEdge[edgeID]] = true;
			}
			edgeID = graph.nextOutbound[edgeID];
			while (edgeID != -1) {
				to = graph.to[edgeID];
				if (to == last) {
					edgeID = graph.nextOutbound[edgeID];
					continue;
				}
				if (visited[to])
					fUp[current] = Math.min(fUp[current], timeIn[to]);
				else
					return Pair.makePair((Callback)new DirectCall(to, current), (Callback)new TailCall(current, last, edgeID));
				edgeID = graph.nextOutbound[edgeID];
			}
			return null;
		}
	}
}
