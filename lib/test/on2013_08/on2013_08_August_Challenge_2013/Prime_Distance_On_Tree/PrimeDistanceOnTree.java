package on2013_08.on2013_08_August_Challenge_2013.Prime_Distance_On_Tree;



import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class PrimeDistanceOnTree {
	int[] p;
	int[] nextPrimeIndex;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		p = IntegerUtils.generatePrimes(count + 50000);
		nextPrimeIndex = new int[count];
		int k = 0;
		for (int i = 0; i < count; i++) {
			nextPrimeIndex[i] = k;
			if (i == p[k])
				k++;
		}
		int[] from = new int[count - 1];
		int[] to = new int[count - 1];
		IOUtils.readIntArrays(in, from, to);
		MiscUtils.decreaseByOne(from, to);
		Graph graph = BidirectionalGraph.createGraph(count, from, to);
		int[] queue = new int[count];
		int[] last = new int[count];
		last[0] = -1;
		int size = 1;
		for (int i = 0; i < count; i++) {
			int vertex = queue[i];
			for (int j = graph.firstOutbound(vertex); j != -1; j = graph.nextOutbound(j)) {
				int next = graph.destination(j);
				if (next != last[vertex]) {
					last[next] = vertex;
					queue[size++] = next;
				}
			}
		}
		Container[] containers = new Container[count];
		for (int i = count - 1; i >= 0; i--) {
			int vertex = queue[i];
			Container current = null;
			for (int j = graph.firstOutbound(vertex); j != -1; j = graph.nextOutbound(j)) {
				int next = graph.destination(j);
				if (next == last[vertex])
					continue;
				if (current == null) {
					current = containers[next];
					current.advance();
				} else {
					Container another = containers[next];
					current = current.unite(another);
				}
			}
			if (current == null)
				current = new Container();
			containers[vertex] = current;
		}
		out.printLine((double)containers[0].result / count / (count - 1) * 2);
	}

	private Container go(int vertex, int last, Graph graph) {
		Container current = null;
		for (int i = graph.firstOutbound(vertex); i != -1; i = graph.nextOutbound(i)) {
			int next = graph.destination(i);
			if (next == last)
				continue;
			if (current == null) {
				current = go(next, vertex, graph);
				current.advance();
			} else {
				Container another = go(next, vertex, graph);
				current = current.unite(another);
			}
		}
		if (current == null)
			current = new Container();
		return current;
	}

	class Container {
		int[] array;
		int size;
		int result;

		{
			array = new int[2];
			array[0] = 1;
		}

		void advanceWithoutAdd() {
			ensureCapacity(size + 2);
			size++;
		}

		private void ensureCapacity(int capacity) {
			if (capacity > array.length) {
				int[] newArray = new int[Math.max(capacity, array.length << 1)];
				System.arraycopy(array, 0, newArray, 0, size + 1);
				array = newArray;
			}
		}

		void advance() {
			advanceWithoutAdd();
			array[size] = 1;
			for (int i : p) {
				if (i > size)
					break;
				result += array[size - i];
			}
		}

		Container unite(Container container) {
			container.advanceWithoutAdd();
			if (container.size > size) {
				container.uniteImpl(this);
				return container;
			} else {
				uniteImpl(container);
				return this;
			}
		}

		private void uniteImpl(Container container) {
			result += container.result;
			int containerSize = container.size;
			for (int i = containerSize; i >= 0; i--) {
				int current = container.array[i];
				int distance = containerSize - i;
				int max = distance + size;
				for (int j = nextPrimeIndex[distance]; p[j] <= max; j++)
					result += current * array[size - p[j] + distance];
			}
			for (int i = containerSize; i >= 0; i--)
				array[size - i] += container.array[containerSize - i];
		}
	}
}
