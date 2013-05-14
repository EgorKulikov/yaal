package on2011_11.on2011_10_19.taskf;



import net.egork.misc.ArrayUtils;
import net.egork.collections.sequence.Array;
import net.egork.collections.sequence.ListUtils;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskF {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int clientCount = in.readInt();
		int changeCount = in.readInt();
		int[] lunch = new int[clientCount];
		int[] time = new int[clientCount];
		IOUtils.readIntArrays(in, lunch, time);
		int[] index = new int[changeCount];
		int[] newLunch = new int[changeCount];
		int[] newTime = new int[changeCount];
		IOUtils.readIntArrays(in, index, newLunch, newTime);
		MiscUtils.decreaseByOne(index);
		int[] allTimes = new int[clientCount + changeCount];
		System.arraycopy(time, 0, allTimes, 0, clientCount);
		System.arraycopy(newTime, 0, allTimes, clientCount, changeCount);
		Integer[] order = ListUtils.order(Array.wrap(allTimes));
		int[] reverseOrder = new int[clientCount + changeCount];
		for (int i = 0; i < clientCount + changeCount; i++)
			reverseOrder[order[i]] = i;
		IntervalTree tree = new IntervalTree(clientCount + changeCount);
		int[] position = new int[clientCount];
		for (int i = 0; i < clientCount; i++) {
			position[i] = reverseOrder[i];
			tree.put(position[i], time[i]);
		}
		long result = ArrayUtils.sumArray(lunch);
		for (int i = 0; i < clientCount; i++)
			result -= time[i] * (long)tree.getCount(position[i], clientCount + changeCount);
		out.printLine(result);
		for (int i = 0; i < changeCount; i++) {
			result += newLunch[i] - lunch[index[i]];
			lunch[index[i]] = newLunch[i];
			result += time[index[i]] * (long)tree.getCount(position[index[i]], clientCount + changeCount);
			result += tree.getSegment(0, position[index[i]]);
			tree.remove(position[index[i]], time[index[i]]);
			position[index[i]] = reverseOrder[i + clientCount];
			time[index[i]] = newTime[i];
			tree.put(position[index[i]], time[index[i]]);
			result -= time[index[i]] * (long)tree.getCount(position[index[i]], clientCount + changeCount);
			result -= tree.getSegment(0, position[index[i]]);
			out.printLine(result);
		}
	}
}

class IntervalTree {
	private int[] left;
	private int[] right;
	private long[] value;
	private int[] count;

	public IntervalTree(int size) {
		int arraysSize = Math.max(1, Integer.highestOneBit(size) << 2);
		left = new int[arraysSize];
		right = new int[arraysSize];
		value = new long[arraysSize];
		count = new int[arraysSize];
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

	public void put(int position, long value) {
		put(position, value, 0);
	}

	private void put(int position, long value, int root) {
		if (left[root] > position || right[root] <= position)
			return;
		this.value[root] += value;
		this.count[root]++;
		if (right[root] - left[root] > 1) {
			put(position, value, 2 * root + 1);
			put(position, value, 2 * root + 2);
		}
	}

	public void remove(int position, long value) {
		remove(position, value, 0);
	}

	private void remove(int position, long value, int root) {
		if (left[root] > position || right[root] <= position)
			return;
		this.value[root] -= value;
		this.count[root]--;
		if (right[root] - left[root] > 1) {
			remove(position, value, 2 * root + 1);
			remove(position, value, 2 * root + 2);
		}
	}

	public long getSegment(int left, int right) {
		return getSegment(left, right, 0);
	}

	private long getSegment(int left, int right, int root) {
		if (left >= this.right[root] || right <= this.left[root])
			return 0;
		if (left <= this.left[root] && right >= this.right[root])
			return value[root];
		return getSegment(left, right, 2 * root + 1) + getSegment(left, right, 2 * root + 2);
	}

	public int getCount(int left, int right) {
		return getCount(left, right, 0);
	}

	private int getCount(int left, int right, int root) {
		if (left >= this.right[root] || right <= this.left[root])
			return 0;
		if (left <= this.left[root] && right >= this.right[root])
			return count[root];
		return getCount(left, right, 2 * root + 1) + getCount(left, right, 2 * root + 2);
	}
}
