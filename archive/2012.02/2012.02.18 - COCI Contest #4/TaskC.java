package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int digitCount = in.readInt();
		int canSkip = in.readInt();
		int shouldTake = digitCount - canSkip;
		char[] number = IOUtils.readCharArray(in, digitCount);
		StringBuilder answer = new StringBuilder(shouldTake);
		Tree tree = new Tree(number);
		int index = 0;
		for (int i = 0; i < shouldTake; i++) {
			char next = tree.get(index, index + canSkip + 1);
			while (number[index] != next) {
				index++;
				canSkip--;
			}
			answer.append(next);
			index++;
		}
		out.printLine(answer);
	}
}

class Tree {
	private int length;
	private char[] value;

	public Tree(char[] number) {
		length = number.length;
		int nodeCount = Integer.highestOneBit(length) << 2;
		value = new char[nodeCount];
		init(0, 0, length, number);
	}

	private void init(int root, int left, int right, char[] number) {
		if (left + 1 == right)
			value[root] = number[left];
		else {
			int middle = (left + right) >> 1;
			init(2 * root + 1, left, middle, number);
			init(2 * root + 2, middle, right, number);
			value[root] = (char) Math.max(value[2 * root + 1], value[2 * root + 2]);
		}
	}

	public char get(int from, int to) {
		return get(0, 0, length, from, to);
	}

	private char get(int root, int left, int right, int from, int to) {
		if (left >= to || right <= from)
			return 0;
		if (from <= left && right <= to)
			return value[root];
		int middle = (left + right) >> 1;
		return (char) Math.max(get(2 * root + 1, left, middle, from, to), get(2 * root + 2, middle, right, from, to));
	}
}