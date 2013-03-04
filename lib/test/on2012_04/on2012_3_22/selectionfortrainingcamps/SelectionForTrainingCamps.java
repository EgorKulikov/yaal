package on2012_04.on2012_3_22.selectionfortrainingcamps;



import net.egork.collections.CollectionUtils;
import net.egork.collections.sequence.Array;
import net.egork.collections.sequence.ListUtils;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.*;

public class SelectionForTrainingCamps {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] height = new int[count];
		int[] weight = new int[count];
		IOUtils.readIntArrays(in, height, weight);
		Integer[] order = ListUtils.order(Array.wrap(height), Array.wrap(weight));
		Set<Integer> allWeights = new HashSet<Integer>(Array.wrap(weight));
		Integer[] ww = allWeights.toArray(new Integer[allWeights.size()]);
		Arrays.sort(ww);
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < ww.length; i++)
			map.put(ww[i], i);
		for (int i = 0; i < count; i++)
			weight[i] = map.get(weight[i]);
		Tree tree = new Tree(allWeights.size());
		Collections.reverse(Array.wrap(order));
		int[] teamNumber = new int[count];
		int to = allWeights.size() - 1;
		for (int i : order) {
			teamNumber[i] = tree.query(weight[i], to);
			tree.update(weight[i], teamNumber[i] + 1);
		}
		int teamCount = CollectionUtils.maxElement(Array.wrap(teamNumber)) + 1;
		int[] answer = new int[teamCount];
		for (int i : teamNumber)
			answer[i]++;
		out.printLine(teamCount);
		out.printLine(Array.wrap(answer).toArray());
	}
}

class Tree {
	int[] value;
	int size;

	Tree(int size) {
		this.size = size;
		value = new int[4 * size];
	}

	public void update(int position, int value) {
		update(0, 0, size - 1, position, value);
	}

	private void update(int root, int left, int right, int position, int value) {
		if (left > position || right < position)
			return;
		this.value[root] = Math.max(this.value[root], value);
		if (left == right)
			return;
		int middle = (left + right) >> 1;
		update(2 * root + 1, left, middle, position, value);
		update(2 * root + 2, middle + 1, right, position, value);
	}

	public int query(int from, int to) {
		return query(0, 0, size - 1, from, to);
	}

	private int query(int root, int left, int right, int from, int to) {
		if (left > to || right < from)
			return 0;
		if (left >= from && right <= to)
			return value[root];
		int middle = (left + right) >> 1;
		return Math.max(query(2 * root + 1, left, middle, from, to), query(2 * root + 2, middle + 1, right, from, to));
	}
}