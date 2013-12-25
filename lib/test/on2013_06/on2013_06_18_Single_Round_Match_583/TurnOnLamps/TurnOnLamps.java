package on2013_06.on2013_06_18_Single_Round_Match_583.TurnOnLamps;



import net.egork.graph.Graph;

public class TurnOnLamps {
	int answer;
	Graph graph;
	boolean[] init;
	boolean[] important;

    public int minimize(int[] roads, String initState, String isImportant) {
		answer = 0;
		int count = roads.length + 1;
		init = new boolean[count - 1];
		important = new boolean[count - 1];
		int[] to = new int[count - 1];
		for (int i = 0; i < count - 1; i++) {
			init[i] = initState.charAt(i) == '1';
			important[i] = isImportant.charAt(i) == '1';
			to[i] = i + 1;
		}
		graph = Graph.createGraph(count, roads, to);
		if (go(0, -1))
			answer++;
		return answer;
    }

	private boolean go(int vertex, int last) {
		int id = graph.firstOutbound(vertex);
		int looseEnds = 0;
		while (id != -1) {
			int next = graph.destination(id);
			if (next != last) {
				boolean end = go(next, vertex);
				if (important[id]) {
					if (init[id]) {
						if (end)
							answer++;
						end = false;
					} else
						end = true;
				}
				if (end)
					looseEnds++;
			}
			id = graph.nextOutbound(id);
		}
		answer += looseEnds >> 1;
		return (looseEnds & 1) != 0;
	}
}
