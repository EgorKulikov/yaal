package on2013_08.on2013_08_27_Single_Round_Match_589.GearsDiv1;



import net.egork.graph.Graph;
import net.egork.graph.MaxFlow;

public class GearsDiv1 {
	char[] colors = "RGB".toCharArray();

    public int getmin(String color, String[] graph) {
		int count = color.length();
		int answer = Integer.MAX_VALUE;
		for (char first : colors) {
			for (char second : colors) {
				if (first == second)
					break;
				Graph current = new Graph(count + 2);
				for (int i = 0; i < count; i++) {
					if (color.charAt(i) == first) {
						current.addFlowEdge(count, i, 1);
						for (int j = 0; j < count; j++) {
							if (graph[i].charAt(j) == 'Y')
								current.addFlowEdge(i, j, 1);
						}
					} else if (color.charAt(i) == second)
						current.addFlowEdge(i, count + 1, 1);
				}
				answer = (int) Math.min(answer, MaxFlow.dinic(current, count, count + 1));
			}
		}
		return answer;
    }
}
