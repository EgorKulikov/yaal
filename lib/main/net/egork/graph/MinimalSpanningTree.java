package net.egork.graph;

import net.egork.collections.comparators.IntComparator;
import net.egork.collections.intcollection.IntHashSet;
import net.egork.collections.intcollection.IntSet;
import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.RecursiveIndependentSetSystem;
import net.egork.misc.ArrayUtils;
import net.egork.numbers.IntegerUtils;

/**
 * @author egorku@yandex-team.ru
 */
public class MinimalSpanningTree {
	public static IntSet minimalTree(final BidirectionalGraph graph) {
		IntSet result = new IntHashSet(graph.vertexCount - 1);
		int[] edgeOrder = new int[graph.edgeCount >> 1];
		for (int i = 0; i < edgeOrder.length; i++)
			edgeOrder[i] = i << 1;
		ArrayUtils.sort(edgeOrder, new IntComparator() {
			public int compare(int first, int second) {
				return IntegerUtils.longCompare(graph.weight(first), graph.weight(second));
			}
		});
		IndependentSetSystem setSystem = new RecursiveIndependentSetSystem(graph.vertexCount);
		for (int i : edgeOrder) {
			if (setSystem.join(graph.source(i), graph.destination(i)))
				result.add(i);
		}
		if (setSystem.getSetCount() == 1)
			return result;
		else
			return null;
	}
}
