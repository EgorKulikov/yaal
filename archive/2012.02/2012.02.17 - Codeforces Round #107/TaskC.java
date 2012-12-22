package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int stopCount = in.readInt();
		int passengerCount = in.readInt();
		long fine = in.readInt();
		int[] cost = IOUtils.readIntArray(in, stopCount);
		int[] probability = IOUtils.readIntArray(in, stopCount - 1);
		long[] profit = new long[stopCount - 1];
		for (int i = 0; i < stopCount - 1; i++)
			profit[i] = (cost[i + 1] - cost[i]) * 50L - fine * probability[i];
		Tree tree = new Tree(profit);
		long answer = 0;
		for (int i = 0; i < passengerCount; i++) {
			int from = in.readInt() - 1;
			int to = in.readInt() - 1;
			answer += tree.get(from, to);
		}
		out.printLine((double)answer / 100);
	}
}

class Tree {
	private long[] min;
	private long[] sum;
	private long[] minLeft;
	private long[] minRight;
	private long[] resultMin;
	private long[] resultMinLeft;
	private long[] resultMinRight;
	private int length;

	Tree(long[] array) {
		int nodeCount = Integer.highestOneBit(array.length) << 2;
		sum = new long[nodeCount];
		min = new long[nodeCount];
		minLeft = new long[nodeCount];
		minRight = new long[nodeCount];
		resultMin = new long[nodeCount];
		resultMinLeft = new long[nodeCount];
		resultMinRight = new long[nodeCount];
		length = array.length;
		init(0, 0, length, array);
	}

	private void init(int root, int left, int right, long[] array) {
		if (left + 1 == right) {
			sum[root] = array[left];
			min[root] = Math.max(array[left], 0);
			minLeft[root] = array[left];
			minRight[root] = array[left];
			return;
		}
		int middle = (left + right) >> 1;
		init(2 * root + 1, left, middle, array);
		init(2 * root + 2, middle, right, array);
		sum[root] = sum[2 * root + 1] + sum[2 * root + 2];
		min[root] = Math.max(minRight[2 * root + 1] + minLeft[2 * root + 2], Math.max(min[2 * root + 1], min[2 * root + 2]));
		minLeft[root] = Math.max(minLeft[2 * root + 1], sum[2 * root + 1] + minLeft[2 * root + 2]);
		minRight[root] = Math.max(minRight[2 * root + 2], sum[2 * root + 2] + minRight[2 * root + 1]);
	}

	long get(int from, int to) {
		long result = get(0, 0, length, from, to);
//		result -= resultMin[0];
		return resultMin[0];
	}

	private long get(int root, int left, int right, int from, int to) {
		if (right <= from || left >= to)
			return resultMin[root] = resultMinLeft[root] = resultMinRight[root] = 0;
		if (from <= left && right <= to) {
			resultMin[root] = min[root];
			resultMinLeft[root] = minLeft[root];
			resultMinRight[root] = minRight[root];
			return sum[root];
		}
		int middle = (left + right) >> 1;
		long sumLeft = get(2 * root + 1, left, middle, from, to);
		long sumRight = get(2 * root + 2, middle, right, from, to);
		resultMin[root] = Math.max(resultMinLeft[2 * root + 2] + resultMinRight[2 * root + 1], Math.max(resultMin[2 * root + 1], resultMin[2 * root + 2]));
		resultMinLeft[root] = Math.max(resultMinLeft[2 * root + 1], sumLeft + resultMinLeft[2 * root + 2]);
		resultMinRight[root] = Math.max(resultMinRight[2 * root + 2], sumRight + resultMinRight[2 * root + 1]);
		return sumLeft + sumRight;
	}
}
