package on2011_11.on2011_10_26.taskh0;



import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskH {
	private int[] p = IntegerUtils.generatePrimes(1500000);
	
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int module = in.readInt();
		long answer = 0;
		IntervalTree tree = new IntervalTree(module);
		for (int i = 0; i < count; i++) {
			int next = p[i] % module;
			tree.put(next, i + 1);
			answer += i - tree.getSegment(next + 1, module) + 1;
		}
		out.printLine(answer % module);
	}
}

class IntervalTree {
	private int[] left;
	private int[] right;
	private int[] value;

	public IntervalTree(int size) {
		int arraysSize = Math.max(1, Integer.highestOneBit(size) << 2);
		left = new int[arraysSize];
		right = new int[arraysSize];
		value = new int[arraysSize];
		initTree(0, size, 0);
	}

	private void initTree(int left, int right, int root) {
		this.left[root] = left;
		this.right[root] = right;
		if (right - left > 1) {
			initTree(left, (left + right + 1) / 2, 2 * root + 1);
			initTree((left + right + 1) / 2, right, 2 * root + 2);
		}
	}

	public void put(int position, int value) {
		put(position, value, 0);
	}

	private void put(int position, int value, int root) {
		if (left[root] > position || right[root] <= position)
			return;
		this.value[root] = Math.max(this.value[root], value);
		if (right[root] - left[root] > 1) {
			put(position, value, 2 * root + 1);
			put(position, value, 2 * root + 2);
		}
	}

	public int getSegment(int left, int right) {
		return getSegment(left, right, 0);
	}

	private int getSegment(int left, int right, int root) {
		if (left >= this.right[root] || right <= this.left[root])
			return 0;
		if (left <= this.left[root] && right >= this.right[root])
			return value[root];
		return Math.max(getSegment(left, right, 2 * root + 1), getSegment(left, right, 2 * root + 2));
	}
}