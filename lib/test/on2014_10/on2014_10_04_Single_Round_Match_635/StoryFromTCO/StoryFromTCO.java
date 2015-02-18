package on2014_10.on2014_10_04_Single_Round_Match_635.StoryFromTCO;



import net.egork.collections.intcollection.IntHashSet;
import net.egork.collections.intcollection.IntSet;
import net.egork.misc.ArrayUtils;

import java.util.Comparator;
import java.util.NavigableSet;
import java.util.TreeSet;

public class StoryFromTCO {
    public int minimumChanges(int[] places, int[] cutoff) {
		int[] plOrder = ArrayUtils.order(places);
		int[] cuOrder = ArrayUtils.order(cutoff);
		NavigableSet<Integer> available = new TreeSet<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				if (cutoff[o1] != cutoff[o2]) {
					return cutoff[o2] - cutoff[o1];
				}
				return o1 - o2;
			}
		});
		int id = 0;
		int answer = 0;
		IntSet removed = new IntHashSet();
		IntSet free = new IntHashSet();
		for (int i : cuOrder) {
			while (id < places.length && places[plOrder[id]] <= cutoff[i]) {
				int current = plOrder[id++];
				if (removed.contains(current)) {
					free.add(current);
				} else {
					available.add(current);
				}
			}
			if (available.isEmpty() && free.size() == 0) {
				return -1;
			}
			if (available.contains(i)) {
				available.remove(i);
			} else {
				if (free.size() != 0) {
					free.remove(free.iterator().value());
				} else {
					available.pollFirst();
				}
				removed.add(i);
				answer++;
			}
		}
		return answer;
    }
}
