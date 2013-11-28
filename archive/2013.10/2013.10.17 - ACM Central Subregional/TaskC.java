package net.egork;

import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.collections.intcollection.IntPair;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class TaskC {
	List<IntPair> answer = new ArrayList<IntPair>();

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		if (count == 2) {
			out.printLine(1);
			out.printLine(1, 2);
			return;
		}
		int[] from = new int[count - 1];
		int[] to = new int[count - 1];
		IOUtils.readIntArrays(in, from, to);
		MiscUtils.decreaseByOne(from, to);
		Graph graph = BidirectionalGraph.createGraph(count, from, to);
		for (int i = 0; i < count; i++) {
			if (graph.nextOutbound(graph.firstOutbound(i)) != -1) {
				IntList result = go(i, -1, graph);
				if (result.size() == 2)
					answer.add(new IntPair(result.get(0), result.get(1)));
				else
					answer.add(new IntPair(result.get(0), i));
				out.printLine(answer.size());
				for (IntPair pair : answer)
					out.printLine(pair.first + 1, pair.second + 1);
				return;
			}
		}
    }

	private IntList go(int current, int last, Graph graph) {
		IntList result = null;
		for (int i = graph.firstOutbound(current); i != -1; i = graph.nextOutbound(i)) {
			int next = graph.destination(i);
			if (next == last)
				continue;
			IntList call = go(next, current, graph);
			if (result == null)
				result = call;
			else if (result.size() == 1) {
				if (call.size() == 1)
					result.add(call.get(0));
				else {
					answer.add(new IntPair(result.get(0), call.get(0)));
					result.set(0, call.get(1));
				}
			} else {
				if (call.size() == 1) {
					answer.add(new IntPair(result.get(1), call.get(0)));
					result.popBack();
				} else {
					answer.add(new IntPair(result.get(1), call.get(1)));
					result.set(1, call.get(0));
				}
			}
		}
		if (result == null) {
			result = new IntArrayList();
			result.add(current);
		}
		return result;
	}
}
