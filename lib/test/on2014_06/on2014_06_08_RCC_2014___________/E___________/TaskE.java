package on2014_06.on2014_06_08_RCC_2014___________.E___________;



import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int edgeCount = in.readInt();
		int[] wage = new int[count];
		int[] bonus = new int[count];
		IOUtils.readIntArrays(in, wage, bonus);
		Graph direct = new BidirectionalGraph(count);
		Graph reverse = new BidirectionalGraph(count);
		int[] from = new int[edgeCount];
		int[] to = new int[edgeCount];
		IOUtils.readIntArrays(in, from, to);
		MiscUtils.decreaseByOne(from, to);
		for (int i = 0; i < edgeCount; i++) {
			if (Math.max(wage[from[i]], bonus[from[i]]) <= Math.min(wage[to[i]], bonus[to[i]])) {
				out.printLine(-1);
				return;
			}
			if (Math.min(wage[from[i]], bonus[from[i]]) > Math.min(wage[to[i]], bonus[to[i]]) ||
				Math.max(wage[from[i]], bonus[from[i]]) > Math.max(wage[to[i]], bonus[to[i]]))
			{
				continue;
			}
			if (wage[from[i]] > wage[to[i]] || bonus[from[i]] > bonus[to[i]]) {
				direct.addSimpleEdge(from[i], to[i]);
			} else {
				reverse.addSimpleEdge(from[i], to[i]);
			}
		}
		int[] side = new int[count];
		IntList answer = new IntArrayList();
		int[] queue = new int[count];
		for (int i = 0; i < count; i++) {
			if (side[i] != 0)
				continue;
			side[i] = 1;
			queue[0] = i;
			int size = 1;
			IntList plus = new IntArrayList();
			IntList minus = new IntArrayList();
			plus.add(i + 1);
			for (int j = 0; j < size; j++) {
				int current = queue[j];
				for (int k = direct.firstOutbound(current); k != -1; k = direct.nextOutbound(k)) {
					int next = direct.destination(k);
					if (side[next] == -side[current]) {
						out.printLine(-1);
						return;
					}
					if (side[next] == 0) {
						side[next] = side[current];
						queue[size++] = next;
						if (side[next] == 1)
							plus.add(next + 1);
						else
							minus.add(next + 1);
					}
				}
				for (int k = reverse.firstOutbound(current); k != -1; k = reverse.nextOutbound(k)) {
					int next = reverse.destination(k);
					if (side[next] == side[current]) {
						out.printLine(-1);
						return;
					}
					if (side[next] == 0) {
						side[next] = -side[current];
						queue[size++] = next;
						if (side[next] == 1)
							plus.add(next + 1);
						else
							minus.add(next + 1);
					}
				}
			}
			if (plus.size() > minus.size())
				answer.addAll(minus);
			else
				answer.addAll(plus);
		}
		out.print(answer.size() + " ");
		out.printLine(answer);
	}
}
