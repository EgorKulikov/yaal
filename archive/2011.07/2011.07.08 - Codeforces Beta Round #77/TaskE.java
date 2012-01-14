import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.RecursiveIndependentSetSystem;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;
import java.util.Arrays;

public class TaskE implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int islandCount = in.readInt();
		int roadCount = in.readInt();
		IndependentSetSystem setSystem = new RecursiveIndependentSetSystem(islandCount);
		final int[] size = new int[islandCount];
		Arrays.fill(size, 1);
		setSystem.setListener(new IndependentSetSystem.Listener() {
			public void joined(int joinedRoot, int root) {
				size[root] += size[joinedRoot];
				size[joinedRoot] = 0;
			}
		});
		for (int i = 0; i < roadCount; i++) {
			int from = in.readInt() - 1;
			int to = in.readInt() - 1;
			setSystem.join(from, to);
		}
		int[] count = new int[islandCount + 1];
		for (int region : size)
			count[region]++;
		islandCount = Math.min(islandCount, 77777);
		int[] result = new int[islandCount + 1];
		Arrays.fill(result, Integer.MAX_VALUE / 2);
		init(result, 0);
		Heap heap = new Heap();
		for (int i = 1; i <= islandCount; i++) {
			if (count[i] == 0)
				continue;
			int edge = count[i] * i;
			for (int j = islandCount; j > islandCount - i; j--) {
				heap.clear();
				int shift = 0;
				for (int k = j; k >= 0; k -= i) {
					heap.add(k, result[k] + shift);
					while (true) {
						int head = heap.peekKey();
						if (head - k <= edge) {
							result[k] = heap.peekValue() - shift;
							break;
						}
						heap.poll();
					}
					shift--;
				}
			}
		}
		int answer = result[0];
		if (answer == Integer.MAX_VALUE / 2)
			answer = 0;
		out.println(answer - 1);
	}

	private void init(int[] result, int index) {
		if (index >= result.length)
			return;
		if (index != 0)
			result[index] = 0;
		init(result, index * 10 + 4);
		init(result, index * 10 + 7);
	}
}

class Heap {
	private int size = 0;
	private int[] key = new int[1 << 18];
	private int[] value = new int[1 << 18];

	public void clear() {
		size = 0;
	}

	public void add(int k, int v) {
		int index = size++;
		while (index != 0) {
			int parent = (index - 1) >> 1;
			if (v >= value[parent])
				break;
			key[index] = key[parent];
			value[index] = value[parent];
			index = parent;
		}
		key[index] = k;
		value[index] = v;
	}

	public int peekKey() {
		return key[0];
	}

	public void poll() {
		int k = key[--size];
		int v = value[size];
		int index = 0;
		while (true) {
			int child = (index << 1) + 1;
			if (child >= size)
				break;
			if (child + 1 < size && value[child] > value[child + 1])
				child++;
			if (value[child] >= v)
				break;
			key[index] = key[child];
			value[index] = value[child];
			index = child;
		}
		key[index] = k;
		value[index] = v;
	}

	public int peekValue() {
		return value[0];
	}
}
