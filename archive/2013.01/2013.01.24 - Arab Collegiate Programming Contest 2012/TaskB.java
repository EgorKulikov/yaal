package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskB {
	int curIndex;
	int[] first, next;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		String initial = in.readString();
		List<Operation> operations = new ArrayList<Operation>();
		operations.add(new Operation(true, 0, -1, initial));
		int total = initial.length();
		while (true) {
			char operation = in.readCharacter();
			if (operation == 'E')
				break;
			if (operation == 'I') {
				String s = in.readString();
				int from = in.readInt();
				operations.add(new Operation(true, from, -1, s));
				total += s.length();
			} else {
				int from = in.readInt();
				int to = in.readInt();
				operations.add(new Operation(false, from, to, null));
			}
		}
		first = new int[operations.size()];
		next = new int[total];
		Arrays.fill(first, -1);
		IntervalTree tree = new IntervalTree(total);
		for (int i = operations.size() - 1; i >= 0; i--) {
			Operation operation = operations.get(i);
			if (operation.type) {
				curIndex = i;
				tree.update(operation.from, operation.s);
			}
		}
		for (int i = 0; i < operations.size(); i++) {
			Operation operation = operations.get(i);
			if (operation.type) {
				for (int j = first[i]; j != -1; j = next[j])
					tree.add(j);
			} else
				tree.query(operation.from, operation.to - operation.from + 1, out);
		}
    }

	static class Operation {
		final boolean type;
		final int from, to;
		final String s;

		Operation(boolean type, int from, int to, String s) {
			this.type = type;
			this.from = from;
			this.to = to;
			this.s = s;
		}
	}

	class IntervalTree {
		protected int size;
		protected int[] value;
		protected char[] result;

		protected IntervalTree(int size) {
			this.size = size;
			int nodeCount = Math.max(1, Integer.highestOneBit(size) << 2);
			value = new int[nodeCount];
			result = new char[size];
			init();
		}

		public void init() {
			init(0, 0, size - 1);
		}

		private void init(int root, int left, int right) {
			if (left == right) {
				value[root] = 1;
			} else {
				int middle = (left + right) >> 1;
				init(2 * root + 1, left, middle);
				init(2 * root + 2, middle + 1, right);
				value[root] = value[2 * root + 1] + value[2 * root + 2];
			}
		}

		public void update(int from, String s) {
			update(0, 0, size - 1, s, -from);
		}

		private void update(int root, int left, int right, String s, int from) {
			if (from >= s.length() || value[root] <= -from || value[root] == 0)
				return;
			if (left == right) {
				value[root] = 0;
				result[left] = s.charAt(from);
				next[left] = first[curIndex];
				first[curIndex] = left;
				return;
			}
			int middle = (left + right) >> 1;
			int oldValue = value[2 * root + 1];
			update(2 * root + 1, left, middle, s, from);
			from += oldValue;
			update(2 * root + 2, middle + 1, right, s, from);
			value[root] = value[2 * root + 1] + value[2 * root + 2];
		}

		public void query(int from, int length, OutputWriter out) {
			query(0, 0, size - 1, -from, length, out);
			out.printLine();
		}

		private void query(int root, int left, int right, int from, int length, OutputWriter out) {
			if (from >= length || value[root] <= -from || value[root] == 0)
				return;
			if (left == right) {
				out.print(result[left]);
				return;
			}
			int middle = (left + right) >> 1;
			query(2 * root + 1, left, middle, from, length, out);
			from += value[2 * root + 1];
			query(2 * root + 2, middle + 1, right, from, length, out);
		}

		public void add(int position) {
			add(0, 0, size - 1, position);
		}

		private void add(int root, int left, int right, int position) {
			if (left > position || right < position)
				return;
			value[root]++;
			if (left != right) {
				int middle = (left + right) >> 1;
				add(2 * root + 1, left, middle, position);
				add(2 * root + 2, middle + 1, right, position);
			}
		}
	}

}
