package net.egork;

public class ChromaticNumber {
	public int minColors(String[] graph) {
		int count = graph.length;
		boolean[][] edges = new boolean[count][count];
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < count; j++)
				edges[i][j] = graph[i].charAt(j) == 'Y';
		}
		int answer = 0;
		boolean[] processed = new boolean[count];
		for (int i = 0; i < count; i++) {
			for (int j = i + 1; j < count; j++) {
				for (int k = j + 1; k < count; k++) {
					if (!edges[i][j] && !edges[j][k] && !edges[i][k]) {
						processed[i] = processed[j] = processed[k] = true;
						answer++;
					}
				}
			}
		}
		for (int i = 0; i < count; i++) {
			if (processed[i])
				continue;
			processed[i] = true;
			int[] mayBeSameColor = new int[2];
			int size = 0;
			for (int j = 0; j < count; j++) {
				if (i != j && !edges[i][j] && !processed[j])
					mayBeSameColor[size++] = j;
			}
			if (size == 0) {
				answer++;
				continue;
			}
			int curCount = 1;
			for (int j = 0; j < size; j++) {
				int current = mayBeSameColor[j];
				if (processed[current])
					continue;
				while (current != -1) {
					curCount++;
					processed[current] = true;
					int next = -1;
					for (int k = 0; k < count; k++) {
						if (k != current && !edges[current][k] && !processed[k]) {
							next = k;
							break;
						}
					}
					if (next == -1)
						break;
					current = next;
				}
			}
			answer += (curCount + 1) / 2;
		}
		return answer;
	}


}

