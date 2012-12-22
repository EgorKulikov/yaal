package net.egork;

import net.egork.collections.intervaltree.SumIntervalTree;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskL {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int hanging = in.readInt();
		int friction = in.readInt();
		int[] weight = new int[count];
		int[] price = new int[count];
		IOUtils.readIntArrays(in, weight, price);
		int[] sumPrice = new int[count + 1];
		for (int i = 0; i < count; i++)
			sumPrice[i + 1] = sumPrice[i] + price[i];
		IntervalTree tree = new IntervalTree(count);
		for (int i = 0; i < count; i++)
			tree.update(i, i, weight[i]);
		long answer = 0;
		int tailCuts = 0;
		int frontCuts = 0;
		for (int i = 0; i < count - hanging; i++) {
			long tailWeight = tree.query(count - i - hanging, count - i - 1);
			long requiredWeight = (tailWeight - 1) / friction + 1;
			int last = count - i - hanging - 1;
/*			if (tree.query(0, last) < requiredWeight)
				break;
			int left = 0;
			int right = last;
			while (left < right) {
				int middle = (left + right + 1) >> 1;
				if (tree.query(middle, last) < requiredWeight)
					right = middle - 1;
				else
					left = middle;
			}*/
			int left = tree.binarySearch(last, requiredWeight);
			if (left == -1)
				break;
			long current = sumPrice[left] - sumPrice[0] + sumPrice[count] - sumPrice[count - i];
			if (current > answer) {
				answer = current;
				tailCuts = i;
				frontCuts = left;
			}
		}
		out.printLine(tailCuts + frontCuts, answer);
		for (int i = 0; i < tailCuts; i++)
			out.print('H');
		for (int i = 0; i < frontCuts; i++)
			out.print('T');
		out.printLine();
	}

	static class IntervalTree extends SumIntervalTree {
		public IntervalTree(int size) {
			super(size);
		}

		public int binarySearch(int last, long required) {
			long total = query(0, last);
			if (total < required)
				return -1;
			long max = total - required;
			return binarySearch(0, 0, size - 1, max);
		}

		private int binarySearch(int root, int left, int right, long max) {
			if (left == right)
				return left;
			if (value[2 * root + 1] > max)
				return binarySearch(2 * root + 1, left, (left + right) >> 1, max);
			return binarySearch(2 * root + 2, ((left + right) >> 1) + 1, right, max - value[2 * root + 1]);
		}
	}
}
