package net.egork;

import net.egork.graph.Edge;
import net.egork.graph.Graph;
import net.egork.graph.WeightedFlowEdge;

import java.util.Arrays;

public class PrinceXDominoes {
	public int play(String[] dominoes) {
		int count = dominoes.length;
		int[][] edgeCount = new int[count + 2][count + 2];
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < count; j++) {
				if (dominoes[i].charAt(j) != '.')
					edgeCount[i][j] = dominoes[i].charAt(j) - 'A' + 1;
			}
		}
		boolean[][] connected = new boolean[count][count];
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < count; j++)
				connected[i][j] = edgeCount[i][j] != 0;
			connected[i][i] = true;
		}
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < count; j++) {
				for (int k = 0; k < count; k++)
					connected[j][k] |= connected[j][i] && connected[i][k];
			}
		}
		for (int i = 0; i < count; i++) {
			if (!connected[0][i])
				return -1;
		}
		int answer = 0;
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < count; j++) {
				if (edgeCount[i][j] != 0) {
					edgeCount[count][j]++;
					edgeCount[i][count + 1]++;
					edgeCount[i][j]--;
					answer++;
				}
			}
		}
		Graph graph = new Graph(count + 2);
		for (int i = 0; i < count + 2; i++) {
			for (int j = 0; j < count + 2; j++) {
				if (edgeCount[i][j] != 0)
					graph.add(new WeightedFlowEdge(i, j, Math.max(i, j) >= count ? 0 : -1, edgeCount[i][j]));
			}
		}
		Edge[] last = new Edge[count + 2];
		Edge[] next = new Edge[count + 2];
		long[] distance = new long[count + 2];
		boolean[] visited = new boolean[count + 2];
		count += 2;
		while (true) {
			Arrays.fill(distance, Long.MAX_VALUE);
			Arrays.fill(last, null);
			distance[count - 2] = 0;
			for (int i = 0; i < 2 * count; i++) {
				for (int j = 0; j < count; j++) {
					if (distance[j] == Long.MAX_VALUE)
						continue;
					for (Edge edge : graph.getIncident(j)) {
						if (edge.getCapacity() == 0)
							continue;
						if (distance[edge.getDestination()] > distance[j] + edge.getWeight()) {
							distance[edge.getDestination()] = distance[j] + edge.getWeight();
							last[edge.getDestination()] = edge;
						}
					}
				}
			}
			if (distance[count - 1] == Long.MAX_VALUE)
				break;
			Arrays.fill(visited, false);
			int current = count - 1;
			while (current != count - 2 && !visited[current]) {
				visited[current] = true;
				next[last[current].getSource()] = last[current];
				current = last[current].getSource();
			}
			int start = current;
			int stopAt = current == count - 2 ? count - 1 : current;
			long minFlow = Long.MAX_VALUE;
			do {
				minFlow = Math.min(minFlow, next[current].getCapacity());
				current = next[current].getDestination();
			} while (current != stopAt);
			current = start;
			do {
				next[current].pushFlow(minFlow);
				answer -= minFlow * next[current].getWeight();
				current = next[current].getDestination();
			} while (current != stopAt);
		}
		for (Edge edge : graph.getIncident(count - 2)) {
			if (edge.getCapacity() != 0)
				return -1;
		}
		return answer;
	}


}

