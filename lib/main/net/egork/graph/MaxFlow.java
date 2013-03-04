package net.egork.graph;

import java.util.Arrays;

/**
 * @author Egor Kulikov (egorku@yandex-team.ru)
 */
public class MaxFlow<V> {
	private final Graph<V> graph;
	private int sourceID;
	private int destinationID;
	private int[] queue;
	private int[] distance;
	private int[] nextEdge;

	private MaxFlow(Graph<V> graph, V source, V destination) {
		this.graph = graph;
		sourceID = graph.resolve(source);
		destinationID = graph.resolve(destination);
		int vertexCount = graph.getVertexCount();
		queue = new int[vertexCount];
		distance = new int[vertexCount];
		nextEdge = new int[vertexCount];
	}

	public static<V> long dinic(Graph<V> graph, V source, V destination) {
		return new MaxFlow<V>(graph, source, destination).dinic();
	}

	private long dinic() {
		long totalFlow = 0;
		while (true) {
			edgeDistances();
			if (distance[destinationID] == -1)
				break;
			Arrays.fill(nextEdge, -2);
			totalFlow += dinicImpl(sourceID, Long.MAX_VALUE);
		}
		return totalFlow;
	}

	private void edgeDistances() {
		Arrays.fill(distance, -1);
		distance[sourceID] = 0;
		int size = 1;
		queue[0] = sourceID;
		for (int i = 0; i < size; i++) {
			int current = queue[i];
			int edgeID = graph.firstOutbound[current];
			while (edgeID != -1) {
				if (graph.removed[edgeID] || graph.capacity[edgeID] == 0) {
					edgeID = graph.nextOutbound[edgeID];
					continue;
				}
				int next = graph.to[edgeID];
				if (distance[next] == -1) {
					distance[next] = distance[current] + 1;
					queue[size++] = next;
				}
				edgeID = graph.nextOutbound[edgeID];
			}
		}
	}

	private long dinicImpl(int sourceID, long flow) {
		if (sourceID == destinationID)
			return flow;
		if (flow == 0 || distance[sourceID] == distance[destinationID])
			return 0;
		int edgeID = nextEdge[sourceID];
		if (edgeID == -2)
			nextEdge[sourceID] = edgeID = graph.firstOutbound[sourceID];
		long totalPushed = 0;
		while (edgeID != -1) {
			int nextDestinationID = graph.to[edgeID];
			if (graph.removed[edgeID] || graph.capacity[edgeID] == 0 || distance[nextDestinationID] != distance[sourceID] + 1) {
				nextEdge[sourceID] = edgeID = graph.nextOutbound[edgeID];
				continue;
			}
			long pushed = dinicImpl(nextDestinationID, Math.min(flow, graph.capacity[edgeID]));
			if (pushed != 0) {
				graph.edges[edgeID].pushFlow(pushed);
				flow -= pushed;
				totalPushed += pushed;
				if (flow == 0)
					return totalPushed;
			}
			nextEdge[sourceID] = edgeID = graph.nextOutbound[edgeID];
		}
		return totalPushed;
	}
}
