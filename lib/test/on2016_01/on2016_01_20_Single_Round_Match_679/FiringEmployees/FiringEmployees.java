package on2016_01.on2016_01_20_Single_Round_Match_679.FiringEmployees;



import net.egork.collections.intcollection.Range;
import net.egork.generated.collections.function.IntToIntFunction;
import net.egork.graph.Graph;

public class FiringEmployees {
	public int fire(int[] manager, int[] salary, int[] productivity) {
		Graph graph = Graph.createGraph(manager.length + 1, manager, Range.range(manager.length).map(
				(IntToIntFunction) x -> x + 1).compute().toArray());
		int[] answer = new int[manager.length + 1];
		for (int i = manager.length; i >= 0; i--) {
			int current = i == 0 ? 0 : productivity[i - 1] - salary[i - 1];
			for (int j = graph.firstOutbound(i); j != -1; j = graph.nextOutbound(j)) {
				current += answer[graph.destination(j)];
			}
			answer[i] = Math.max(current, 0);
		}
		return answer[0];
	}
}
