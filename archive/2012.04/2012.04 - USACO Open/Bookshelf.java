package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;
import java.util.Stack;

public class Bookshelf {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int length = in.readInt();
		int[] height = new int[count];
		int[] width = new int[count];
		IOUtils.readIntArrays(in, height, width);
		Stack<Integer> stack = new Stack<Integer>();
		long[] sumWidth = new long[count + 1];
		Tree tree = new Tree(count + 1);
		long answer = 0;
		for (int i = 0; i < count; i++) {
			while (!stack.isEmpty()) {
				if (height[stack.peek()] <= height[i])
					stack.pop();
				else
					break;
			}
			tree.setAdd(stack.isEmpty() ? 0 : stack.peek() + 1, i, height[i]);
			stack.add(i);
			sumWidth[i + 1] = sumWidth[i] + width[i];
			int position = Arrays.binarySearch(sumWidth, 0, i + 1, sumWidth[i + 1] - length);
			if (position < 0)
				position = -position - 1;
			answer = tree.get(position, i);
			tree.put(i + 1, answer);
		}
		out.printLine(answer);
	}
}

class Tree {
	private final int size;
	long[] total, main, add;

	public Tree(int size) {
		this.size = size;
		int arraySize = size << 2;
		total = new long[arraySize];
		main = new long[arraySize];
		add = new long[arraySize];
	}

	public long get(int from, int to) {
		return get(0, 0, size - 1, from, to);
	}

	private long get(int root, int left, int right, int from, int to) {
		if (left > to || right < from)
			return Long.MAX_VALUE;
		if (left >= from && right <= to)
			return total[root];
		propagate(root);
		int middle = (left + right) >> 1;
		return Math.min(get(2 * root + 1, left, middle, from, to), get(2 * root + 2, middle + 1, right, from, to));
	}

	private void propagate(int root) {
		propagate(root, 2 * root + 1);
		propagate(root, 2 * root + 2);
	}

	private void propagate(int root, int child) {
		if (add[child] < add[root]) {
			add[child] = add[root];
			total[child] = main[child] + add[child];
		}
	}

	public void setAdd(int from, int to, int value) {
		setAdd(0, 0, size - 1, from, to, value);
	}

	private void setAdd(int root, int left, int right, int from, int to, int value) {
		if (left > to || right < from)
			return;
		if (left >= from && right <= to) {
			add[root] = value;
			total[root] = main[root] + value;
			return;
		}
		propagate(root);
		int middle = (left + right) >> 1;
		setAdd(2 * root + 1, left, middle, from, to, value);
		setAdd(2 * root + 2, middle + 1, right, from, to, value);
		total[root] = Math.min(total[2 * root + 1], total[2 * root + 2]);
	}

	public void put(int position, long value) {
		put(0, 0, size - 1, position, value);
	}

	private void put(int root, int left, int right, int position, long value) {
		if (left > position || right < position)
			return;
		if (left == right) {
			main[root] = value;
			total[root] = value + add[root];
			return;
		}
		propagate(root);
		int middle = (left + right) >> 1;
		put(2 * root + 1, left, middle, position, value);
		put(2 * root + 2, middle + 1, right, position, value);
		main[root] = Math.min(main[2 * root + 1], main[2 * root + 2]);
		total[root] = Math.min(total[2 * root + 1], total[2 * root + 2]);
	}
}
