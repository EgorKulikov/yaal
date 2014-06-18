package on2014_06.on2014_06_14_Google_Code_Jam_Round_3_2014.C___Crime_House;



import net.egork.collections.comparators.ReverseComparator;
import net.egork.collections.intcollection.IntPair;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.*;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		NavigableSet<Integer> maskEnter = new TreeSet<>();
		NavigableSet<Integer> maskLeave = new TreeSet<>();
		int[] lastAction = new int[2001];
		int[] firstAction = new int[2001];
		boolean consistent = true;
		List<IntPair> removeEnters = new ArrayList<>();
		List<IntPair> wiggle = new ArrayList<>();
		for (int i = 1; i <= count; i++) {
			char type = in.readCharacter();
			int id = in.readInt();
			if (id == 0) {
				if (type == 'E')
					maskEnter.add(i);
				else
					maskLeave.add(i);
				continue;
			}
			if (lastAction[id] == 0) {
				firstAction[id] = lastAction[id] = type == 'E' ? i : -i;
				continue;
			}
			if (type == 'E') {
				if (lastAction[id] > 0) {
					NavigableSet<Integer> subSet = maskLeave.tailSet(lastAction[id], false);
					if (subSet.isEmpty())
						consistent = false;
					else
						subSet.pollFirst();
				}
				lastAction[id] = i;
			} else {
				if (lastAction[id] < 0)
					removeEnters.add(new IntPair(-lastAction[id], i));
				else
					wiggle.add(new IntPair(lastAction[id], i));
				lastAction[id] = -i;
			}
		}
		Collections.sort(removeEnters, new ReverseComparator<IntPair>());
		Collections.sort(wiggle, new ReverseComparator<IntPair>());
		for (IntPair pair : removeEnters) {
			NavigableSet<Integer> subSet = maskEnter.subSet(pair.first, false, pair.second, false);
			if (subSet.isEmpty())
				consistent = false;
			else
				subSet.pollLast();
		}
		if (!consistent) {
			out.printLine("Case #" + testNumber + ": CRIME TIME");
			return;
		}
		int answer = 0;
		for (int i = 1; i <= 2000; i++) {
			if (lastAction[i] > 0) {
				NavigableSet<Integer> subSet = maskLeave.tailSet(lastAction[i], false);
				if (subSet.isEmpty())
					answer++;
				else
					subSet.pollFirst();
			}
			if (firstAction[i] < 0) {
				NavigableSet<Integer> subSet = maskEnter.headSet(-firstAction[i], false);
				if (!subSet.isEmpty())
					subSet.pollLast();
			}
		}
		for (IntPair pair : wiggle) {
			NavigableSet<Integer> subSet = maskEnter.subSet(pair.first, false, pair.second, false);
			if (subSet.isEmpty())
				continue;
			NavigableSet<Integer> otherSet = maskLeave.subSet(pair.first, false, pair.second, false);
			if (otherSet.isEmpty())
				continue;
			if (subSet.last() > otherSet.first()) {
				subSet.pollLast();
				otherSet.pollFirst();
			}
		}
		for (int i : maskEnter) {
			NavigableSet<Integer> subSet = maskLeave.tailSet(i, false);
			if (subSet.isEmpty())
				answer++;
			else
				subSet.pollFirst();
		}
		out.printLine("Case #" + testNumber + ":", answer);
    }
}
