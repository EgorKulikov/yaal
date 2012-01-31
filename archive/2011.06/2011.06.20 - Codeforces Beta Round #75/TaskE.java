import net.egork.io.IOUtils;
import net.egork.numbers.Rational;
import net.egork.utils.Solver;

import java.io.PrintWriter;

public class TaskE implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int walrusCount = in.readInt();
		int queryCount = in.readInt();
		int[] initial = new int[walrusCount];
		int[] perTurn = new int[walrusCount];
		IOUtils.readIntArrays(in, initial, perTurn);
		IntervalTree tree = new IntervalTree(initial, perTurn);
		for (int i = 0; i < queryCount; i++) {
			int left = in.readInt() - 1;
			int right = in.readInt();
			int time = in.readInt();
			out.println(tree.query(left, right, time) + 1);
		}
	}
}

class IntervalTree {
	private int[] left;
	private int[] right;
	private int[][] order;
	private int[] length;
	private final int[] initial;
	private final int[] perTurn;

	public IntervalTree(int[] initial, int[] perTurn) {
		this.initial = initial;
		this.perTurn = perTurn;
		int size = initial.length;
		int arraysSize = Math.max(1, Integer.highestOneBit(size) << 2);
		left = new int[arraysSize];
		right = new int[arraysSize];
		order = new int[arraysSize][];
		length = new int[arraysSize];
		initTree(0, size, 0);
	}

	private void initTree(int left, int right, int root) {
		this.left[root] = left;
		this.right[root] = right;
		if (right - left > 1) {
			initTree(left, (left + right + 1) / 2, 2 * root + 1);
			initTree((left + right + 1) / 2, right, 2 * root + 2);
			int index1 = 0;
			int index2 = 0;
			order[root] = new int[length[2 * root + 1] + length[2 * root + 2]];
			while (index1 < length[2 * root + 1] || index2 < length[2 * root + 2]) {
				int candidate;
				if (index1 < length[2 * root + 1] && (index2 == length[2 * root + 2] ||
					initial[order[2 * root + 1][index1]] > initial[order[2 * root + 2][index2]] ||
					initial[order[2 * root + 1][index1]] == initial[order[2 * root + 2][index2]] &&
					perTurn[order[2 * root + 1][index1]] > perTurn[order[2 * root + 2][index2]]))
				{
					candidate = order[2 * root + 1][index1++];
				} else
					candidate = order[2 * root + 2][index2++];
				if (length[root] == 0 || perTurn[candidate] > perTurn[order[root][length[root] - 1]]) {
					while (length[root] > 1) {
						Rational time1 = new Rational(initial[order[root][length[root] - 2]] - initial[order[root][length[root] - 1]],
							-perTurn[order[root][length[root] - 2]] + perTurn[order[root][length[root] - 1]]);
						Rational time2 = new Rational(initial[candidate] - initial[order[root][length[root] - 1]],
							-perTurn[candidate] + perTurn[order[root][length[root] - 1]]);
						if (time1.compareTo(time2) >= 0)
							length[root]--;
						else
							break;
					}
					order[root][length[root]++] = candidate;
				}
			}
		} else {
			order[root] = new int[1];
			order[root][0] = left;
			length[root] = 1;
		}
	}

	public int query(int left, int right, long time) {
		return query(left, right, time, 0);
	}

	private int query(int left, int right, long time, int root) {
		if (this.left[root] >= right || this.right[root] <= left)
			return -1;
		if (this.left[root] >= left && this.right[root] <= right) {
			int leftIndex = 0;
			int rightIndex = length[root] - 1;
			while (rightIndex > leftIndex + 2) {
				int leftMiddle = (2 * leftIndex + rightIndex) / 3;
				int rightMiddle = (2 * rightIndex + leftIndex) / 3;
				if (compare(time, order[root][leftMiddle], order[root][rightMiddle]))
					rightIndex = rightMiddle;
				else
					leftIndex = leftMiddle;
			}
			int result = order[root][leftIndex];
			for (int i = leftIndex + 1; i <= rightIndex; i++) {
				if (compare(time, order[root][i], result))
					result = order[root][i];
			}
			return result;
		}
		int leftIndex = query(left, right, time, 2 * root + 1);
		int rightIndex = query(left, right, time, 2 * root + 2);
		if (leftIndex == -1)
			return rightIndex;
		if (rightIndex == -1)
			return leftIndex;
		if (compare(time, leftIndex, rightIndex))
			return leftIndex;
		return rightIndex;
	}

	private boolean compare(long time, int leftIndex, int rightIndex) {
		return initial[leftIndex] + perTurn[leftIndex] * time > initial[rightIndex] + perTurn[rightIndex] * time;
	}
}
