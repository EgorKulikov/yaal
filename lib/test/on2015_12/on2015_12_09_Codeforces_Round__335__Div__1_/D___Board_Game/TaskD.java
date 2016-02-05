package on2015_12.on2015_12_09_Codeforces_Round__335__Div__1_.D___Board_Game;



import net.egork.generated.collections.comparator.IntComparator;
import net.egork.generated.collections.list.IntArrayList;
import net.egork.generated.collections.list.IntList;
import net.egork.generated.collections.queue.IntArrayQueue;
import net.egork.generated.collections.queue.IntQueue;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskD {
	int[] last;
	int[] a;
	int[] b;
	int[] c;
	int[] d;
	IntQueue queue = new IntArrayQueue();
	IntList[] lists;
	int[] shift;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		a = new int[n];
		b = new int[n];
		c = new int[n];
		d = new int[n];
		IOUtils.readIntArrays(in, a, b, c, d);
		a = Arrays.copyOf(a, n + 1);
		b = Arrays.copyOf(b, n + 1);
		c = Arrays.copyOf(c, n + 1);
		d = Arrays.copyOf(d, n + 1);
		ArrayUtils.compress(a, c);
		ArrayUtils.compress(b, d);
		last = new int[n + 1];
		Arrays.fill(last, -1);
		last[n] = n;
		lists = new IntList[8 * n + 4];
		shift = new int[8 * n + 4];
		init(0, 0, 2 * n);
		for (int i = 0; i < n; i++) {
			add(0, 0, 2 * n, i);
		}
		IntComparator comparator = (x, y) -> b[x] - b[y];
		for (int i = 0; i < 8 * n + 4; i++) {
			if (lists[i] != null) {
				lists[i].sort(comparator);
			}
		}
		queue.add(n);
		while (!queue.isEmpty()) {
			int current = queue.poll();
			check(0, 0, 2 * n, current);
		}
		if (last[n - 1] == -1) {
			out.printLine(-1);
			return;
		}
		IntList answer = new IntArrayList();
		int current = n - 1;
		while (current != n) {
			answer.add(current + 1);
			current = last[current];
		}
		answer.inPlaceReverse();
		out.printLine(answer.size());
		out.printLine(answer);
	}

	private void check(int root, int left, int right, int id) {
		if (left > c[id]) {
			return;
		}
		if (right <= c[id]) {
			while (shift[root] < lists[root].size()) {
				int current = lists[root].get(shift[root]);
				if (b[current] <= d[id]) {
					if (last[current] == -1) {
						last[current] = id;
						queue.add(current);
					}
					shift[root]++;
				} else {
					break;
				}
			}
			return;
		}
		int middle = (left + right) >> 1;
		check(2 * root + 1, left, middle, id);
		check(2 * root + 2, middle + 1, right, id);
	}

	private void add(int root, int left, int right, int id) {
		if (a[id] < left || a[id] > right) {
			return;
		}
		lists[root].add(id);
		if (left != right) {
			int middle = (left + right) >> 1;
			add(2 * root + 1, left, middle, id);
			add(2 * root + 2, middle + 1, right, id);
		}
	}

	private void init(int root, int left, int right) {
		lists[root] = new IntArrayList();
		if (left != right) {
			int middle = (left + right) >> 1;
			init(2 * root + 1, left, middle);
			init(2 * root + 2, middle + 1, right);
		}
	}
}
