package on2013_05.on2013_05_11_2013_TopCoder_Open_Algorithm.DancingFoxes;



import net.egork.collections.Pair;
import net.egork.collections.intcollection.IntList;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.graph.ShortestDistance;

public class DancingFoxes {
    public int minimalDays(String[] friendship) {
		int count = friendship.length;
		Graph graph = new BidirectionalGraph(count);
		for (int i = 0; i < count; i++) {
			for (int j = i + 1; j < count; j++) {
				if (friendship[i].charAt(j) == 'Y')
					graph.addWeightedEdge(i, j, 1);
			}
		}
		Pair<Long,IntList> result = ShortestDistance.dijkstraAlgorithm(graph, 0, 1);
		if (result == null)
			return -1;
		long length = result.first + 1;
		int answer = 0;
		while (length != 2) {
			length -= length / 3;
			answer++;
		}
		return answer;
    }
}
