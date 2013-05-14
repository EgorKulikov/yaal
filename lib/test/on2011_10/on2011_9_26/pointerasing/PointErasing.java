package on2011_10.on2011_9_26.pointerasing;


import net.egork.collections.CollectionUtils;
import net.egork.collections.intcollection.IntArray;
import net.egork.collections.intcollection.IntList;
import net.egork.collections.sequence.Array;
import net.egork.collections.sequence.ListUtils;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class PointErasing {
	public int[] getOutcomes(int[] y) {
		IntList wrappedY = new IntArray(y);
		int min = wrappedY.min();
		int max = wrappedY.max();
		int first = Math.min(wrappedY.find(min),wrappedY.find(max));
		int last = Math.max(wrappedY.findLast(min), wrappedY.findLast(max));
		int minAnswer = wrappedY.count(min);
		if (min != max)
			minAnswer += wrappedY.count(max);
		if (first != 0)
			minAnswer++;
		if (last != y.length - 1)
			minAnswer++;
		int[] leftVariants = go(y, first);
		int[] yReverse = new int[y.length];
		for (int i = 0; i < y.length; i++)
			yReverse[i] = y[y.length - 1 - i];
		int[] rightVariants = go(yReverse, y.length - 1 - last);
		Set<Integer> variants = new TreeSet<Integer>();
		for (int i : leftVariants) {
			for (int j : rightVariants)
				variants.add(i + j);
		}
		int[] result = new int[variants.size()];
		int index = 0;
		for (int i : variants)
			result[index++] = minAnswer + i;
		return result;
	}

	private int[] go(int[] array, int upTo) {
		if (upTo == 0)
			return new int[]{0};
		boolean[][] canBe = new boolean[upTo][upTo];
		canBe[0][0] = true;
		for (int i = 1; i < upTo; i++) {
			for (int j = 0; j < upTo; j++) {
				if (canBe[i - 1][j]) {
					if (array[i] == array[0])
						canBe[i][j + 1] = true;
					else {
						canBe[i][j] = true;
						canBe[upTo - 1][j] = true;
						for (int k = i + 1; k < upTo; k++) {
							if (array[i] < array[0] && array[k] > array[0] || array[i] > array[0] && array[k] < array[0])
								canBe[k - 1][j] = true;
						}
					}
				}
			}
		}
		int size = CollectionUtils.count(Array.wrap(canBe[upTo - 1]), true);
		int[] result = new int[size];
		int index = 0;
		for (int i = 0; i < upTo; i++) {
			if (canBe[upTo - 1][i])
				result[index++] = i;
		}
		return result;
	}


}

