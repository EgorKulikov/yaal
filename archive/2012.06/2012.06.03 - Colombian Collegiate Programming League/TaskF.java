package net.egork;

import net.egork.collections.CollectionUtils;
import net.egork.collections.sequence.Array;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskF {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int maxSize = in.readInt();
		if (count == 0 && maxSize == 0)
			throw new UnknownError();
		int[] weights = IOUtils.readIntArray(in, count);
		int[] parent = IOUtils.readIntArray(in, count - 1);
		int max = CollectionUtils.maxElement(Array.wrap(weights));
		if (max <= 0) {
			out.printLine(max);
			return;
		}
		int[] countChildren = new int[count];
		for (int i : parent)
			countChildren[i]++;
		int[][] answer = new int[count][maxSize + 1];
		int[] queue = new int[count];
		int size = 0;
		for (int i = 0; i < count; i++) {
			if (countChildren[i] == 0)
				queue[size++] = i;
		}
		for (int i = 0; i < size; i++) {
			int current = queue[i];
			answer[current][1] = Math.max(answer[current][1], weights[current]);
			if (current == 0)
				break;
			int curParent = parent[current - 1];
			add(answer[curParent], answer[current]);
			countChildren[curParent]--;
			if (countChildren[curParent] == 0)
				queue[size++] = curParent;
		}
		out.printLine(CollectionUtils.maxElement(Array.wrap(answer[0])));
	}

	private void add(int[] parent, int[] array) {
		for (int i = parent.length - 1; i >= 0; i--) {
			for (int j = i; j >= 0; j--)
				parent[i] = Math.max(parent[i], parent[i - j] + array[j]);
		}
	}
}
