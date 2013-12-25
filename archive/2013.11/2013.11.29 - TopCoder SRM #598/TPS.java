package net.egork;

public class TPS {
    public int minimalBeacons(String[] linked) {
		int count = linked.length;
		char[][] graph = new char[count][];
		for (int i = 0; i < count; i++)
			graph[i] = linked[i].toCharArray();
		boolean[] removed = new boolean[count];
		boolean[] single = new boolean[count];
		int remaining = count;
		for (int i = 0; i < count; i++) {
			int edges = 0;
			int first = -1;
			int second = -1;
			for (int j = 0; j < count; j++) {
				if (graph[i][j] == 'Y') {
					edges++;
					if (first == -1)
						first = j;
					else
						second = j;
				}
			}
			if (edges == 2) {
				removed[i] = true;
				graph[i][first] = graph[first][i] = 'N';
				graph[i][second] = graph[second][i] = 'N';
				graph[first][second] = graph[second][first] = 'Y';
				remaining--;
			} else if (edges == 1)
				single[i] = true;
		}
		if (remaining <= 2)
			return remaining - 1;
		int answer = 0;
		for (int i = 0; i < count; i++) {
			if (removed[i] || single[i])
				continue;
			int current = 0;
			for (int j = 0; j < count; j++) {
				if (graph[i][j] == 'Y' && single[j])
					current++;
			}
			answer += Math.max(0, current - 1);
		}
		return answer;
    }
}
