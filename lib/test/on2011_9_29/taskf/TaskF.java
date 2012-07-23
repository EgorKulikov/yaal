package on2011_9_29.taskf;



import net.egork.collections.intervaltree.SumIntervalTree;
import net.egork.graph.GraphUtils;
import net.egork.utils.io.InputReader;
import java.io.PrintWriter;

public class TaskF {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int count = in.readInt();
		int queryCount = in.readInt();
		int[] salary = new int[count];
		salary[0] = in.readInt();
		int[] from = new int[count - 1];
		int[] to = new int[count - 1];
		for (int i = 1; i < count; i++) {
			from[i - 1] = in.readInt();
			to[i - 1] = i;
			salary[i] = in.readInt();
		}
		int[][] graph = GraphUtils.buildSimpleOrientedGraph(count, from, to);
		int[] first = new int[count];
		int[] last = new int[count];
		if (count != createOrder(0, 0, graph, first, last))
			throw new RuntimeException();
		SumIntervalTree tree = new SumIntervalTree(count);
		for (int i = 0; i < count; i++)
			tree.update(first[i], first[i], salary[i]);
		for (int i = 0; i < queryCount; i++) {
			String type = in.readString();
			int index = in.readInt();
			int maxSalary = in.readInt();
			int delta = in.readInt();
			if ("employee".equals(type)) {
				if (tree.query(first[index], first[index]) < maxSalary)
					tree.update(first[index], first[index], delta);
			} else {
				if (tree.query(first[index], last[index] - 1) / (last[index] - first[index]) < maxSalary)
					tree.update(first[index], last[index] - 1, delta);
			}
		}
		for (int i = 0; i < count; i++)
			out.println(tree.query(first[i], first[i]));
	}

	private int createOrder(int vertex, int index, int[][] graph, int[] first, int[] last) {
		first[vertex] = index++;
		for (int i : graph[vertex])
			index = createOrder(i, index, graph, first, last);
		last[vertex] = index;
		return index;
	}
}
