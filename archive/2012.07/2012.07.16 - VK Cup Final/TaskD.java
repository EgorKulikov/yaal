package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskD {
	private int[] heights;
	private boolean[] present;
	private int[] next;
	private int[] last;
	private int[] queue;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		heights = IOUtils.readIntArray(in, count);
		int[] order = new int[count];
		int[] temp = new int[count];
		for (int i = 0; i < count; i++)
			order[i] = temp[i] = i;
		sort(order, temp, 0, count);
		long[] delta = new long[count + 2];
		present = new boolean[count];
		Arrays.fill(present, true);
		next = new int[count];
		for (int i = 0; i < count; i++)
			next[i] = i + 1;
		last = new int[count];
		for (int i = 0; i < count; i++)
			last[i] = i - 1;
		int[] left = new int[count];
		int[] right = new int[count];
		queue = new int[count];
		for (int i = count - 1; i >= 0; i--) {
			present[order[i]] = false;
			left[order[i]] = get(order[i], last);
			right[order[i]] = get(order[i], next);
		}
		for (int i = 0; i < count; i++) {
			delta[0] += heights[i];
			delta[Math.min(i - left[i], right[i] - i)] -= heights[i];
			delta[Math.max(i - left[i], right[i] - i)] -= heights[i];
			delta[right[i] - left[i]] += heights[i];
		}
		long sum = 0;
		long curDelta = 0;
		long[] answer = new long[count + 1];
		for (int i = 0; i < count; i++) {
			curDelta += delta[i];
			sum += curDelta;
			answer[i + 1] = sum;
		}
		int queryCount = in.readInt();
		for (int i = 0; i < queryCount; i++) {
			int query = in.readInt();
			out.printLine((double)answer[query] / (count - query + 1));
		}
	}

	private int get(int i, int[] next) {
		int size = 0;
		while (!(i < 0 || i >= heights.length || present[i])) {
			queue[size++] = i;
			i = next[i];
		}
		for (int j = 0; j < size; j++)
			next[queue[j]] = i;
		return i;
	}

	private void sort(int[] order, int[] temp, int from, int to) {
		if (to - from <= 1)
			return;
		int middle = (from + to) >> 1;
		sort(temp, order, from, middle);
		sort(temp, order, middle, to);
		int firstIndex = from;
		int secondIndex = middle;
		int index = from;
		while (firstIndex < middle && secondIndex < to) {
			if (heights[temp[firstIndex]] < heights[temp[secondIndex]])
				order[index++] = temp[firstIndex++];
			else
				order[index++] = temp[secondIndex++];
		}
		if (firstIndex != middle)
			System.arraycopy(temp, firstIndex, order, index, to - index);
		if (secondIndex != to)
			System.arraycopy(temp, secondIndex, order, index, to - index);
	}
}
