package on2014_08.on2014_08_10_MemSQL_Start_c_UP_2_0___Round_2.C___Elections;



import net.egork.collections.comparators.IntComparator;
import net.egork.collections.heap.Heap;
import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Iterator;
import java.util.LinkedList;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] vote = new int[count];
		int[] bribe = new int[count];
		IOUtils.readIntArrays(in, vote, bribe);
		IntList[] voters = new IntList[100001];
		for (int i = 0; i <= 100000; i++) {
			voters[i] = new IntArrayList();
		}
		for (int i = 0; i < count; i++) {
			voters[vote[i]].add(i);
		}
		LinkedList<IntList> candidates = new LinkedList<>();
		for (int i = 0; i <= 100000; i++) {
			if (voters[i].size() != 0) {
				voters[i].inPlaceSort(new IntComparator() {
					@Override
					public int compare(int first, int second) {
						return bribe[second] - bribe[first];
					}
				});
				candidates.add(voters[i]);
			}
		}
		int curVotes = count;
		int curCost = (int) ArrayUtils.sumArray(bribe);
		int answer = curCost;
		Heap freeVoters = new Heap(count, 10001	);
		for (int i = 0; i < count - 1; i++) {
			for (Iterator<IntList> iterator = candidates.iterator(); iterator.hasNext();) {
				IntList current = iterator.next();
				if (current.size() <= i) {
					iterator.remove();
				} else {
					curVotes--;
					curCost -= bribe[current.get(i)];
					freeVoters.add(bribe[current.get(i)]);
				}
			}
			while (curVotes < i + 2) {
				curCost += freeVoters.poll();
				curVotes++;
			}
			answer = Math.min(answer, curCost);
		}
		out.printLine(answer);
	}
}
