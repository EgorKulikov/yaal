package on2013_06.on2013_06_23_June_Cook_Off_2013.Tywins_Tactics;



import net.egork.collections.intervaltree.LongIntervalTree;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Edge;
import net.egork.graph.Graph;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TywinsTactics {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int queryCount = in.readInt();
		int[] value = IOUtils.readIntArray(in, count);
		int[] from = new int[count - 1];
		int[] to = new int[count - 1];
		IOUtils.readIntArrays(in, from, to);
		MiscUtils.decreaseByOne(from, to);
		Graph graph = BidirectionalGraph.createGraph(count, from, to);
		int[] order = new int[count];
		int[] until = new int[count];
		int[] last = new int[count];
		int[] stack = new int[count];
		int[] edge = new int[count];
		last[0] = -1;
		int head = 0;
		for (int i = 0; i < count; i++)
			edge[i] = graph.firstOutbound(i);
		int index = 1;
		while (head != -1) {
			int current = stack[head];
			while (edge[current] != -1 && graph.destination(edge[current]) == last[current])
				edge[current] = graph.nextOutbound(edge[current]);
			if (edge[current] == -1) {
				until[current] = index - 1;
				head--;
			} else {
				int next = graph.destination(edge[current]);
				order[index++] = next;
				last[next] = current;
				stack[++head] = next;
				edge[current] = graph.nextOutbound(edge[current]);
			}

		}
//		dfs(0, -1, 0, order, until, graph);
		int[] position = ArrayUtils.reversePermutation(order);
		LongIntervalTree tree = new LongIntervalTree(count) {
			@Override
			protected long joinValue(long left, long right) {
				return left + right;
			}

			@Override
			protected long joinDelta(long was, long delta) {
				if (delta == neutralDelta())
					return was;
				return delta;
			}

			@Override
			protected long accumulate(long value, long delta, int length) {
				if (delta == neutralDelta())
					return value;
				return delta * length;
			}

			@Override
			protected long neutralValue() {
				return 0;
			}

			@Override
			protected long neutralDelta() {
				return Long.MIN_VALUE;
			}
		};
		tree.init();
		for (int i = 0; i < count; i++)
			tree.update(i, i, value[order[i]]);
		for (int i = 0; i < queryCount; i++) {
			char type = in.readCharacter();
			if (type == 'Q') {
				index = in.readInt() - 1;
				out.printLine(tree.query(position[index], until[index]));
			} else {
				index = in.readInt() - 1;
				int newValue = in.readInt();
				tree.update(position[index], position[index], newValue);
			}
		}
    }

	private int dfs(int vertex, int last, int index, int[] order, int[] until, Graph graph) {
		order[index++] = vertex;
		for (Edge edge : graph.outbound(vertex)) {
			if (edge.getDestination() != last)
				index = dfs(edge.getDestination(), vertex, index, order, until, graph);
		}
		until[vertex] = index - 1;
		return index;
	}
}
