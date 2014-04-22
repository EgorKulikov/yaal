package net.egork.collections.intcollection;

import java.util.Arrays;
import java.util.Random;

/**
 * @author egor@egork.net
 */
public class IntTreapArray extends IntList {
	private int[] left;
	private int[] right;
	private long[] priority;
	private int[] value;
	private int[] size;
	private int count;
	private Random random = new Random(239);
	private int root = -1;
	private int last = 0;

	public IntTreapArray() {
		createArrays(10);
	}

	public IntTreapArray(int[] array) {
		createArrays(array.length);
		for (int i = 0; i < array.length; i++)
			insert(i, array[i]);
	}

	private void createArrays(int count) {
		left = new int[count];
		right = new int[count];
		priority = new long[count];
		value = new int[count];
		size = new int[count];
	}

	public int get(int at) {
		if (at < 0 || at >= count)
			throw new IndexOutOfBoundsException();
		int current = root;
		while (true) {
			int leftSize = size(left[current]);
			if (leftSize == at)
				return value[current];
			if (leftSize > at)
				current = left[current];
			else {
				at -= leftSize + 1;
				current = right[current];
			}
		}
	}

	public void set(int at, int value) {
		if (at < 0 || at >= count)
			throw new IndexOutOfBoundsException();
		int current = root;
		while (true) {
			int leftSize = size(left[current]);
			if (leftSize == at) {
				this.value[current] = value;
				return;
			}
			if (leftSize > at)
				current = left[current];
			else {
				at -= leftSize + 1;
				current = right[current];
			}
		}
	}

	public void remove(int at) {
		root = remove(root, at);
		count--;
	}

	private int remove(int root, int at) {
		int leftSize = size(left[root]);
		if (leftSize == at)
			return merge(left[root], right[root]);
		if (leftSize > at) {
			left[root] = remove(left[root], at);
		} else {
			right[root] = remove(right[root], at - leftSize - 1);
		}
		updateSize(root);
		return root;
	}

	private void updateSize(int root) {
		size[root] = size(left[root]) + size(right[root]) + 1;
	}

	private int merge(int cLeft, int cRight) {
		if (cLeft == -1)
			return cRight;
		if (cRight == -1)
			return cLeft;
		if (priority[cLeft] > priority[cRight]) {
			right[cLeft] = merge(right[cLeft], cRight);
			updateSize(cLeft);
			return cLeft;
		} else {
			left[cRight] = merge(cLeft, left[cRight]);
			updateSize(cRight);
			return cRight;
		}
	}

	public void add(int value) {
		insert(count, value);
	}

	public void insert(int at, int value) {
		ensureCapacity(last + 1);
		this.value[last] = value;
		priority[last] = random.nextLong();
		left[last] = -1;
		right[last] = -1;
		size[last] = 1;
		IntPair result = split(root, at, 0);
		root = merge(result.first, last);
		root = merge(root, result.second);
		count++;
		last++;
	}

	private IntPair split(int root, int key, int toAdd) {
		if (root == -1)
			return new IntPair(-1, -1);
		int curKey = toAdd + size(left[root]);
		if (key <= curKey) {
			IntPair result = split(left[root], key, toAdd);
			left[root] = result.second;
			updateSize(root);
			return new IntPair(result.first, root);
		} else {
			IntPair result = split(right[root], key, curKey + 1);
			right[root] = result.first;
			updateSize(root);
			return new IntPair(root, result.second);
		}
	}

	private int size(int index) {
		if (index == -1)
			return 0;
		return size[index];
	}

	private void ensureCapacity(int newSize) {
		if (left.length < newSize) {
			newSize = Math.max(newSize, 2 * count);
			left = Arrays.copyOf(left, newSize);
			right = Arrays.copyOf(right, newSize);
			value = Arrays.copyOf(value, newSize);
			priority = Arrays.copyOf(priority, newSize);
			size = Arrays.copyOf(size, newSize);
		}
	}

	public int size() {
		return count;
	}
}
