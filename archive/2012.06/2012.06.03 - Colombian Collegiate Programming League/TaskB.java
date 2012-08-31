import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int colorCount = in.readInt();
		if (count == 0 && colorCount == 0)
			throw new UnknownError();
		int[] heights = IOUtils.readIntArray(in, count);
		int[] colors = IOUtils.readIntArray(in, count);
		IntervalTree tree = new IntervalTree(heights, colors);
		int[] leftQueue = new int[count];
		int[] rightQueue = new int[count];
		int allMask = (1 << colorCount) - 1;
		rightQueue[0] = count - 1;
		int size = 1;
		long answer = 0;
		for (int i = 0; i < size; i++) {
			int left = leftQueue[i];
			int right = rightQueue[i];
			if (tree.queryColorMask(left, right) != allMask)
				continue;
			int middle = tree.queryMinPosition(left, right);
			answer = Math.max(answer, (long)heights[middle] * (right - left + 1));
			if (middle != left) {
				leftQueue[size] = left;
				rightQueue[size++] = middle - 1;
			}
			if (middle != right) {
				leftQueue[size] = middle + 1;
				rightQueue[size++] = right;
			}
		}
		out.printLine(answer);
	}
}

class IntervalTree {
	protected int size;
	protected int[] minPosition;
	protected int[] colorsMask;
	private final int[] heights;

	protected IntervalTree(int[] heights, int[] colors) {
		this.heights = heights;
		size = heights.length;
		int nodeCount = Math.max(1, Integer.highestOneBit(size) << 2);
		minPosition = new int[nodeCount];
		colorsMask = new int[nodeCount];
		init(heights, colors);
	}

	public void init(int[] heights, int[] colors) {
		init(0, 0, size - 1, heights, colors);
	}

	private void init(int root, int left, int right, int[] heights, int[] colors) {
		if (left == right) {
			minPosition[root] = left;
			colorsMask[root] = 1 << colors[left];
		} else {
			int middle = (left + right) >> 1;
			init(2 * root + 1, left, middle, heights, colors);
			init(2 * root + 2, middle + 1, right, heights, colors);
			if (heights[minPosition[2 * root + 1]] < heights[minPosition[2 * root + 2]])
				minPosition[root] = minPosition[2 * root + 1];
			else
				minPosition[root] = minPosition[2 * root + 2];
			colorsMask[root] = colorsMask[2 * root + 1] | colorsMask[2 * root + 2];
		}
	}

	public int queryMinPosition(int from, int to) {
		return queryMinPosition(0, 0, size - 1, from, to);
	}

	private int queryMinPosition(int root, int left, int right, int from, int to) {
		if (left > to || right < from)
			return -1;
		if (left >= from && right <= to)
			return minPosition[root];
		int middle = (left + right) >> 1;
		int leftResult = queryMinPosition(2 * root + 1, left, middle, from, to);
		int rightResult = queryMinPosition(2 * root + 2, middle + 1, right, from, to);
		if (leftResult == -1)
			return rightResult;
		if (rightResult == -1)
			return leftResult;
		if (heights[leftResult] < heights[rightResult])
			return leftResult;
		return rightResult;
	}

	public int queryColorMask(int from, int to) {
		return queryColorMask(0, 0, size - 1, from, to);
	}


	private int queryColorMask(int root, int left, int right, int from, int to) {
		if (left > to || right < from)
			return 0;
		if (left >= from && right <= to)
			return colorsMask[root];
		int middle = (left + right) >> 1;
		int leftResult = queryColorMask(2 * root + 1, left, middle, from, to);
		int rightResult = queryColorMask(2 * root + 2, middle + 1, right, from, to);
		return rightResult | leftResult;
	}
}
