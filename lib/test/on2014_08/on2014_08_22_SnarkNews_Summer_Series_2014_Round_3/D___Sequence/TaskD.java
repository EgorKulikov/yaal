package on2014_08.on2014_08_22_SnarkNews_Summer_Series_2014_Round_3.D___Sequence;



import net.egork.collections.intcollection.IntHashMap;
import net.egork.graph.Graph;
import net.egork.graph.GraphAlgorithms;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[][] sequences = new int[count][];
		for (int i = 0; i < count; i++) {
			sequences[i] = IOUtils.readIntArray(in, in.readInt());
		}
		IntHashMap map = new IntHashMap();
		int id = 0;
		for (int i = 0; i < count; i++) {
			for (int j : sequences[i]) {
				if (!map.contains(j)) {
					map.put(j, id++);
				}
			}
		}
		Graph graph = new Graph(id);
		for (int i = 0; i < count; i++) {
			for (int j = 1; j < sequences[i].length; j++) {
				graph.addSimpleEdge(map.get(sequences[i][j - 1]), map.get(sequences[i][j]));
			}
		}
		if (GraphAlgorithms.topologicalSort(graph) != null) {
			out.printLine("Yes");
		} else {
			out.printLine("No");
		}
	}
}
